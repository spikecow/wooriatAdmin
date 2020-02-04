package com.wooriat.admin.dto;

import com.wooriat.admin.domain.TbPopup;
import com.wooriat.admin.domain.TbUser;
import lombok.Data;
import org.apache.commons.lang.StringEscapeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Data
public class PopupDto {
    private Long popupId;
    private String popupTitle;
    private String popupType;
    private String popupText;
    private String popupImg;
    private LocalDateTime popupStrtDt;
    private LocalDateTime popupEndDt;
    private String popupLnkUrl;
    private LocalDateTime cretDtm;
    private LocalDateTime mdfyDtm;
    private TbUser userInfo;
    private String startDate, startHour, startMinute;
    private String endDate, endHour, endMinute;

    public TbPopup toEntity(){

        this.startDate = startDate.replaceAll("-","");
        this.endDate = endDate.replaceAll("-","");

        return TbPopup.builder()
                .popupId(this.popupId)
                .popupTitle(this.popupTitle)
                .popupType(this.popupType)
                .popupText(this.popupText)
                .popupImg(this.popupImg)
                .popupLnkUrl(this.popupLnkUrl)
                .popupStrtDt(LocalDateTime.of( Integer.parseInt(this.startDate.substring(0,4)), Integer.parseInt(this.startDate.substring(4,6)), Integer.parseInt(this.startDate.substring(6,8)), Integer.parseInt(this.startHour), Integer.parseInt(this.startMinute), 0, 0))
                .popupEndDt(LocalDateTime.of( Integer.parseInt(this.endDate.substring(0,4)), Integer.parseInt(this.endDate.substring(4,6)), Integer.parseInt(this.endDate.substring(6,8)), Integer.parseInt(this.endHour), Integer.parseInt(this.endMinute), 0, 0))
                .userInfo(this.userInfo)
                .build();
    }

    public PopupDto(Optional<TbPopup> tbPopup) {
    
        if(tbPopup.isPresent()) {
            
            this.popupId = tbPopup.get().getPopupId();
            this.popupTitle = tbPopup.get().getPopupTitle();
            this.popupType = tbPopup.get().getPopupType();
            this.popupText = StringEscapeUtils.unescapeHtml(tbPopup.get().getPopupText());
            this.popupImg = tbPopup.get().getPopupImg();
            this.popupLnkUrl = tbPopup.get().getPopupLnkUrl();
            this.popupStrtDt = tbPopup.get().getPopupStrtDt();
            this.popupEndDt = tbPopup.get().getPopupEndDt();
            this.cretDtm = tbPopup.get().getCretDtm();
            this.mdfyDtm = tbPopup.get().getMdfyDtm();
            this.userInfo = tbPopup.get().getUserInfo();
            this.startDate = tbPopup.get().getPopupStrtDt().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            this.startHour = tbPopup.get().getPopupStrtDt().format(DateTimeFormatter.ofPattern("HH"));
            this.startMinute = tbPopup.get().getPopupStrtDt().format(DateTimeFormatter.ofPattern("mm"));
            this.endDate = tbPopup.get().getPopupEndDt().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            this.endHour = tbPopup.get().getPopupEndDt().format(DateTimeFormatter.ofPattern("HH"));
            this.endMinute = tbPopup.get().getPopupEndDt().format(DateTimeFormatter.ofPattern("mm"));
        }
    }

    public PopupDto toDto(PopupDto popupDto) {

        this.popupId = popupDto.getPopupId();
        this.popupTitle = popupDto.getPopupTitle();
        this.popupType = popupDto.getPopupType();
        this.popupText = popupDto.getPopupText();
        this.popupImg = popupDto.getPopupImg();
        this.popupLnkUrl = popupDto.getPopupLnkUrl();
        this.popupStrtDt = popupDto.getPopupStrtDt();
        this.popupEndDt = popupDto.getPopupEndDt();
        this.userInfo = popupDto.getUserInfo();
        this.startDate = popupDto.getStartDate();
        this.startHour = popupDto.getStartHour();
        this.startMinute = popupDto.getStartMinute();
        this.endDate = popupDto.getEndDate();
        this.endHour = popupDto.getEndHour();
        this.endMinute = popupDto.getEndMinute();

        return popupDto;
    }
}
