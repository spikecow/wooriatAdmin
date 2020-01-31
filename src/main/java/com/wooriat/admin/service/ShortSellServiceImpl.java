package com.wooriat.admin.service;


import com.querydsl.core.BooleanBuilder;
import com.wooriat.admin.common.exception.NotExistDataException;
import com.wooriat.admin.domain.QShotSell;
import com.wooriat.admin.domain.ShotSell;
import com.wooriat.admin.dto.ShotSellDto;
import com.wooriat.admin.repository.ShortSellRepository;
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
public class ShortSellServiceImpl implements ShortSellService  {

    private final ShortSellRepository shortSellRepository;

    @Override
    public Page<ShotSell> getList(Map<String, Object> params, Pageable pageable) {

        QShotSell shotSell = QShotSell.shotSell;
        BooleanBuilder builder = new BooleanBuilder();

        String newsTitle = (String)params.get("searchWord");
        String sortStatus = (String)params.get("sortStatus");

        if(newsTitle != null && !newsTitle.equals("")){
            builder.and(shotSell.newsTitle.like("%"+newsTitle+"%"));
        }
        if(sortStatus != null && !sortStatus.equals("")){
            builder.and(shotSell.sortStatus.eq(sortStatus));
        }

        return shortSellRepository.findAll(builder, pageable);
    }

    @Override
    @Transactional
    public ShotSell insert(MultipartHttpServletRequest req, ShotSellDto shotSellDto) throws IOException, ServletException {

        return shortSellRepository.save(shotSellDto.toEntity());
    }

    @Override
    @Transactional
    public ShotSell update(ShotSellDto shotSellDto) {

        ShotSellDto dto = new ShotSellDto(shortSellRepository.findById(shotSellDto.getSellId()));
        dto.toDto(shotSellDto);
        return shortSellRepository.save(dto.toEntity());
    }

    @Override
    public ShotSellDto getDetail(Long id) {

        return new ShotSellDto(shortSellRepository.findById(id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<ShotSell> byId = shortSellRepository.findById(id);
        if(!byId.isPresent()){
            throw new NotExistDataException("존재하지 않는 데이터 입니다.");
        }
        shortSellRepository.deleteById(byId.get().getSellId());
    }

}
