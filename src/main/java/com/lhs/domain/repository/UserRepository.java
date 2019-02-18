package com.lhs.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lhs.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUserId(String userId);
}
