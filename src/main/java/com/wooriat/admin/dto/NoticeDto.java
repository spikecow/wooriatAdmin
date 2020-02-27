package com.wooriat.admin.dto;

import com.wooriat.admin.domain.TbNotice;
import com.wooriat.admin.domain.TbUser;
import lombok.Data;
import org.apache.commons.lang.StringEscapeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Data
public class NoticeDto {
    private Long seqNo;
    private String menuCd;
    private String typeCd;
    private String title;
    private String content;
    private LocalDateTime strtDt;
    private LocalDateTime endDt;
    private LocalDateTime cretDtm;
    private LocalDateTime mdfyDtm;
    private String img;
    private LocalDateTime regDate;
    private String regDateInput = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private TbUser userInfo;
    private String startDate;
    private String endDate;

    public TbNotice toEntity(){

        if(this.regDateInput != null && !this.regDateInput.isEmpty()){
            this.regDate = LocalDateTime.parse(this.regDateInput+ " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        if(this.startDate != null && !this.startDate.isEmpty()){
            this.strtDt = LocalDateTime.parse(this.startDate+ " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        if(this.endDate != null && !this.endDate.isEmpty()){
            this.endDt = LocalDateTime.parse(this.endDate+ " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        return TbNotice.builder()
                .seqNo(this.seqNo)
                .menuCd(this.menuCd)
                .typeCd(this.typeCd)
                .title(this.title)
                .content(this.content)
                .strtDt(this.strtDt)
                .endDt(this.endDt)
                .cretDtm(this.cretDtm)
                .mdfyDtm(this.mdfyDtm)
                .img(this.img)
                .regDate(this.regDate)
                .userInfo(this.userInfo)
                .build();
    }

    public NoticeDto(){

    }

    public NoticeDto(Optional<TbNotice> tbNotice) {
    
        if(tbNotice.isPresent()) {
            
            this.seqNo = tbNotice.get().getSeqNo();
            this.menuCd = tbNotice.get().getMenuCd();
            this.typeCd = tbNotice.get().getTypeCd();
            this.title = tbNotice.get().getTitle();
            this.content = StringEscapeUtils.unescapeHtml(tbNotice.get().getContent());
            this.img = tbNotice.get().getImg();
            this.strtDt = tbNotice.get().getStrtDt();
            this.endDt = tbNotice.get().getEndDt();
            this.cretDtm = tbNotice.get().getCretDtm();
            this.mdfyDtm = tbNotice.get().getMdfyDtm();
            this.userInfo = tbNotice.get().getUserInfo();
            if(tbNotice.get().getStrtDt() != null){
                this.startDate=tbNotice.get().getStrtDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            if(tbNotice.get().getEndDt() != null){
                this.endDate=tbNotice.get().getEndDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            if(tbNotice.get().getRegDate() != null){
                this.regDateInput=tbNotice.get().getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        }
    }

    public NoticeDto toDto(NoticeDto noticeDto) {

        this.seqNo = noticeDto.getSeqNo();
        this.menuCd = noticeDto.getMenuCd();
        this.typeCd = noticeDto.getTypeCd();
        this.title = noticeDto.getTitle();
        this.content = noticeDto.getContent();
        this.img = noticeDto.getImg();
        this.strtDt = noticeDto.getStrtDt();
        this.endDt = noticeDto.getEndDt();
        this.startDate = noticeDto.getStartDate();
        this.endDate = noticeDto.getEndDate();
        this.regDate = noticeDto.getRegDate();
        this.regDateInput = noticeDto.getRegDateInput();
        this.userInfo = noticeDto.getUserInfo();

        return noticeDto;
    }
}
