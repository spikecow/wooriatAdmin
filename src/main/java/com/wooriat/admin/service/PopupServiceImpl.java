package com.wooriat.admin.service;

import com.wooriat.admin.common.exception.NotExistDataException;
import com.wooriat.admin.domain.TbPopup;
import com.wooriat.admin.dto.PopupDto;
import com.wooriat.admin.repository.PopupRepository;
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
public class PopupServiceImpl implements PopupService{

    private final PopupRepository popupRepository;

    @Override
    public Page<TbPopup> getList(Map<String, Object> params, Pageable pageable) {

        String popupTitle = (String)params.get("searchWord");
        Page<TbPopup> popupList;

        if(popupTitle != null && !popupTitle.equals("")){
            popupList = popupRepository.findByPopupTitleLike(popupTitle, pageable);
        }else{
            popupList = popupRepository.findAll(pageable);
        }

        return popupList;
    }

    @Override
    @Transactional
    public TbPopup insert(MultipartHttpServletRequest req, PopupDto popupDto) throws IOException, ServletException {
        return popupRepository.save(popupDto.toEntity());
    }

    @Override
    @Transactional
    public TbPopup update(PopupDto popupDto) {
        PopupDto dto = new PopupDto(popupRepository.findById(popupDto.getPopupId()));
        dto.toDto(dto);
        return popupRepository.save(dto.toEntity());
    }

    @Override
    public PopupDto getDetail(Long id) {
        return new PopupDto(popupRepository.findById(id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<TbPopup> byId = popupRepository.findById(id);
        if(!byId.isPresent()){
            throw new NotExistDataException("존재하지 않는 데이터 입니다.");
        }
        popupRepository.deleteById(byId.get().getPopupId());
    }
}
