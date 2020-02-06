package com.wooriat.admin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_answer")
public class TbAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aid")
    private Long aid;

    @Column(name = "content", length = 5000)
    private String content;

    @CreationTimestamp
    @Column(name = "cret_dtm", updatable = false)
    private LocalDateTime cretDtm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="qid")
    private TbQuestion questionInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="uid")
    private TbUser userInfo;

    @Builder
    public TbAnswer(Long aid, String content, TbQuestion questionInfo, TbUser userInfo){
        this.aid = aid;
        this.content = content;
        this.questionInfo = questionInfo;
        this.userInfo = userInfo;
    }

}
