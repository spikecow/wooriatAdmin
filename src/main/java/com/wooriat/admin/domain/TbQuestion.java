package com.wooriat.admin.domain;

import com.wooriat.admin.common.enums.type.QuestionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_question")
public class TbQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qid")
    private Long qid;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "tel", length = 20)
    private String tel;

    @Column(name = "title", length = 256)
    private String title;

    @Column(name = "content", length = 5000)
    private String content;

    @CreationTimestamp
    @Column(name = "cret_dtm", updatable = false)
    private LocalDateTime cretDtm;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="qid")
    private List<TbAnswer> answers = new ArrayList<>();

    @Builder
    private TbQuestion(Long qid, String name, String email, String tel, String title, String content, List<TbAnswer> answers){
        this.qid = qid;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.title = title;
        this.content = content;
        this.answers = answers;
    }
}
