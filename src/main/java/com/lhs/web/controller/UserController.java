package com.lhs.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.lhs.web.domainForm.UserForm;

@Controller
public class UserController {
	
	@Autowired
	private HttpServletRequest request;

	@PostMapping("/create")
	public String create(UserForm data, Model model) {
		model.addAttribute("userId",data.getUserId());
		model.addAttribute("name",data.getName());
		model.addAttribute("email",data.getEmail());
		
		return "/create";
	}
}
