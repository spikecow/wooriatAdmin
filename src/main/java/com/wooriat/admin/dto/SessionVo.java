package com.wooriat.admin.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SessionVo {

    private Long uid;
    private String userId;
    private String userNm;
    private String deptNm;
    private String email;
    private String authCd;
    private LocalDateTime cretDtm;
    private LocalDateTime lastLoginDtm;

    private String jSessionId;

    private String returnUrl;

}
