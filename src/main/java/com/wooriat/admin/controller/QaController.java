package com.wooriat.admin.controller;

import com.wooriat.admin.common.utility.MailUtil;
import com.wooriat.admin.common.utility.SessionUtil;
import com.wooriat.admin.constant.AdminConst;
import com.wooriat.admin.domain.TbAnswer;
import com.wooriat.admin.domain.TbQuestion;
import com.wooriat.admin.domain.TbUser;
import com.wooriat.admin.dto.AnswerDto;
import com.wooriat.admin.dto.QuestionDto;
import com.wooriat.admin.dto.SessionVo;
import com.wooriat.admin.service.QaService;
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

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/qa")
@RequiredArgsConstructor
public class QaController {

	private final QaService qaService;

	@GetMapping("/list")
    @ResponseBody
	public ModelAndView listController(@RequestParam Map<String, Object> params, Model model,
									   @PageableDefault(
											   size= AdminConst.SORT_DEFAULT_SIZE_10,
											   sort="qid", //정렬 key값
											   direction = Sort.Direction.DESC) Pageable pageable) {
		log.info("adminUserController! - Start");
		log.info("page : {}", pageable);

		String searchWord = (String)params.get("searchWord");

		Page<TbQuestion> qList = qaService.getList(params, pageable);
		
		int page = qList.getPageable().getPageNumber()+1;

        model.addAttribute("list", qList);
        model.addAttribute("totalPage", qList.getTotalPages());
		model.addAttribute("totalCount", qList.getTotalElements());
        model.addAttribute("page",page);
        model.addAttribute("searchWord",searchWord);
        model.addAttribute("type", params.get("type"));
		
		ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("qa/qaList");
        
        return modelAndView;
	}

	@PostMapping("/insert")
	@ResponseBody
	public Map<String,Object> insertController(@ModelAttribute AnswerDto answerDto, MultipartHttpServletRequest req){

		Map<String,Object> map = new HashMap<String, Object>();
		SessionVo sessionVo = (SessionVo) SessionUtil.get(req, AdminConst.SESSION_NAME);

		try {
			answerDto.setUserInfo(new TbUser().builder().uid(sessionVo.getUid()).build());
			TbAnswer tbAnswer = qaService.insert(req, answerDto);
			qaService.mailSend(answerDto);  // 메일 전송
			map.put("status", "success");
			map.put("qid", answerDto.getQid());
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

		QuestionDto questionDto = qaService.getDetail(id);

		model.addAttribute("data", questionDto);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("qa/qaDetail");
		return modelAndView;
	}

	@DeleteMapping("/delete/question/{id}")
	@ResponseBody
	public Map<String,String> deleteQuestionController(@PathVariable Long id) throws Exception {

		Map<String,String> map = new HashMap<String, String>();

		try {
			qaService.deleteQuestion(id);
			map.put("status", "success");
		}catch (Exception e){
			map.put("status", "fail");
			map.put("errorMsg", e.getMessage());
			log.error("userInsert : {} ", e);
		}

		return map;
	}

	@DeleteMapping("/delete/answer/{id}")
	@ResponseBody
	public Map<String,String> detailAnswerController(@PathVariable Long id) throws Exception {

		Map<String,String> map = new HashMap<String, String>();

		try {
			qaService.deleteAnswer(id);
			map.put("status", "success");
		}catch (Exception e){
			map.put("status", "fail");
			map.put("errorMsg", e.getMessage());
			log.error("userInsert : {} ", e);
		}

		return map;
	}

}
