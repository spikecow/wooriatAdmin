package com.wooriat.admin.service;


import com.wooriat.admin.domain.TbUser;
import com.wooriat.admin.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface UserService {

    public TbUser insert(UserDto userDto) ;
    public TbUser update(UserDto userDto) ;
    public UserDto getDetail(Long id);
    public Page<TbUser> getUserList(String searchWord, Pageable pageable);
    public Map menuList();
    public void delete(Long id);
    public TbUser passwordCheck(String userId, String userPwd);
}
