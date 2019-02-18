package com.lhs.web.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.domain.User;
import com.lhs.domain.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{id}")
	public User shwo(@PathVariable Long id) {
		return userRepository.findById(id).get();
	}
}
