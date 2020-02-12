package com.wooriat.admin.controller;

import com.wooriat.admin.common.utility.SessionUtil;
import com.wooriat.admin.constant.AdminConst;
import com.wooriat.admin.domain.KoaSale;
import com.wooriat.admin.domain.TbUser;
import com.wooriat.admin.dto.KoaSaleDto;
import com.wooriat.admin.dto.SessionVo;
import com.wooriat.admin.service.SaleService;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/SaleItem")
@RequiredArgsConstructor
public class SaleController {

	private final SaleService saleService;

	private final ServletContext servletContext;

	@GetMapping("/list")
    @ResponseBody
	public ModelAndView listController(@RequestParam Map<String, Object> params, Model model,
									   @PageableDefault(
                    size= AdminConst.SORT_DEFAULT_SIZE_10,
                    sort="regDate", //정렬 key값
                    direction = Sort.Direction.DESC) Pageable pageable) {
		log.info("adminUserController! - Start");
		log.info("page : {}", pageable);

		String searchWord = (String)params.get("searchWord");
		String bizCase = (String)params.get("bizCase");
		String progress6 = (String)params.get("progress6");
		String status = (String)params.get("status");

		Page<KoaSale> saleList = saleService.getList(params, pageable);
		
		int page = saleList.getPageable().getPageNumber()+1;

        model.addAttribute("list", saleList);
        model.addAttribute("totalPage", saleList.getTotalPages());
		model.addAttribute("totalCount", saleList.getTotalElements());
        model.addAttribute("page",page);
        model.addAttribute("searchWord",searchWord);
		model.addAttribute("bizCase",bizCase);
		model.addAttribute("progress6",progress6);
		model.addAttribute("status",status);
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sale/objectSaleList");
        
        return modelAndView;
	}

	@GetMapping("/createForm")
	@ResponseBody
	public ModelAndView createFormController(Model model) {

		model.addAttribute("type", "POST");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sale/objectSaleCreate");
		return modelAndView;
	}

	@GetMapping("/updateForm/{id}")
	@ResponseBody
	public ModelAndView updateFormController(Model model, @PathVariable Long id) {

		KoaSaleDto koaSaleDto = saleService.getDetail(id);

		model.addAttribute("data", koaSaleDto);
		model.addAttribute("type", "PUT");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sale/objectSaleCreate");
		return modelAndView;
	}

	@PostMapping("/insert")
	@ResponseBody
	public Map<String,String> insertController(@ModelAttribute KoaSaleDto koaSaleDto, MultipartHttpServletRequest req){

		Map<String,String> map = new HashMap<String, String>();
		SessionVo sessionVo = (SessionVo) SessionUtil.get(req, AdminConst.SESSION_NAME);

		try {
			String file1 = getUploadFileUrl(req, "imgFile1", "SaleItem");
			String file2 = getUploadFileUrl(req, "imgFile2", "SaleItem");
			if(!file1.isEmpty()) {
				koaSaleDto.setNPhoto1(file1);
			}
			if(!file2.isEmpty()) {
				koaSaleDto.setNPhoto2(file2);
			}

			koaSaleDto.setUserInfo(TbUser.builder().uid(sessionVo.getUid()).build());
			saleService.insert(koaSaleDto);
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
	public Map<String,Object> updateController(@ModelAttribute KoaSaleDto koaSaleDto, MultipartHttpServletRequest req){

		Map<String,Object> map = new HashMap<String, Object>();
		SessionVo sessionVo = (SessionVo) SessionUtil.get(req, AdminConst.SESSION_NAME);

		try {
			String file1 = getUploadFileUrl(req, "imgFile1", "SaleItem");
			String file2 = getUploadFileUrl(req, "imgFile2", "SaleItem");
			if(!file1.isEmpty()) {
				koaSaleDto.setNPhoto1(file1);
			}
			if(!file2.isEmpty()) {
				koaSaleDto.setNPhoto2(file2);
			}

			koaSaleDto.setUserInfo(TbUser.builder().uid(sessionVo.getUid()).build());
			saleService.update(koaSaleDto);
			map.put("status", "success");
			map.put("saleId", koaSaleDto.getSaleId());
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

		KoaSaleDto koaSaleDto = saleService.getDetail(id);

		model.addAttribute("data", koaSaleDto);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("sale/objectSaleDetail");
		return modelAndView;
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public Map<String,String> deleteController(@PathVariable Long id) throws Exception {

		Map<String,String> map = new HashMap<String, String>();

		try {
			saleService.delete(id);
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
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			String uploadedFileName = part.getSubmittedFileName();
			LocalDateTime localDate = LocalDateTime.now();
			String uploadTime = localDate.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
			String uploadedFilePath = uploadDir.getAbsolutePath() + File.separator + uploadTime+"_"+uploadedFileName;

			part.write(uploadedFilePath);
			fileUrl = uploadedFileName;

		}

		return fileUrl;
	}
	
}
