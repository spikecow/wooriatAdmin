package com.wooriat.admin.dto;

import com.wooriat.admin.domain.ShotSell;
import com.wooriat.admin.domain.TbUser;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Data
public class ShotSellDto {

    private Long sellId;
    private LocalDateTime regDate;
    private String regDateInput = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private String newsTitle;
    private Integer clickCnt;
    private String sortStatus;
    private String insertFile1;
    private String insertFile2;
    private String insertFile3;
    private String insertFile4;
    private String insertFile5;
    private TbUser userInfo;

    public ShotSell toEntity(){

        if(this.clickCnt == null){
            this.clickCnt = 0;
        }
        return ShotSell.builder()
                .sellId(this.sellId)
                .regDate(LocalDateTime.parse(this.regDateInput+ " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .newsTitle(this.newsTitle)
                .clickCnt(this.clickCnt)
                .sortStatus(this.sortStatus)
                .insertFile1(this.insertFile1)
                .insertFile2(this.insertFile2)
                .insertFile3(this.insertFile3)
                .insertFile4(this.insertFile4)
                .insertFile5(this.insertFile5)
                .userInfo(this.userInfo)
                .build();
    }

    public ShotSellDto(){

    }

    public ShotSellDto(Optional<ShotSell> shotSell) {
        if(shotSell.isPresent()) {
            this.sellId = shotSell.get().getSellId();
            this.regDate = shotSell.get().getRegDate();
            this.newsTitle = shotSell.get().getNewsTitle();
            this.clickCnt = shotSell.get().getClickCnt();
            this.sortStatus = shotSell.get().getSortStatus();
            this.insertFile1 = shotSell.get().getInsertFile1();
            this.insertFile2 = shotSell.get().getInsertFile2();
            this.insertFile3 = shotSell.get().getInsertFile3();
            this.insertFile4 = shotSell.get().getInsertFile4();
            this.insertFile5 = shotSell.get().getInsertFile5();
            this.userInfo = shotSell.get().getUserInfo();
            this.regDateInput = shotSell.get().getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    public ShotSellDto toDto(ShotSellDto shotSellDto) {

        this.sellId = shotSellDto.getSellId();
        this.regDate = shotSellDto.getRegDate();
        this.newsTitle = shotSellDto.getNewsTitle();
        this.clickCnt = shotSellDto.getClickCnt();
        this.sortStatus = shotSellDto.getSortStatus();
        this.insertFile1 = shotSellDto.getInsertFile1();
        this.insertFile2 = shotSellDto.getInsertFile2();
        this.insertFile3 = shotSellDto.getInsertFile3();
        this.insertFile4 = shotSellDto.getInsertFile4();
        this.insertFile5 = shotSellDto.getInsertFile5();
        this.userInfo = shotSellDto.getUserInfo();
        this.regDateInput = shotSellDto.getRegDateInput();

        return shotSellDto;
    }
}
