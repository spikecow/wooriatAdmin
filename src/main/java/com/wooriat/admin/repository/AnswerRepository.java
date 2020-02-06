package com.wooriat.admin.repository;

import com.wooriat.admin.domain.TbAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AnswerRepository extends JpaRepository<TbAnswer,Long>, QuerydslPredicateExecutor<TbAnswer> {

}
