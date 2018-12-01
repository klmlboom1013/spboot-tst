package com.lhs.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lhs.domain.User;
import com.lhs.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("")
	public String create(Model model, User data) {
		model.addAttribute("userId",data.getUserId());
		model.addAttribute("name",data.getName());
		model.addAttribute("email",data.getEmail());
		userRepository.save(data);
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model, User data) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
}
