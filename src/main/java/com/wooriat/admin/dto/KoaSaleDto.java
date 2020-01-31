package com.wooriat.admin.dto;

import com.wooriat.admin.domain.KoaSale;
import com.wooriat.admin.domain.TbUser;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Data
public class KoaSaleDto {

    private Long saleId;
    private String bunName; // 사업명
    private String address; // 주소명
    private String etcAddress; // 기타 주소
    private String bizType; // 분양방식
    private String bizCase; // 사업종류
    private String progress1; // 가설공사 공정률
    private String progress2; // 건축공사 공정률
    private String progress3; // 토목공사 공정률
    private String progress4; // 전기공사 공정률
    private String progress5; // 설비공사 공정률
    private String progress6; // 전체 공정률
    private String reqTel; // 연락처 분양문의
    private String scale; // 규모
    private String totalSedae; // 총세대수
    private String bunSedae; // 분양세대수
    private String pyung; // 연면적
    private String construction; // 구조
    private LocalDateTime bunDate; // 분양시기
    private String bunDateInput;
    private LocalDateTime ipDate; // 입주시기
    private String ipDateInput;
    private String landArea; // 대지면적
    private String constArea; // 건축면적
    private String yungPer; // 용적률
    private String gunPer; // 건폐율
    private String parkn; // 주차대수
    private String siOffice; // 위탁사
    private String sgOffice; // 시공사
    private String bankOffice; // 금융사
    private String bunType; // 분양방식
    private String status; // 사업상태
    private String hotWay; // 난방방식
    private String imgTitle1; // 리스트용 이미지 제목
    private String imgTitle2; // 조감도 이미지 제목
    private String nPhoto1; // 리스트용 이미지 파일
    private String nPhoto2; // 조감도 이미지 파일
    private String team; // 담당팀
    private String homePage; // 사업자홈페이지
    private String sigongHomePage; // 시행사홈페이지
    private String constHomePage; // 건설사 홈페이지
    private LocalDateTime regDate; // 등록일
    private Long viewCount = 0L; // 조회수
    private String memo; // 특징 및 기타 내용
    private TbUser userInfo;

