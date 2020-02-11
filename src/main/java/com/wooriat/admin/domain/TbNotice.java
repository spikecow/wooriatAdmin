package com.wooriat.admin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_notice")
public class TbNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_no")
    private Long seqNo;

    /*
        M-경영현황
        C-회사소식
        P-포토소식
        S-사회공헌
     */
    @Column(name = "menu_cd", length = 1, nullable = false)
    private String menuCd; // 메뉴구분
    /*
        01-수시공시
        02-영업보고
        03-영업순자본비율
        04-감사보고서
        05-(구)경영공시
        06-약관공시
     */
    @Column(name = "type_cd", length = 4, columnDefinition ="char")
    private String typeCd; // 경영현황에서 사용하는 코드값

    @Column(name = "title", length = 1000)
    private String title; // 제목

    @Column(name = "content", length = 10485760)
    private String content; // 내용

    @Column(name = "strt_dt")
    private LocalDateTime strtDt;

    @Column(name = "end_dt")
    private LocalDateTime endDt;

    @CreationTimestamp
    @Column(name = "cret_dtm", updatable = false)
    private LocalDateTime cretDtm;

    @UpdateTimestamp
    @Column(name = "mdfy_dtm")
    private LocalDateTime mdfyDtm;

    @Column(name = "img", length = 1000)
    private String img; // 리스트용 이미지 제목

    @Column(name = "reg_date")
    @CreationTimestamp
    private LocalDateTime regDate; // 등록일

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="uid")
    private TbUser userInfo;

    @Builder
    public TbNotice(Long seqNo, String menuCd, String typeCd, String title, String content, LocalDateTime strtDt, LocalDateTime endDt, LocalDateTime cretDtm, LocalDateTime mdfyDtm, String img, LocalDateTime regDate, TbUser userInfo) {
        this.seqNo = seqNo;
        this.menuCd = menuCd;
        this.typeCd = typeCd;
        this.title = title;
        this.content = content;
        this.strtDt = strtDt;
        this.endDt = endDt;
        this.cretDtm = cretDtm;
        this.mdfyDtm = mdfyDtm;
        this.img = img;
        this.regDate = regDate;
        this.userInfo = userInfo;
    }
}
