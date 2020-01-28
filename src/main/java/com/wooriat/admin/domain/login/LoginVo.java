package com.wooriat.admin.domain.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@Setter
@ToString
public class LoginVo {

    private String userId;
    private String userPw;
    private String authDivCd;
    private String jSessionId;

    private String returnUrl;
}

