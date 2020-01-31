package com.wooriat.admin.repository;

import com.wooriat.admin.domain.KoaSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SaleRepository extends JpaRepository<KoaSale, Long>, QuerydslPredicateExecutor<KoaSale> {

}
