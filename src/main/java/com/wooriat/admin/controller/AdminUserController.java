package com.wooriat.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wooriat.admin.common.utility.SessionUtil;
import com.wooriat.admin.domain.login.LoginVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wooriat.admin.constant.AdminConst;
import com.wooriat.admin.domain.adminuser.TbAdmUserMgt;
import com.wooriat.admin.service.AdminUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/manager")
@RequiredArgsConstructor
public class AdminUserController {
	
	private final AdminUserService adminUserService;

	private final String ADMINUSER_LIST_RETURN_URL = "adminuser/adminuserList";
	
	
	@GetMapping("/list")
    @ResponseBody
	public ModelAndView adminUserControlloer(@RequestParam Map<String, Object> params, Model model,
            @PageableDefault(
                    size= AdminConst.SORT_DEFAULT_SIZE_10,
                    sort= AdminConst.ADMIN_USER_SORT_KEY,
                    direction = Sort.Direction.DESC) Pageable pageable) {
		log.info("adminUserController! - Start");
		log.info("page : {}", pageable);

		String searchWord = (String)params.get("searchWord");
		Page<TbAdmUserMgt> adminUserList = adminUserService.getAdminUserList(searchWord, pageable);
		
		int page = adminUserList.getPageable().getPageNumber()+1;

		log.info("adminUserList : {}", adminUserList);

        model.addAttribute("data",adminUserList);
        model.addAttribute("totalPage",adminUserList.getTotalPages());
        model.addAttribute("page",page);
        model.addAttribute("searchWord",searchWord);
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ADMINUSER_LIST_RETURN_URL);
        
        return modelAndView;
	}
	
	@PostMapping("/update")
	@ResponseBody
	public Map<String,String> adminUserUpdate(@ModelAttribute TbAdmUserMgt tbAdmUserMgt,
											HttpServletRequest req){
		log.info("adminUserUpdate! - Start");
		Map<String,String> map = new HashMap<String, String>();
		LoginVo loginVo = (LoginVo) SessionUtil.get(req, AdminConst.SESSION_NAME);
		log.info(tbAdmUserMgt.toString());
		
		try {

			tbAdmUserMgt.setMdfyUserId(loginVo.getUserId());
			
			boolean chk = adminUserService.updateAdminUser(tbAdmUserMgt);
			
			if(chk) {
				map.put("status", "success");
			}
			else {
				map.put("status", "notexsit");
			}

		}catch (Exception e) {
			// TODO: handle exception
			map.put("status", "fail");
			map.put("errorMsg", e.getMessage());
			log.error("adminUserUpdate : {} ", e);
		}


		log.info("adminUserUpdate! - End");
		return map;
	}
	
	@PostMapping("/insert")
	@ResponseBody
	public Map<String,String> adminUserInsert(@ModelAttribute TbAdmUserMgt tbAdmUserMgt,
											  HttpServletRequest req){

		log.info("adminUserInsert! - Start");
		Map<String,String> map = new HashMap<String, String>();
		LoginVo loginVo = (LoginVo) SessionUtil.get(req, AdminConst.SESSION_NAME);
		log.info(tbAdmUserMgt.toString());

		try {

			tbAdmUserMgt.setCretUserId(loginVo.getUserId());
			tbAdmUserMgt.setMdfyUserId(loginVo.getUserId());

			boolean chk = adminUserService.insertAdminUser(tbAdmUserMgt);
			
			if(chk) {
				map.put("status", "success");
			}
			else {
				map.put("status", "duplicate");
			}

		}catch(Exception e) {
			map.put("status", "fail");
			map.put("errorMsg", e.getMessage());
			log.error("adminuserInsert : {} ", e);
		}
		

		log.info("adminUserInsert! - End");
		return map;
	}
	
	@GetMapping("/detail")
	@ResponseBody
	public Map<String,Object> adminUserDetail(@RequestParam Map<String, Object> params) throws Exception {
		
		String userId = (String)params.get("userId");
		log.info("adminUserDetail userId : {}", userId);

		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			TbAdmUserMgt detailAdmUser = adminUserService.getDetailAdminUser(userId);
			log.info("detail : {}", detailAdmUser);
			map.put("data", detailAdmUser);
			map.put("status", "success");
			
		}catch (Exception e) {
			map.put("status", "fail");
			map.put("errorMsg", e.getMessage());
			log.error("admUser Detail : {} ", e);
		}

		log.info("admDetail Map : {}" , map);

		return map;
	}
	
	@PostMapping("/deleteList")
	@ResponseBody
	public Map<String, Object> adminUserDeleteList(@RequestParam Map<String, Object> params){
		log.info("adminUser DelList - Start");
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			adminUserService.deleteAdminUser(params);
			map.put("status", "success");
			
		}catch (Exception e) {
			// TODO: handle exception
			map.put("status", "fail");
			map.put("errorMsg", e.getMessage());
			log.error("delService : {} " , e);
		}

		log.info("adminUser DelList - End : {}", map);
		return map;
	}
	
}
