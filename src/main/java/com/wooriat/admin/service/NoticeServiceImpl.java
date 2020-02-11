package com.wooriat.admin.service;

import com.wooriat.admin.common.exception.NotExistDataException;
import com.wooriat.admin.domain.TbNotice;
import com.wooriat.admin.dto.NoticeDto;
import com.wooriat.admin.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;

    @Override
    public Page<TbNotice> getList(Map<String, Object> params, Pageable pageable) {

        String mc = (String)params.get("menuCd");
        String tp = (String)params.get("typeCd") == null ? "" : (String)params.get("typeCd");
        String sw = (String)params.get("searchWord") == null ? "" : (String)params.get("searchWord");

        Page<TbNotice> noticeList;

        if(!tp.equals("") && sw.equals("")){
            noticeList = noticeRepository.findByMenuCdAndTypeCd(mc, tp,  pageable);
        }
        else if(!tp.equals("") && !sw.equals("")) {
            noticeList = noticeRepository.findByMenuCdAndTypeCdAndTitleLikeOrContentLike(mc, tp, sw, sw, pageable);
        }
        else if(tp.equals("") && !sw.equals("")) {
            noticeList = noticeRepository.findByMenuCdAndTitleLikeOrContentLike(mc, sw, sw, pageable);
        }
        else{
            noticeList = noticeRepository.findByMenuCd(mc, pageable);
        }

        return noticeList;
    }

    @Override
    @Transactional
    public TbNotice insert(MultipartHttpServletRequest req, NoticeDto noticeDto) throws IOException, ServletException {
        return noticeRepository.save(noticeDto.toEntity());
    }

    @Override
    @Transactional
    public TbNotice update(NoticeDto noticeDto) {
        NoticeDto dto = new NoticeDto(noticeRepository.findById(noticeDto.getSeqNo()));
        dto.toDto(dto);
        return noticeRepository.save(dto.toEntity());
    }

    @Override
    public NoticeDto getDetail(Long id) {
        return new NoticeDto(noticeRepository.findById(id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<TbNotice> byId = noticeRepository.findById(id);
        if(!byId.isPresent()){
            throw new NotExistDataException("존재하지 않는 데이터 입니다.");
        }
        noticeRepository.deleteById(byId.get().getSeqNo());
    }
}
