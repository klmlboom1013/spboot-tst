package com.lhs.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lhs.domain.Question;

/**
 * QuestionRepository
 */
public interface QuestionRepository  extends JpaRepository<Question, Long> {
	
}