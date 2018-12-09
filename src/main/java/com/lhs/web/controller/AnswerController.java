package com.lhs.web.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lhs.common.util.HttpSessionUtils;
import com.lhs.domain.Answer;
import com.lhs.domain.AnswerRepository;
import com.lhs.domain.Question;
import com.lhs.domain.QuestionRepository;
import com.lhs.domain.User;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private HttpSession session;
	
	
	@PostMapping("")
	public String create(Model model, @PathVariable Long questionId, String contents) {
		if(!HttpSessionUtils.isLoginUser(session))
            return "redirect:/users/loginForm";
		
		final Question question = questionRepository.getOne(questionId);
		final User writer = HttpSessionUtils.getUserFormSession(session);
		
		answerRepository.save(new Answer(writer, question, contents, LocalDateTime.now()));
		
		return "redirect:/questions/"+questionId;
	}
}
