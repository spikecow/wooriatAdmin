package com.wooriat.admin.repository;

import com.wooriat.admin.domain.ShotSell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ShortSellRepository extends JpaRepository<ShotSell, Long>, QuerydslPredicateExecutor<ShotSell> {

}
