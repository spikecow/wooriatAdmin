package com.wooriat.admin.service;


import com.wooriat.admin.domain.KoaSale;
import com.wooriat.admin.dto.KoaSaleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

public interface SaleService {

    public Page<KoaSale> getList(Map<String, Object> params, Pageable pageable);
    public KoaSale insert(KoaSaleDto koaSaleDto);
    public KoaSale update(KoaSaleDto koaSaleDto);
    public KoaSaleDto getDetail(Long saleId);
    public void delete(Long saleId);

}
