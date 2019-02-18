package com.lhs.web.controller.rest;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.common.util.HttpSessionUtils;
import com.lhs.domain.Answer;
import com.lhs.domain.Question;
import com.lhs.domain.Result;
import com.lhs.domain.User;
import com.lhs.domain.repository.AnswerRepository;
import com.lhs.domain.repository.QuestionRepository;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private HttpSession session;
	
	
	@GetMapping("/{id}")
	public Answer show(@PathVariable Long id) {
		System.out.println("[[ SHOW ]]");
		Answer answer = answerRepository.findById(id).get();
		
		System.out.println("writer : "+answer.getWriter().toString());
		System.out.println("question : " + answer.getQuestion().toString());
		
		return answer;
	}
	
	@PostMapping("")
	public Answer create(@PathVariable Long questionId, String contents) {
		if(!HttpSessionUtils.isLoginUser(session)) {
            return null;
		}
		final User loginUser = HttpSessionUtils.getUserFormSession(session);
		final Question question = questionRepository.findById(questionId).get();
		final Answer answer = answerRepository.save(new Answer(loginUser, question, contents, LocalDateTime.now()));
		
		System.out.println(answer.toString());
		
		return answer;
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Long questionId, @PathVariable Long id) {
		System.out.println("[[ delete ]]");
		
		if(!HttpSessionUtils.isLoginUser(session)) {
            return Result.fail("로그인이 후 가능합니다.");
		}
		
		User loginUser = HttpSessionUtils.getUserFormSession(session);
		Answer answer = answerRepository.findById(id).get();
		
		if(!answer.isSammeWriter(loginUser)) {
			return Result.fail("작성자만 삭제 가능 합니다.");
		}
		
		answerRepository.deleteById(id);
		return Result.OK();
	}
	
}
