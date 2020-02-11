package com.wooriat.admin.repository;

import com.wooriat.admin.domain.KoaSale;
import com.wooriat.admin.domain.TbNotice;
import com.wooriat.admin.domain.TbPopup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface NoticeRepository extends JpaRepository<TbNotice,Long>  {
    public Page<TbNotice> findByMenuCdAndTitleLikeOrContentLike(String mc, String tl, String sw, Pageable page);
    public Page<TbNotice> findByMenuCdAndTypeCdAndTitleLikeOrContentLike(String mc, String tp, String tl, String sw, Pageable page);

    public Page<TbNotice> findByMenuCdAndTypeCd(String mc, String tp, Pageable page);
    public Page<TbNotice> findByMenuCd(String mc, Pageable page);
}
