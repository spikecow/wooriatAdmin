package com.wooriat.admin.controller;

import com.wooriat.admin.common.utility.SessionUtil;
import com.wooriat.admin.constant.AdminConst;
import com.wooriat.admin.domain.TbNotice;
import com.wooriat.admin.domain.TbUser;
import com.wooriat.admin.dto.NoticeDto;
import com.wooriat.admin.dto.SessionVo;
import com.wooriat.admin.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping(value = "/notice")
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;

	private final ServletContext servletContext;

	private String urlPath = "";

	@GetMapping("/list")
    @ResponseBody
	public ModelAndView listController(@RequestParam Map<String, Object> params, Model model,
									   @PageableDefault(
											   size= AdminConst.SORT_DEFAULT_SIZE_10,
											   sort="seqNo", //정렬 key값
											   direction = Sort.Direction.DESC) Pageable pageable) {
		log.info("adminUserController! - Start");
		log.info("page : {}", pageable);

		String menuCd = (String)params.get("menuCd");
		String typeCd = (String)params.get("typeCd") == null ? "" : (String)params.get("typeCd");
		String sw = (String)params.get("searchWord") == null ? "" : (String)params.get("searchWord");

		Page<TbNotice> noticeList = noticeService.getList(params, pageable);
		
		int page = noticeList.getPageable().getPageNumber()+1;

        model.addAttribute("menuCd", menuCd);
		model.addAttribute("typeCd", typeCd);
        model.addAttribute("list", noticeList);
        model.addAttribute("totalPage", noticeList.getTotalPages());
		model.addAttribute("totalCount", noticeList.getTotalElements());
        model.addAttribute("page",page);
        model.addAttribute("searchWord",sw);
		
		ModelAndView modelAndView = new ModelAndView();

		if(StringUtils.equals(menuCd, "M")){
			modelAndView.setViewName("notice/noticeList");
		}
		else if(StringUtils.equals(menuCd, "C")){
			modelAndView.setViewName("notice/companyList");
		}
		else if(StringUtils.equals(menuCd, "P")){
			modelAndView.setViewName("notice/photoList");
		}
		else if(StringUtils.equals(menuCd, "S")){
			modelAndView.setViewName("notice/socialList");
		}
        
        return modelAndView;
	}

	@GetMapping("/createForm")
	@ResponseBody
	public ModelAndView createFormController(@RequestParam Map<String, Object> params, Model model) {

		String menuCd = (String)params.get("menuCd");

		model.addAttribute("type", "POST");
		model.addAttribute("menuCd", (String) menuCd);

		ModelAndView modelAndView = new ModelAndView();

		if(StringUtils.equals(menuCd, "M")){
			modelAndView.setViewName("notice/noticeCreate");
		}
		else if(StringUtils.equals(menuCd, "C")){
			modelAndView.setViewName("notice/companyCreate");
		}
		else if(StringUtils.equals(menuCd, "P")){
			modelAndView.setViewName("notice/photoCreate");
		}
		else if(StringUtils.equals(menuCd, "S")){
			modelAndView.setViewName("notice/socialCreate");
		}

		return modelAndView;
	}

	@GetMapping("/updateForm/{id}/{menuCd}")
	@ResponseBody
	public ModelAndView updateFormController(Model model, @PathVariable Long id, @PathVariable String menuCd) {

		NoticeDto noticeDto = noticeService.getDetail(id);

		model.addAttribute("data", noticeDto);
		model.addAttribute("type", "PUT");

		ModelAndView modelAndView = new ModelAndView();

		if(StringUtils.equals(menuCd, "M")){
			modelAndView.setViewName("notice/noticeCreate");
		}
		else if(StringUtils.equals(menuCd, "C")){
			modelAndView.setViewName("notice/companyCreate");
		}
		else if(StringUtils.equals(menuCd, "P")){
			modelAndView.setViewName("notice/photoCreate");
		}
		else if(StringUtils.equals(menuCd, "S")){
			modelAndView.setViewName("notice/socialCreate");
		}

		return modelAndView;
	}

	@PostMapping("/insert")
	@ResponseBody
	public Map<String,String> insertController(@ModelAttribute NoticeDto noticeDto, MultipartHttpServletRequest req){

		Map<String,String> map = new HashMap<String, String>();
		SessionVo sessionVo = (SessionVo) SessionUtil.get(req, AdminConst.SESSION_NAME);

		if(StringUtils.equals(noticeDto.getMenuCd(), "M")){
			urlPath = "Government";
		}
		else if(StringUtils.equals(noticeDto.getMenuCd(), "C")){
			urlPath = "Notice";
		}
		else if(StringUtils.equals(noticeDto.getMenuCd(), "P")){
			urlPath = "Photo";
		}
		else if(StringUtils.equals(noticeDto.getMenuCd(), "S")){
			urlPath = "Social";
		}

		try {
			String file = getUploadFileUrl(req, "imgFile1", urlPath);

			if(!file.isEmpty()) {
				noticeDto.setImg(file);
			}

			noticeDto.setUserInfo(new TbUser().builder().uid(sessionVo.getUid()).build());
			noticeService.insert(req, noticeDto);
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
	public Map<String,Object> updateController(@ModelAttribute NoticeDto noticeDto, MultipartHttpServletRequest req){

		Map<String,Object> map = new HashMap<String, Object>();
		SessionVo sessionVo = (SessionVo) SessionUtil.get(req, AdminConst.SESSION_NAME);

		if(StringUtils.equals(noticeDto.getMenuCd(), "M")){
			urlPath = "Government";
		}
		else if(StringUtils.equals(noticeDto.getMenuCd(), "C")){
			urlPath = "Notice";
		}
		else if(StringUtils.equals(noticeDto.getMenuCd(), "P")){
			urlPath = "Photo";
		}
		else if(StringUtils.equals(noticeDto.getMenuCd(), "S")){
			urlPath = "Social";
		}

		try {
			String file = getUploadFileUrl(req, "imgFile1", urlPath);

			if(!file.isEmpty()) {
				noticeDto.setImg(file);
			}

			noticeDto.setUserInfo(new TbUser().builder().uid(sessionVo.getUid()).build());
			noticeService.update(noticeDto);
			map.put("status", "success");
			map.put("seqNo", noticeDto.getSeqNo());
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

		NoticeDto noticeDto = noticeService.getDetail(id);

		model.addAttribute("data", noticeDto);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("notice/noticeDetail");
		return modelAndView;
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public Map<String,String> detailController(@PathVariable Long id) throws Exception {

		Map<String,String> map = new HashMap<String, String>();

		try {
			noticeService.delete(id);
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

			// 실서버 경로 D:\WEBSERVICE\wooriAtUploadFiles\"uploadPath"\파일명
			File uploadDir = new File("D:"+File.separator+"WEBSERVICE"+File.separator+"wooriAtUploadFiles"+File.separator+urlPath);

			//String uploadPath = File.separator + "uploads" + File.separator + urlPath;
			//File uploadDir = new File(servletContext.getRealPath("/") + uploadPath);
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
