package com.wooriat.admin.dto;

import com.wooriat.admin.common.enums.type.AuthCd;
import com.wooriat.admin.domain.TbMenu;
import com.wooriat.admin.domain.TbUser;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class UserDto {

    private Long uid;
    private String userId;
    private String userPwd;
    private String userNm;
    private String deptNm;
    private String email;
    private AuthCd authCd;
    private List<Long> menuId;
    private List<TbMenu> userMenus = new ArrayList<>();
    private LocalDateTime cretDtm;
    private LocalDateTime mdfyDtm;
    private LocalDateTime lastLoginDtm;

    public TbUser toEntity(){
        if(authCd == null){
            authCd = AuthCd.A;
        }
        if(menuId!= null && !menuId.isEmpty()){
            List<TbMenu> list = new ArrayList<>();
            for(Long menu_id: menuId){
                list.add(TbMenu.builder().menuId(menu_id).build());
            }
            this.userMenus = list;
        }
        return TbUser.builder()
                .uid(this.uid)
                .userId(this.userId)
                .userPwd(this.userPwd)
                .userNm(this.userNm)
                .email(this.email)
                .authCd(this.authCd)
                .userMenus(this.userMenus)
                .deptNm(this.deptNm)
                .lastLoginDtm(this.lastLoginDtm)
                .build();
    }

    public UserDto(Optional<TbUser> tbUser) {
        if(tbUser.isPresent()) {
            this.uid = tbUser.get().getUid();
            this.userId = tbUser.get().getUserId();
            this.userPwd = tbUser.get().getUserPwd();
            this.userNm = tbUser.get().getUserNm();
            this.deptNm = tbUser.get().getDeptNm();
            this.email = tbUser.get().getEmail();
            this.authCd = tbUser.get().getAuthCd();
            this.userMenus = tbUser.get().getUserMenus();
            this.cretDtm = tbUser.get().getCretDtm();
            this.mdfyDtm = tbUser.get().getMdfyDtm();
            this.lastLoginDtm = tbUser.get().getLastLoginDtm();
        }
    }

    public UserDto toDto(UserDto userDto) {
        if(userDto.getUid() != null){                                                this.uid = userDto.getUid();                      }
        if(userDto.getUserId() != null){                                            this.userId = userDto.getUserId();              }
        if(userDto.getUserPwd() != null && !userDto.getUserPwd().equals("")){       this.userPwd = userDto.getUserPwd();            }
        if(userDto.getUserNm() != null){                                            this.userNm = userDto.getUserNm();              }
        if(userDto.getDeptNm() != null){                                            this.deptNm = userDto.getDeptNm();              }
        if(userDto.getEmail() != null){                                             this.email = userDto.getEmail();                }
        if(userDto.getAuthCd() != null){                                            this.authCd = userDto.getAuthCd();              }
        if(userDto.getMenuId() != null && !userDto.getMenuId().isEmpty()){
            List<TbMenu> list = new ArrayList<>();
            for(Long menu_id: userDto.getMenuId()){
                list.add(TbMenu.builder().menuId(menu_id).build());
            }
            this.userMenus = list;
        }

        return userDto;
    }
}
