package com.wooriat.admin.service;

import com.querydsl.core.BooleanBuilder;
import com.wooriat.admin.common.enums.type.QuestionType;
import com.wooriat.admin.common.exception.NotExistDataException;
import com.wooriat.admin.domain.*;
import com.wooriat.admin.dto.AnswerDto;
import com.wooriat.admin.dto.QuestionDto;
import com.wooriat.admin.repository.AnswerRepository;
import com.wooriat.admin.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QaServiceImpl implements QaService {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;


    @Override
    public Page<TbQuestion> getList(Map<String, Object> params, Pageable pageable) {
        QTbQuestion tbQuestion = QTbQuestion.tbQuestion;
        BooleanBuilder builder = new BooleanBuilder();

        String searchWord = (String)params.get("searchWord");
        String type = (String)params.get("type");

        if(searchWord != null && !searchWord.equals("")){
            builder.and(tbQuestion.title.like("%"+searchWord+"%")).or(tbQuestion.content.like("%"+searchWord+"%"))
            .or(tbQuestion.name.like("%"+searchWord+"%"));
        }
        if(type != null && !type.equals("")){
            builder.and(tbQuestion.type.eq(type));
        }

        return questionRepository.findAll(builder, pageable);
    }

    @Override
    @Transactional
    public TbAnswer insert(HttpServletRequest req, AnswerDto answerDto) throws IOException, ServletException {
        return answerRepository.save(answerDto.toEntity());
    }

    @Override
    @Transactional
    public TbQuestion update(QuestionDto questionDto) {
        return null;
    }

    @Override
    public QuestionDto getDetail(Long id) {
        return new QuestionDto(questionRepository.findById(id));
    }

    @Override
    @Transactional
    public void deleteQuestion(Long id) {
        Optional<TbQuestion> byId = questionRepository.findById(id);
        if(!byId.isPresent()){
            throw new NotExistDataException("존재하지 않는 데이터 입니다.");
        }
        questionRepository.deleteById(byId.get().getQid());
    }

    @Override
    @Transactional
    public void deleteAnswer(Long id) {
        Optional<TbAnswer> byId = answerRepository.findById(id);
        if(!byId.isPresent()){
            throw new NotExistDataException("존재하지 않는 데이터 입니다.");
        }
        answerRepository.deleteById(byId.get().getAid());
    }
}
