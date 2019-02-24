package com.lhs.web.controller.rest;

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
		
		User loginUser = HttpSessionUtils.getUserFormSession(session);
		Question question = questionRepository.findById(questionId).get();
		Answer answer = answerRepository.save(new Answer(loginUser, question, contents));
		
		question.addAnswer();
		questionRepository.save(question);
		
		return answer;
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Long questionId, @PathVariable Long id) {
		if(!HttpSessionUtils.isLoginUser(session)) {
            return Result.fail("로그인이 후 가능합니다.");
		}
		
		User loginUser = HttpSessionUtils.getUserFormSession(session);
		Answer answer = answerRepository.findById(id).get();
		
		if(!answer.isSammeWriter(loginUser)) {
			return Result.fail("작성자만 삭제 가능 합니다.");
		}
		
		answerRepository.deleteById(id);
		
		Question question = questionRepository.findById(questionId).get();
		question.deleteAnswer();
		questionRepository.save(question);
		
		return Result.OK(new Integer(question.getCountAnswer()));
	}
	
}
