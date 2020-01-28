package com.wooriat.admin.dto;

import com.wooriat.admin.common.enums.type.AuthCd;
import com.wooriat.admin.domain.TbMenu;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class SessionVo {

    private String userId;
    private String userPwd;
    private String userNm;
    private String deptNm;
    private String email;
    private String authCd;
    private LocalDateTime cretDtm;
    private LocalDateTime lastLoginDtm;

    private String jSessionId;

    private String returnUrl;

}
