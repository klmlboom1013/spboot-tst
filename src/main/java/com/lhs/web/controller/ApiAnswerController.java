package com.lhs.web.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.common.util.HttpSessionUtils;
import com.lhs.domain.Answer;
import com.lhs.domain.AnswerRepository;
import com.lhs.domain.Question;
import com.lhs.domain.QuestionRepository;
import com.lhs.domain.User;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private HttpSession session;
	
	
	@PostMapping("")
	public Answer create(Model model, @PathVariable Long questionId, String contents) {
		if(!HttpSessionUtils.isLoginUser(session))
            return null;
		
		final Question question = questionRepository.getOne(questionId);
		final User writer = HttpSessionUtils.getUserFormSession(session);
		
		Answer answer = answerRepository.save(new Answer(writer, question, contents, LocalDateTime.now()));
		System.out.println(answer.toString());
		
		return answer;
	}
}
