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

	private static final String _SESSION_USER_KEY_NM = "sessUser";
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HttpSession session;

	
	
	/**
	 * 회원가입 form
	 * @return
	 */
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}

	/**
	 * 회원가입 (사용자 생성)
	 * @param model
	 * @param data
	 * @return
	 */
	@PostMapping("")
	public String create(Model model, User data) {
		model.addAttribute("userId", data.getUserId());
		model.addAttribute("name", data.getName());
		model.addAttribute("email", data.getEmail());
		userRepository.save(data);
		return "redirect:/users";
	}

	/**
	 * 회원정보 리스트 조회
	 * @param model
	 * @param data
	 * @return
	 */
	@GetMapping("")
	public String list(Model model, User data) {
		User sessUser = (User)this.session.getAttribute(_SESSION_USER_KEY_NM);
		if(ObjectUtils.isEmpty(sessUser)) {
			return "redirect:/users/loginForm";
		}
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}

	/**
	 * 회원 정보 변경 form
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}/form")
	public String updateForm(Model model, @PathVariable Long id) {
		User sessUser = (User)this.session.getAttribute(_SESSION_USER_KEY_NM);
		if(ObjectUtils.isEmpty(sessUser)) {
			return "redirect:/users/loginForm";
		}
		
		if(!StringUtils.equals(id, sessUser.getId())) {
			throw new IllegalStateException("Miss match ID ERROR.");
		}
		
		User user = this.userRepository.getOne(sessUser.getId());
		model.addAttribute("user", user);
		
		session.removeAttribute(_SESSION_USER_KEY_NM);
		session.setAttribute(_SESSION_USER_KEY_NM, user);
		
		
		return "/user/updateForm";
	}

	/**
	 * 회원정보 변경. (업데이트)
	 * @param model
	 * @param id
	 * @param modifyUser
	 * @return
	 */
	@PutMapping("/{id}")
	public String update(Model model, @PathVariable Long id, User modifyUser) {
		User sessUser = (User)this.session.getAttribute(_SESSION_USER_KEY_NM);
		if(ObjectUtils.isEmpty(sessUser)) {
			return "redirect:/users/loginForm";
		}
		
		if(!StringUtils.equals(id, sessUser.getId())) {
			throw new IllegalStateException("Miss match ID ERROR.");
		}
		
		User user = this.userRepository.getOne(id);
		user.update(modifyUser);
		this.userRepository.save(user);
		
		session.removeAttribute(_SESSION_USER_KEY_NM);
		session.setAttribute(_SESSION_USER_KEY_NM, user);
		
		return "redirect:/users";
	}

	
	/**
	 * 로그인 from
	 * @return
	 */
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}

	
	/**
	 * 로그인 실행 (사용자정보 세션 저장)
	 * @param userId
	 * @param password
	 * @return
	 */
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
		session.setAttribute(_SESSION_USER_KEY_NM, user);
		
		return "/user/profile";
	}
	
	/**
	 * 로그아웃 (세션 시용자 정보 삭제)
	 * @return
	 */
	@GetMapping("/logout")
	public String logout() {
		session.removeAttribute(_SESSION_USER_KEY_NM);
		return "redirect:/";
	}
}


































