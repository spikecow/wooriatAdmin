package com.wooriat.admin.service;


import com.wooriat.admin.domain.TbAnswer;
import com.wooriat.admin.domain.TbQuestion;
import com.wooriat.admin.dto.AnswerDto;
import com.wooriat.admin.dto.QuestionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public interface QaService {

    public Page<TbQuestion> getList(Map<String, Object> params, Pageable pageable);
    public TbAnswer insert(HttpServletRequest req, AnswerDto answerDto) throws IOException, ServletException;
    public TbQuestion update(QuestionDto questionDto);
    public QuestionDto getDetail(Long id);
    public void deleteQuestion(Long id);
    public void deleteAnswer(Long id);

}
