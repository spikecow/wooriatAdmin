package com.wooriat.admin.service;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.wooriat.admin.common.exception.NotExistDataException;
import com.wooriat.admin.domain.KoaSale;
import com.wooriat.admin.domain.QKoaSale;
import com.wooriat.admin.dto.KoaSaleDto;
import com.wooriat.admin.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService  {

    private final SaleRepository saleRepository;

    @Override
    public Page<KoaSale> getList(Map<String, Object> params, Pageable pageable) {

        QKoaSale koaSale = QKoaSale.koaSale;
        BooleanBuilder builder = new BooleanBuilder();

        String bunName = (String)params.get("searchWord");
        String bizCase = (String)params.get("bizCase");
        String progress6 = (String)params.get("progress6");
        String status = (String)params.get("status");

        if(bunName != null && !bunName.equals("")){
            builder.and(koaSale.bunName.like("%"+bunName+"%"));
        }
        if(bizCase != null && !bizCase.equals("")){
            builder.and(koaSale.bizCase.eq(bizCase));
        }
        if(progress6 != null && !progress6.equals("")){
            NumberTemplate<Double> castProgress6 = Expressions.numberTemplate(Double.class,
                    "CAST({0} AS float )",
                    koaSale.progress6);
            if(progress6.equals("50")){
                builder.and(castProgress6.lt(Double.parseDouble(progress6))); // 공정률 < 50
            }else if(progress6.equals("90")){
                builder.and(castProgress6.lt(Double.parseDouble("100")).and(castProgress6.goe(Double.parseDouble("50")))); // 100 < 공정률 >= 50
            }else{
                builder.and(koaSale.progress6.eq(progress6)); // 공정률 == 100
            }
        }
        if(status != null && !status.equals("")){
            builder.and(koaSale.status.eq(status));
        }

        return saleRepository.findAll(builder, pageable);
    }

    @Override
    @Transactional
    public KoaSale insert(KoaSaleDto koaSaleDto) {

        return saleRepository.save(koaSaleDto.toEntity());
    }

    @Override
    @Transactional
    public KoaSale update(KoaSaleDto koaSaleDto) {

        KoaSaleDto dto = new KoaSaleDto(saleRepository.findById(koaSaleDto.getSaleId()));
        dto.toDto(koaSaleDto);
        return saleRepository.save(dto.toEntity());
    }

    @Override
    public KoaSaleDto getDetail(Long id) {

        return new KoaSaleDto(saleRepository.findById(id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Optional<KoaSale> byId = saleRepository.findById(id);
        if(!byId.isPresent()){
            throw new NotExistDataException("존재하지 않는 데이터 입니다.");
        }
        saleRepository.deleteById(byId.get().getSaleId());
    }
}
