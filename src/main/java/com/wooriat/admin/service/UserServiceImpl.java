package com.wooriat.admin.service;


import com.wooriat.admin.common.exception.NotExistDataException;
import com.wooriat.admin.domain.TbMenu;
import com.wooriat.admin.domain.TbUser;
import com.wooriat.admin.dto.UserDto;
import com.wooriat.admin.repository.MenuRepository;
import com.wooriat.admin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final MenuRepository menuRepository;

    @Override
    @Transactional
    public TbUser insert(UserDto userDto) {

        return userRepository.save(userDto.toEntity());
    }

    @Override
    @Transactional
    public TbUser update(UserDto userDto) {

        UserDto dto = new UserDto(userRepository.findById(userDto.getUid()));
        dto.toDto(userDto);
        return userRepository.save(dto.toEntity());
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Optional<TbUser> byId = userRepository.findById(id);
        if(!byId.isPresent()){
            throw new NotExistDataException("존재하지 않는 데이터 입니다.");
        }
        userRepository.deleteById(byId.get().getUid());
    }

    @Override
    public UserDto getDetail(Long id) {

        return new UserDto(userRepository.findById(id));
    }

    @Override
    public Map menuList(){
        List<TbMenu> list = menuRepository.findByUpperMenuIdIsNull();
        Map map = new HashMap();

        int index = 1;
        for(TbMenu menu:list){
            map.put("menuList"+index, menuRepository.findByUpperMenuIdOrderByMenuOrder(menu.getMenuId()));
            index++;
        }

        return map;
    }

    @Override
    public Page<TbUser> getUserList(String searchWord, Pageable pageable) {

        Page<TbUser> userPage = null;
        if(searchWord != null && !searchWord.equals("")){
            String sw = "%"+searchWord+"%";
            userPage = userRepository.findByUserNmLikeOrEmailLike(sw, sw, pageable);
        }else{
            userPage = userRepository.findAll(pageable);
        }

        return userPage;
    }
}
