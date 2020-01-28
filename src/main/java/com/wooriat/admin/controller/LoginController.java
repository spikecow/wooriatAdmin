package com.wooriat.admin.controller;

import com.wooriat.admin.dto.SessionVo;
import com.wooriat.admin.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping(value = "/admin")
@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	private final String LOGIN_VIEW_RETURN_URL = "login/login";

	@RequestMapping(value = "/loginView", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView loginPage(@RequestParam Map<String, String> params, Model model, HttpServletRequest req,
								  HttpServletResponse res) {
		log.info("\n\n\n----------------login-------------------------------\n\n");
		model.addAttribute("returnUrl", params.get("returnUrl"));
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(LOGIN_VIEW_RETURN_URL);

		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestParam Map<String, String> params, HttpServletRequest req,
                                     HttpServletResponse res) {
		Map<String,Object> resultMap = new HashMap<>();
		try {
			resultMap =	loginService.loginUser(params,req,res);
		} catch (Exception ex) {
			resultMap.put("status", "fail");
			resultMap.put("msg", ex.getMessage());
			log.error("LoginException : {}",ex);
		}
		
		return resultMap;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> logout(@RequestParam Map<String, Object> params, HttpServletRequest req,
                                      HttpServletResponse res) {


		SessionVo sessionVo = (SessionVo) req.getSession().getAttribute("SESSION_USERINFO");

		HttpSession session = req.getSession();
		if (session != null) {
			session.invalidate();
		}
		
		try {
			
			params.put("account", sessionVo.getUserId());
			loginService.logout(params);
		}catch(Exception e) {
			params.put("status", "fail");
			params.put("errorMsg", e.getMessage());
			log.error("LoginException : {}",e);
		}
		log.info("[Account({}) Logout  Success !!]",sessionVo.getUserId());
		params.put("status", "success");
		
		return params;
	}
}
