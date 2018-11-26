package com.lhs.web.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lhs.web.domainForm.UserForm;

@Controller
public class UserController {
	
	private static final ArrayList<UserForm> userList = new ArrayList<UserForm>();
	
	@PostMapping("/create")
	public String create(Model model, UserForm data) {
		model.addAttribute("userId",data.getUserId());
		model.addAttribute("name",data.getName());
		model.addAttribute("email",data.getEmail());
		userList.add(data);
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model, UserForm data) {
		model.addAttribute("users", userList);
		return "list";
	}
}
