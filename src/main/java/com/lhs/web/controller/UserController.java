package com.lhs.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import com.lhs.domain.User;
import com.lhs.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HttpSession session;

	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}

	@PostMapping("")
	public String create(Model model, User data) {
		model.addAttribute("userId", data.getUserId());
		model.addAttribute("name", data.getName());
		model.addAttribute("email", data.getEmail());
		userRepository.save(data);
		return "redirect:/users";
	}

	@GetMapping("")
	public String list(Model model, User data) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}

	@GetMapping("/{id}/form")
	public String updateForm(Model model, @PathVariable Long id) {
		User user = this.userRepository.getOne(id);
		model.addAttribute("user", user);
		return "/user/updateForm";
	}

	@PutMapping("/{id}")
	public String update(Model model, @PathVariable Long id, User modifyUser) {
		User user = this.userRepository.getOne(id);
		user.update(modifyUser);
		this.userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}

	@PostMapping("/login")
	public String login(String userId, String password) {
		List<User> users = this.userRepository.findByUserId(userId);
		if(ObjectUtils.isEmpty(users) || users.size() < 1) {
			return "redirect:/users/loginForm?code=9999";
		}
		
		User user = null;
		for(User u : users) {
			if(StringUtils.equals(password, u.getPassword())) {
				user = u;
				break;
			}
		}
		
		if(ObjectUtils.isEmpty(user)) {
			return "redirect:/users/loginForm?code=9990";
		}
		
		System.out.println("Login Success [id:"+user.getUserId()+"]");
		session.setAttribute("sessUser", user);
		
		return "/user/profile";
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.removeAttribute("sessUser");
		return "redirect:/";
	}
}


































