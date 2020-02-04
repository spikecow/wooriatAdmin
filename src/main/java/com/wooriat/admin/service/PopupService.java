package com.wooriat.admin.service;


import com.wooriat.admin.domain.TbPopup;
import com.wooriat.admin.dto.PopupDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

public interface PopupService {

    public Page<TbPopup> getList(Map<String, Object> params, Pageable pageable);
    public TbPopup insert(MultipartHttpServletRequest req, PopupDto popupDto) throws IOException, ServletException;
    public TbPopup update(PopupDto popupDto);
    public PopupDto getDetail(Long id);
    public void delete(Long id);

}
