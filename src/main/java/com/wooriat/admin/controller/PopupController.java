package com.wooriat.admin.controller;

import com.wooriat.admin.common.utility.SessionUtil;
import com.wooriat.admin.constant.AdminConst;
import com.wooriat.admin.domain.TbPopup;
import com.wooriat.admin.domain.TbUser;
import com.wooriat.admin.dto.PopupDto;
import com.wooriat.admin.dto.SessionVo;
import com.wooriat.admin.service.PopupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/popup")
@RequiredArgsConstructor
public class PopupController {

	private final PopupService popupService;

	private final ServletContext servletContext;

	@GetMapping("/list")
    @ResponseBody
	public ModelAndView listController(@RequestParam Map<String, Object> params, Model model,
									   @PageableDefault(
											   size= AdminConst.SORT_DEFAULT_SIZE_10,
											   sort="popupId", //정렬 key값
											   direction = Sort.Direction.DESC) Pageable pageable) {
		log.info("adminUserController! - Start");
		log.info("page : {}", pageable);

		String searchWord = (String)params.get("searchWord");

		Page<TbPopup> popupList = popupService.getList(params, pageable);
		
		int page = popupList.getPageable().getPageNumber()+1;

        model.addAttribute("list", popupList);
        model.addAttribute("totalPage", popupList.getTotalPages());
		model.addAttribute("totalCount", popupList.getTotalElements());
        model.addAttribute("page",page);
        model.addAttribute("searchWord",searchWord);
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("popup/popupList");
        
        return modelAndView;
	}

	@GetMapping("/createForm")
	@ResponseBody
	public ModelAndView createFormController(Model model) {

		model.addAttribute("type", "POST");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("popup/popupCreate");
		return modelAndView;
	}

	@GetMapping("/updateForm/{id}")
	@ResponseBody
	public ModelAndView updateFormController(Model model, @PathVariable Long id) {

		PopupDto popupDto = popupService.getDetail(id);

		model.addAttribute("data", popupDto);
		model.addAttribute("type", "PUT");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("popup/popupCreate");
		return modelAndView;
	}

	@PostMapping("/insert")
	@ResponseBody
	public Map<String,String> insertController(@ModelAttribute PopupDto popupDto, MultipartHttpServletRequest req){

		Map<String,String> map = new HashMap<String, String>();
		SessionVo sessionVo = (SessionVo) SessionUtil.get(req, AdminConst.SESSION_NAME);

		try {
			String file = getUploadFileUrl(req, "imgFile1", "popup");

			if(!file.isEmpty()) {
				popupDto.setPopupImg(file);
			}

			popupDto.setUserInfo(new TbUser().builder().uid(sessionVo.getUid()).build());
			popupService.insert(req, popupDto);
			map.put("status", "success");
		}catch(Exception e) {
			map.put("status", "fail");
			map.put("errorMsg", e.getMessage());
			log.error("userInsert : {} ", e);
		}

		return map;
	}

	@PutMapping("/insert")
	@ResponseBody
	public Map<String,Object> updateController(@ModelAttribute PopupDto popupDto, MultipartHttpServletRequest req){

		Map<String,Object> map = new HashMap<String, Object>();
		SessionVo sessionVo = (SessionVo) SessionUtil.get(req, AdminConst.SESSION_NAME);
		try {
			String file = getUploadFileUrl(req, "imgFile1", "popup");

			if(!file.isEmpty()) {
				popupDto.setPopupImg(file);
			}

			popupDto.setUserInfo(new TbUser().builder().uid(sessionVo.getUid()).build());
			popupService.insert(req, popupDto);
			map.put("status", "success");
			map.put("popupId", popupDto.getPopupId());
		}catch(Exception e) {
			map.put("status", "fail");
			map.put("errorMsg", e.getMessage());
			log.error("userInsert : {} ", e);
		}

		return map;
	}

	@GetMapping("/detail/{id}")
	@ResponseBody
	public ModelAndView detailController(Model model, @PathVariable Long id) throws Exception {

		PopupDto popupDto = popupService.getDetail(id);

		model.addAttribute("data", popupDto);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("popup/popupDetail");
		return modelAndView;
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public Map<String,String> deleteController(@PathVariable Long id) throws Exception {

		Map<String,String> map = new HashMap<String, String>();

		try {
			popupService.delete(id);
			map.put("status", "success");
		}catch (Exception e){
			map.put("status", "fail");
			map.put("errorMsg", e.getMessage());
			log.error("userInsert : {} ", e);
		}

		return map;
	}

	public String getUploadFileUrl(MultipartHttpServletRequest req,
								   String tagName, String urlPath ) throws IOException, ServletException {

		String fileUrl = "";

		Part part = req.getPart(tagName);

		if (part != null && part.getSize() > 0) {

			String uploadPath = File.separator + "uploads" + File.separator + urlPath;
			File uploadDir = new File(servletContext.getRealPath("/") + uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			String uploadedFileName = part.getSubmittedFileName();
			String uploadedFilePath = uploadDir.getAbsolutePath() + File.separator + uploadedFileName;

			part.write(uploadedFilePath);
			fileUrl = uploadedFileName;

		}

		return fileUrl;
	}

}
