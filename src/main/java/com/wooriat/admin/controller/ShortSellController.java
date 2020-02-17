package com.wooriat.admin.controller;

import com.wooriat.admin.common.utility.SessionUtil;
import com.wooriat.admin.common.utility.StringUtil;
import com.wooriat.admin.constant.AdminConst;
import com.wooriat.admin.domain.ShotSell;
import com.wooriat.admin.domain.TbUser;
import com.wooriat.admin.dto.SessionVo;
import com.wooriat.admin.dto.ShotSellDto;
import com.wooriat.admin.service.ShortSellService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/ShortSell")
@RequiredArgsConstructor
public class ShortSellController {

	private final ShortSellService shortSellService;

	private final ServletContext servletContext;

	@GetMapping("/list")
    @ResponseBody
	public ModelAndView listController(@RequestParam Map<String, Object> params, Model model,
									   @PageableDefault(
                    size= AdminConst.SORT_DEFAULT_SIZE_10)
									   @SortDefault.SortDefaults({
											   @SortDefault(sort = "regDate", direction = Sort.Direction.DESC),
											   @SortDefault(sort = "sellId", direction = Sort.Direction.DESC)
									   }) Pageable pageable) {
		log.info("adminUserController! - Start");
		log.info("page : {}", pageable);

		String searchWord = (String)params.get("searchWord");
		String sortStatus = (String)params.get("sortStatus");

		Page<ShotSell> shotSellList = shortSellService.getList(params, pageable);
		
		int page = shotSellList.getPageable().getPageNumber()+1;

        model.addAttribute("list", shotSellList);
        model.addAttribute("totalPage", shotSellList.getTotalPages());
		model.addAttribute("totalCount", shotSellList.getTotalElements());
        model.addAttribute("page",page);
        model.addAttribute("searchWord",searchWord);
		model.addAttribute("sortStatus",sortStatus);
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sell/shortSellList");
        
        return modelAndView;
	}

	@GetMapping("/createForm")
	@ResponseBody
	public ModelAndView createFormController(Model model) {

		model.addAttribute("type", "insert");

		model.addAttribute("data", new ShotSellDto());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sell/shortSellCreate");
		return modelAndView;
	}

	@GetMapping("/updateForm/{id}")
	@ResponseBody
	public ModelAndView updateFormController(Model model, @PathVariable Long id) {

		ShotSellDto shotSellDto = shortSellService.getDetail(id);

		model.addAttribute("data", shotSellDto);
		model.addAttribute("type", "update");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sell/shortSellCreate");
		return modelAndView;
	}

	@PostMapping("/insert")
	@ResponseBody
	public Map<String,String> insertController(@ModelAttribute ShotSellDto shotSellDto, MultipartHttpServletRequest req){

		Map<String,String> map = new HashMap<String, String>();
		SessionVo sessionVo = (SessionVo) SessionUtil.get(req, AdminConst.SESSION_NAME);

		try {
			String file1 = getUploadFileUrl(req, "imgFile1", "ShortSell");
			String file2 = getUploadFileUrl(req, "imgFile2", "ShortSell");
			String file3 = getUploadFileUrl(req, "imgFile3", "ShortSell");
			String file4 = getUploadFileUrl(req, "imgFile4", "ShortSell");
			String file5 = getUploadFileUrl(req, "imgFile5", "ShortSell");

			List<String> fileList = new ArrayList();
			// 저장시 이미지파일 빈자리 앞부터 정렬 후 저장
			if(!file1.isEmpty()) {
				fileList.add(file1);
			}
			if(!file2.isEmpty()) {
				fileList.add(file2);
				shotSellDto.setInsertFile2(null);
			}
			if(!file3.isEmpty()) {
				fileList.add(file3);
				shotSellDto.setInsertFile3(null);
			}
			if(!file4.isEmpty()) {
				fileList.add(file4);
				shotSellDto.setInsertFile4(null);
			}
			if(!file5.isEmpty()) {
				fileList.add(file5);
				shotSellDto.setInsertFile5(null);
			}

			for(int i=0; i<fileList.size(); i++){
				if(i==0){ shotSellDto.setInsertFile1(fileList.get(i)); continue;}
				if(i==1){ shotSellDto.setInsertFile2(fileList.get(i)); continue;}
				if(i==2){ shotSellDto.setInsertFile3(fileList.get(i)); continue;}
				if(i==3){ shotSellDto.setInsertFile4(fileList.get(i)); continue;}
				if(i==4){ shotSellDto.setInsertFile5(fileList.get(i)); continue;}
			}

			shotSellDto.setUserInfo(new TbUser().builder().uid(sessionVo.getUid()).build());
			shortSellService.insert(req, shotSellDto);
			map.put("status", "success");
		}catch(Exception e) {
			map.put("status", "fail");
			map.put("errorMsg", e.getMessage());
			log.error("userInsert : {} ", e);
		}

		return map;
	}

	@PostMapping("/update")
	@ResponseBody
	public Map<String,Object> updateController(@ModelAttribute ShotSellDto shotSellDto, MultipartHttpServletRequest req){

		Map<String,Object> map = new HashMap<String, Object>();
		SessionVo sessionVo = (SessionVo) SessionUtil.get(req, AdminConst.SESSION_NAME);
		try {

			String file1 = getUploadFileUrl(req, "imgFile1", "ShortSell");
			String file2 = getUploadFileUrl(req, "imgFile2", "ShortSell");
			String file3 = getUploadFileUrl(req, "imgFile3", "ShortSell");
			String file4 = getUploadFileUrl(req, "imgFile4", "ShortSell");
			String file5 = getUploadFileUrl(req, "imgFile5", "ShortSell");

			if(!file1.isEmpty()) {
				shotSellDto.setInsertFile1(file1);
			}
			if(!file2.isEmpty()) {
				shotSellDto.setInsertFile2(file2);
			}
			if(!file3.isEmpty()) {
				shotSellDto.setInsertFile3(file3);
			}
			if(!file4.isEmpty()) {
				shotSellDto.setInsertFile4(file4);
			}
			if(!file5.isEmpty()) {
				shotSellDto.setInsertFile5(file5);
			}

			shotSellDto.setUserInfo(new TbUser().builder().uid(sessionVo.getUid()).build());
			shortSellService.update(shotSellDto);
			map.put("status", "success");
			map.put("sellId", shotSellDto.getSellId());
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

		ShotSellDto shotSellDto = shortSellService.getDetail(id);

		model.addAttribute("data", shotSellDto);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sell/shortSellDetail");
		return modelAndView;
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public Map<String,String> deleteController(@PathVariable Long id) throws Exception {

		Map<String,String> map = new HashMap<String, String>();

		try {
			shortSellService.delete(id);
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

		req.setCharacterEncoding("UTF-8");
		String fileUrl = "";

		MultipartFile file = req.getFile(tagName);
		Part part = req.getPart(tagName);

		if (part != null && part.getSize() > 0) {

			// 실서버 경로 D:\WEBSERVICE\wooriAtUploadFiles\"uploadPath"\파일명
			File uploadDir = new File("D:"+File.separator+"WEBSERVICE"+File.separator+"wooriAtUploadFiles"+File.separator+urlPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			//String fileName =  new String(file.getOriginalFilename().getBytes("8859_1"),"UTF-8");
			String fileName =  file.getOriginalFilename();
			String uploadedFileName = fileName.substring(fileName.lastIndexOf("\\")+1);
			String uploadedFilePath = uploadDir.getAbsolutePath() + File.separator + uploadedFileName;

			part.write(uploadedFilePath);
			fileUrl = uploadedFileName;

		}

		return fileUrl;
	}

}
