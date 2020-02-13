package com.wooriat.admin.controller;

import com.wooriat.admin.common.utility.SessionUtil;
import com.wooriat.admin.constant.AdminConst;
import com.wooriat.admin.domain.TbUser;
import com.wooriat.admin.dto.SessionVo;
import com.wooriat.admin.dto.UserDto;
import com.wooriat.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/myinfo")
@RequiredArgsConstructor
public class MyInfoController {

	private final UserService userService;

	@GetMapping("/check")
    @ResponseBody
	public ModelAndView checkController(@ModelAttribute UserDto userDto) {

		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("myinfo/pwdCheck");
        
        return modelAndView;
	}

	@PostMapping("/check")
	@ResponseBody
	public Map<String,String> insertController(@ModelAttribute UserDto userDto, HttpServletRequest req){

		Map<String,String> map = new HashMap<String, String>();
		SessionVo sessionVo = (SessionVo) SessionUtil.get(req, AdminConst.SESSION_NAME);

		try {
			TbUser tbUser = userService.passwordCheck(sessionVo.getUserId(), userDto.getUserPwd());
			if(tbUser.getUserPwd() != null && tbUser.getUserPwd().equals(userDto.getUserPwd())){
				map.put("status", "success");
			}else{
				map.put("status", "fail");
			}

		}catch(Exception e) {
			map.put("status", "fail");
			map.put("errorMsg", e.getMessage());
			log.error("userInsert : {} ", e);
		}

		return map;
	}

	@GetMapping("/updateForm")
	@ResponseBody
	public ModelAndView updateFormController(@ModelAttribute UserDto userDto) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("myinfo/pwdCreate");

		return modelAndView;
	}

	@PutMapping("/update")
	@ResponseBody
	public Map<String,String> updateController(@RequestParam Map<String, Object> params, HttpServletRequest req) {

		Map<String,String> map = new HashMap<String, String>();
		SessionVo sessionVo = (SessionVo) SessionUtil.get(req, AdminConst.SESSION_NAME);

		UserDto userDto = userService.getDetail(sessionVo.getUid());

		String userPwd = (String)params.get("userPwd");
		String newPwd = (String)params.get("newPwd1");
		boolean flag = false;

		try {
			if(!userDto.getUserPwd().equals(userPwd)){
				map.put("status", "not");
			}else {
				if(userDto.getUserPwd().equals(newPwd)){
					map.put("status", "eq");
				}else{
					userDto.setUserPwd(newPwd);
					userService.insert(userDto);
					map.put("status", "success");
				}
			}

		}catch (Exception e){
			map.put("status", "fail");
			map.put("errorMsg", e.getMessage());
			log.error("userInsert : {} ", e);
		}

		return map;
	}
}
