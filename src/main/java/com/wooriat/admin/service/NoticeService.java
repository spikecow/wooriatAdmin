package com.wooriat.admin.service;


import com.wooriat.admin.domain.TbNotice;
import com.wooriat.admin.dto.NoticeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

public interface NoticeService {

    public Page<TbNotice> getList(Map<String, Object> params, Pageable pageable);
    public TbNotice insert(MultipartHttpServletRequest req, NoticeDto noticeDto) throws IOException, ServletException;
    public TbNotice update(NoticeDto noticeDto);
    public NoticeDto getDetail(Long id);
    public void delete(Long id);

}
