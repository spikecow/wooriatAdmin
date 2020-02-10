package com.wooriat.admin.dto;

import com.wooriat.admin.common.enums.type.QuestionType;
import com.wooriat.admin.domain.TbAnswer;
import com.wooriat.admin.domain.TbQuestion;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Data
public class QuestionDto {

    private Long qid;
    private String name;
    private String email;
    private String tel;
    private String title;
    private String content;
    private List<TbAnswer> answers;
    private LocalDateTime cretDtm;

    public TbQuestion toEntity(){

        return TbQuestion.builder()
                .qid(this.qid)
                .name(this.name)
                .email(this.email)
                .tel(this.tel)
                .title(this.title)
                .content(this.content)
                .build();
    }

    public QuestionDto(Optional<TbQuestion> tbQuestion) {
        if(tbQuestion.isPresent()) {
            this.qid = tbQuestion.get().getQid();
            this.name = tbQuestion.get().getName();
            this.email = tbQuestion.get().getEmail();
            this.tel = tbQuestion.get().getTel();
            this.title = tbQuestion.get().getTitle();
            this.content = tbQuestion.get().getContent();
            this.answers = tbQuestion.get().getAnswers();
            this.cretDtm = tbQuestion.get().getCretDtm();
        }
    }

    public QuestionDto toDto(QuestionDto questionDto) {

        this.qid = questionDto.getQid();
        this.name = questionDto.getName();
        this.email = questionDto.getEmail();
        this.tel = questionDto.getTel();
        this.title = questionDto.getTitle();
        this.content = questionDto.getContent();
        this.answers = questionDto.getAnswers();

        return questionDto;
    }
}
