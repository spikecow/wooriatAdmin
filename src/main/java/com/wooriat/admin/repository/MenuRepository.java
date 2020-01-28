package com.wooriat.admin.repository;

import com.wooriat.admin.domain.TbMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<TbMenu,Long> {
    public List<TbMenu> findByUpperMenuIdOrderByMenuOrder(Long upperMenuId);
    public List<TbMenu> findByUpperMenuIdIsNull();
}
