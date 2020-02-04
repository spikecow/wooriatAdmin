package com.wooriat.admin.repository;

import com.wooriat.admin.domain.TbPopup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopupRepository extends JpaRepository<TbPopup,Long> {
    public Page<TbPopup> findByPopupTitleLike(String sw, Pageable page);

}
