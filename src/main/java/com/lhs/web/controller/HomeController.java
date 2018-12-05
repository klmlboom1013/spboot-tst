package com.lhs.web.controller;

import java.util.List;

import com.lhs.domain.Question;
import com.lhs.domain.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("")
	public String home(Model model) {
		List<Question> questionList = this.questionRepository.findAll();
		model.addAttribute("questions", questionList);
		return "index";
	}
}
