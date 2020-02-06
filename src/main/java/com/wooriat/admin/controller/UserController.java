package com.wooriat.admin.controller;

import com.wooriat.admin.constant.AdminConst;
import com.wooriat.admin.domain.TbUser;
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
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/list")
    @ResponseBody
	public ModelAndView listController(@RequestParam Map<String, Object> params, Model model,
									   @PageableDefault(
                    size= AdminConst.SORT_DEFAULT_SIZE_10,
                    sort="uid", //정렬 key값
                    direction = Sort.Direction.DESC) Pageable pageable) {
		log.info("adminUserController! - Start");
		log.info("page : {}", pageable);

		String searchWord = (String)params.get("searchWord");
		Page<TbUser> userList = userService.getUserList(searchWord, pageable);
		
		int page = userList.getPageable().getPageNumber()+1;

        model.addAttribute("list", userList);
        model.addAttribute("totalPage", userList.getTotalPages());
		model.addAttribute("totalCount", userList.getTotalElements());
        model.addAttribute("page",page);
        model.addAttribute("searchWord",searchWord);

		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/userList");
        
        return modelAndView;
	}

	@GetMapping("/createForm")
	@ResponseBody
	public ModelAndView createFormController(Model model) {

		model.addAttribute("menu", userService.menuList());
		model.addAttribute("type", "POST");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/userCreate");
		return modelAndView;
	}

	@GetMapping("/updateForm/{id}")
	@ResponseBody
	public ModelAndView updateFormController(Model model, @PathVariable Long id) {

		UserDto userDto = userService.getDetail(id);

		model.addAttribute("data", userDto);
		model.addAttribute("menu", userService.menuList());
		model.addAttribute("type", "PUT");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/userCreate");
		return modelAndView;
	}

	@PostMapping("/insert")
	@ResponseBody
	public Map<String,String> insertController(@ModelAttribute UserDto userDto, HttpServletRequest req){

		Map<String,String> map = new HashMap<String, String>();
		//LoginVo loginVo = (LoginVo) SessionUtil.get(req, GsItmAdminConst.SESSION_NAME);

		try {
			userService.insert(userDto);
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
	public Map<String,Object> updateController(@ModelAttribute UserDto userDto, HttpServletRequest req){

		Map<String,Object> map = new HashMap<String, Object>();
		//LoginVo loginVo = (LoginVo) SessionUtil.get(req, GsItmAdminConst.SESSION_NAME);

		try {
			userService.update(userDto);
			map.put("status", "success");
			map.put("uid", userDto.getUid());
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

		UserDto userDto = userService.getDetail(id);

		model.addAttribute("data", userDto);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/userDetail");
		return modelAndView;
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public Map<String,String> deleteController(@PathVariable Long id) throws Exception {

		Map<String,String> map = new HashMap<String, String>();

		try {
			userService.delete(id);
			map.put("status", "success");
		}catch (Exception e){
			map.put("status", "fail");
			map.put("errorMsg", e.getMessage());
			log.error("userInsert : {} ", e);
		}

		return map;
	}
	
}
