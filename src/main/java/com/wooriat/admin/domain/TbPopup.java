package com.wooriat.admin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_popup")
public class TbPopup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "popup_id")
    private Long popupId;

    @Column(name = "popup_title", length = 256)
    private String popupTitle;

    @Column(name = "popup_type", length = 1)
    private String popupType;

    @Column(name = "popup_text", length = 5000)
    private String popupText;

    @Column(name = "popup_img", length = 500)
    private String popupImg;

    @Column(name = "popup_strt_dt")
    private LocalDateTime popupStrtDt;

    @Column(name = "popup_end_dt")
    private LocalDateTime popupEndDt;

    @Column(name = "popup_lnk_url", length = 500)
    private String popupLnkUrl;

    @CreationTimestamp
    @Column(name = "cret_dtm", updatable = false)
    private LocalDateTime cretDtm;

    @UpdateTimestamp
    @Column(name = "mdfy_dtm")
    private LocalDateTime mdfyDtm;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="uid")
    private TbUser userInfo;

    @Builder
    public TbPopup(Long popupId,
                   String popupTitle,
                   String popupType,
                   String popupText,
                   String popupImg,
                   LocalDateTime popupStrtDt,
                   LocalDateTime popupEndDt,
                   String popupLnkUrl,
                   TbUser userInfo){

        this.popupId = popupId;
        this.popupTitle = popupTitle;
        this.popupType = popupType;
        this.popupText = popupText;
        this.popupImg = popupImg;
        this.popupLnkUrl = popupLnkUrl;
        this.popupStrtDt = popupStrtDt;
        this.popupEndDt = popupEndDt;
        this.userInfo = userInfo;

    }

}
