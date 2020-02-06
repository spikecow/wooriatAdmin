package com.wooriat.admin.repository;

import com.wooriat.admin.domain.TbQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface QuestionRepository extends JpaRepository<TbQuestion,Long>, QuerydslPredicateExecutor<TbQuestion> {

}
