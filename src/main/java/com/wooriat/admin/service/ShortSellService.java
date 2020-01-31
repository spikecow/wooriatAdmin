package com.wooriat.admin.service;


import com.wooriat.admin.domain.ShotSell;
import com.wooriat.admin.dto.ShotSellDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

public interface ShortSellService {

    public Page<ShotSell> getList(Map<String, Object> params, Pageable pageable);
    public ShotSell insert(MultipartHttpServletRequest req, ShotSellDto shotSellDto) throws IOException, ServletException;
    public ShotSell update(ShotSellDto shotSellDto);
    public ShotSellDto getDetail(Long id);
    public void delete(Long id);

}
