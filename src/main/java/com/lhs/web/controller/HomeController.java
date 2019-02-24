package com.lhs.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lhs.domain.Question;
import com.lhs.domain.repository.QuestionRepository;

@Controller
public class HomeController {

	@Autowired
	private QuestionRepository questionRepository;

//	@Autowired
//	private UserRepository userRepository;

//	@PostConstruct
//	public void init () {
//		System.out.println("###########################################");
//		System.out.println("테스트유저 생성 (1)");
//		System.out.println("###########################################");
//		User user1 = new User("klmlboom", "1234", "테스트유저01", "asd01@asd");
//		user1 = userRepository.save(user1);
//		System.out.println(user1.toString());
//
//		System.out.println("###########################################");
//		System.out.println("테스트유저 생성 (1)");
//		System.out.println("###########################################");
//		User user2 = new User("klml1013", "1234", "테스트유저02", "asd02@asd");
//		user2 = userRepository.save(user2);
//		System.out.println(user2.toString());
//
//		System.out.println("###########################################");
//		System.out.println("테스트 질문 생성");
//		System.out.println("###########################################");
//		Question question = new Question(user1, "질문있습니다.", "앗 질문이 없네요.. 테스트 입니다.", LocalDateTime.now());
//		question = questionRepository.save(question);
//		System.out.println(question.toString());
//	}


	@GetMapping("")
	public String home(Model model) {
		List<Question> questionList = this.questionRepository.findAll();
		model.addAttribute("questions", questionList);
		return "index";
	}
}
