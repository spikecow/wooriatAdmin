package com.wooriat.admin.dto;

import com.wooriat.admin.domain.TbMenu;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private List<Long> menuId = new ArrayList<>();

    private String jSessionId;

    private String returnUrl;

}
