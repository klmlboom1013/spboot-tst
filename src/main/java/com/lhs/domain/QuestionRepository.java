package com.lhs.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * QuestionRepository
 */
public interface QuestionRepository  extends JpaRepository<Question, Long> { 

}