    public KoaSale toEntity(){

        if(this.bunDateInput != null && !this.bunDateInput.isEmpty()){
            this.bunDate = LocalDateTime.parse(this.bunDateInput+ " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        if(this.ipDateInput != null && !this.ipDateInput.isEmpty()){
            this.ipDate = LocalDateTime.parse(this.ipDateInput+ " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        return KoaSale.builder()
                .saleId(this.saleId)
                .bunName(this.bunName)
                .address(this.address)
                .etcAddress(this.etcAddress)
                .bizType(this.bizType)
                .bizCase(this.bizCase)
                .progress1(this.progress1)
                .progress2(this.progress2)
                .progress3(this.progress3)
                .progress4(this.progress4)
                .progress5(this.progress5)
                .progress6(this.progress6)
                .reqTel(this.reqTel)
                .scale(this.scale)
                .totalSedae(this.totalSedae)
                .bunSedae(this.bunSedae)
                .pyung(this.pyung)
                .construction(this.construction)
                .bunDate(this.bunDate)
                .ipDate(this.ipDate)
                .landArea(this.landArea)
                .constArea(this.constArea)
                .yungPer(this.yungPer)
                .gunPer(this.gunPer)
                .parkn(this.parkn)
                .siOffice(this.siOffice)
                .sgOffice(this.sgOffice)
                .bankOffice(this.bankOffice)
                .bunType(this.bunType)
                .status(this.status)
                .hotWay(this.hotWay)
                .imgTitle1(this.imgTitle1)
                .imgTitle2(this.imgTitle2)
                .nPhoto1(this.nPhoto1)
                .nPhoto2(this.nPhoto2)
                .team(this.team)
                .homePage(this.homePage)
                .sigongHomePage(this.sigongHomePage)
                .constHomePage(this.constHomePage)
                .viewCount(this.viewCount)
                .memo(this.memo)
                .userInfo(this.userInfo)
                .build();
    }

    public KoaSaleDto(Optional<KoaSale> koaSale) {
        if(koaSale.isPresent()) {
            this.saleId=koaSale.get().getSaleId();
            this.bunName=koaSale.get().getBunName();
            this.address=koaSale.get().getAddress();
            this.etcAddress= koaSale.get().getEtcAddress();
            this.bizType= koaSale.get().getBizType();
            this.bizCase= koaSale.get().getBizCase();
            this.progress1=koaSale.get().getProgress1();
            this.progress2=koaSale.get().getProgress2();
            this.progress3=koaSale.get().getProgress3();
            this.progress4=koaSale.get().getProgress4();
            this.progress5=koaSale.get().getProgress5();
            this.progress6=koaSale.get().getProgress6();
            this.reqTel=koaSale.get().getReqTel();
            this.scale= koaSale.get().getScale();
            this.totalSedae=koaSale.get().getTotalSedae();
            this.bunSedae= koaSale.get().getBunSedae();
            this.pyung= koaSale.get().getPyung();
            this.construction=koaSale.get().getConstruction();
            this.bunDate= koaSale.get().getBunDate();
            this.ipDate= koaSale.get().getIpDate();
            this.landArea=koaSale.get().getLandArea();
            this.constArea= koaSale.get().getConstArea();
            this.yungPer=koaSale.get().getYungPer();
            this.gunPer=koaSale.get().getGunPer();
            this.parkn= koaSale.get().getParkn();
            this.siOffice=koaSale.get().getSiOffice();
            this.sgOffice=koaSale.get().getSgOffice();
            this.bankOffice=koaSale.get().getBankOffice();
            this.bunType=koaSale.get().getBunType();
            this.status= koaSale.get().getStatus();
            this.hotWay= koaSale.get().getHotWay();
            this.imgTitle1= koaSale.get().getImgTitle1();
            this.imgTitle2= koaSale.get().getImgTitle2();
            this.nPhoto1=koaSale.get().getNPhoto1();
            this.nPhoto2=koaSale.get().getNPhoto2();
            this.team=koaSale.get().getTeam();
            this.homePage= koaSale.get().getHomePage();
            this.sigongHomePage=koaSale.get().getSigongHomePage();
            this.constHomePage= koaSale.get().getConstHomePage();
            this.regDate=koaSale.get().getRegDate();
            this.viewCount= koaSale.get().getViewCount();
            this.memo=koaSale.get().getMemo();
            this.userInfo=koaSale.get().getUserInfo();
            if(koaSale.get().getBunDate() != null){
                this.bunDateInput=koaSale.get().getBunDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            if(koaSale.get().getIpDate() != null){
                this.ipDateInput=koaSale.get().getIpDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }

        }
    }

    public KoaSaleDto toDto(KoaSaleDto koaSaleDto) {
        this.saleId=koaSaleDto.getSaleId();
        this.bunName=koaSaleDto.getBunName();
        this.address=koaSaleDto.getAddress();
        this.etcAddress= koaSaleDto.getEtcAddress();
        this.bizType= koaSaleDto.getBizType();
        this.bizCase= koaSaleDto.getBizCase();
        this.progress1=koaSaleDto.getProgress1();
        this.progress2=koaSaleDto.getProgress2();
        this.progress3=koaSaleDto.getProgress3();
        this.progress4=koaSaleDto.getProgress4();
        this.progress5=koaSaleDto.getProgress5();
        this.progress6=koaSaleDto.getProgress6();
        this.reqTel=koaSaleDto.getReqTel();
        this.scale= koaSaleDto.getScale();
        this.totalSedae=koaSaleDto.getTotalSedae();
        this.bunSedae= koaSaleDto.getBunSedae();
        this.pyung= koaSaleDto.getPyung();
        this.construction=koaSaleDto.getConstruction();
        this.bunDate= koaSaleDto.getBunDate();
        this.ipDate= koaSaleDto.getIpDate();
        this.landArea=koaSaleDto.getLandArea();
        this.constArea= koaSaleDto.getConstArea();
        this.yungPer=koaSaleDto.getYungPer();
        this.gunPer=koaSaleDto.getGunPer();
        this.parkn= koaSaleDto.getParkn();
        this.siOffice=koaSaleDto.getSiOffice();
        this.sgOffice=koaSaleDto.getSgOffice();
        this.bankOffice=koaSaleDto.getBankOffice();
        this.bunType=koaSaleDto.getBunType();
        this.status= koaSaleDto.getStatus();
        this.hotWay= koaSaleDto.getHotWay();
        this.imgTitle1= koaSaleDto.getImgTitle1();
        this.imgTitle2= koaSaleDto.getImgTitle2();
        this.nPhoto1=koaSaleDto.getNPhoto1();
        this.nPhoto2=koaSaleDto.getNPhoto2();
        this.team=koaSaleDto.getTeam();
        this.homePage= koaSaleDto.getHomePage();
        this.sigongHomePage=koaSaleDto.getSigongHomePage();
        this.constHomePage= koaSaleDto.getConstHomePage();
        this.regDate=koaSaleDto.getRegDate();
        this.viewCount= koaSaleDto.getViewCount();
        this.memo=koaSaleDto.getMemo();
        this.userInfo=koaSaleDto.getUserInfo();
        this.bunDateInput=koaSaleDto.getBunDateInput();
        this.ipDateInput=koaSaleDto.getIpDateInput();

        return koaSaleDto;
    }
}
