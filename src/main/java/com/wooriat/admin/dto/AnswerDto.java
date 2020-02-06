package com.wooriat.admin.dto;

import com.wooriat.admin.domain.TbAnswer;
import com.wooriat.admin.domain.TbQuestion;
import com.wooriat.admin.domain.TbUser;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
public class AnswerDto {

    private Long aid;
    private Long qid;
    private String content;
    private TbQuestion questionInfo;
    private TbUser userInfo;
    private LocalDateTime cretDtm;

    public TbAnswer toEntity(){

        return TbAnswer.builder()
                .aid(this.aid)
                .content(this.content)
                .questionInfo(this.questionInfo.builder().qid(qid).build())
                .userInfo(this.userInfo)
                .build();
    }

    public AnswerDto(Optional<TbAnswer> tbQuestion) {
        if(tbQuestion.isPresent()) {
            this.aid = tbQuestion.get().getAid();
            this.content = tbQuestion.get().getContent();
            this.cretDtm = tbQuestion.get().getCretDtm();
            this.questionInfo = tbQuestion.get().getQuestionInfo();
            this.userInfo = tbQuestion.get().getUserInfo();
        }
    }

    public AnswerDto toDto(AnswerDto answerDto) {

        this.aid = answerDto.getAid();
        this.content = answerDto.getContent();
        this.questionInfo = answerDto.getQuestionInfo();
        this.userInfo = answerDto.getUserInfo();

        return answerDto;
    }
}
