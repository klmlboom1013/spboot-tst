package com.lhs.web.controller;

import javax.servlet.http.HttpSession;

import com.lhs.common.util.HttpSessionUtils;
import com.lhs.domain.Question;
import com.lhs.domain.QuestionRepository;
import com.lhs.domain.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * QuestionController
 */
@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private HttpSession session;

    @Autowired
    private QuestionRepository questionRepository;

    /**
     * 질문하기 화면 이동
     * @param model
     * @return
     */
    @GetMapping("/form")
    public String form (Model model) {
    	Result result = valid(null);
    	if(result.isValid()) {
    		model.addAttribute("errMessage", result.getErrorMessage());
    		return "/user/login";
    	}
        return goview ("/form");
    }
    
    /**
     * 질문 내용 등록
     * @param model
     * @param createQuestion
     * @return
     */
    @PostMapping("/form")
    public String form (Model model, String title, String contents) {
    	Result result = valid(null);
    	if(result.isValid()) {
    		model.addAttribute("errMessage", result.getErrorMessage());
    		return "/user/login";
    	}
        return "redirect:/";
    }

    /**
     * 질문 수정 화면 이동
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/{id}/form")
    public String updateForm(Model model, @PathVariable Long id) {
    	final Question question = this.questionRepository.getOne(id);
    	Result result = this.valid(question);    	
    	if(!result.isValid()) {
    		model.addAttribute("errMessage", result.getErrorMessage());
    		return "/user/login";
    	}
    	
       	model.addAttribute("question", question);       	
        return goview("/updateForm");
    }

    /**
     * 질문 내용 수정.
     * @param model
     * @param id
     * @param updateQuestion
     * @return
     */
    @PutMapping("/{id}")
    public String update (Model model, @PathVariable Long id, Question updateQuestion){
    	final Question question = this.questionRepository.getOne(id);
    	Result result = this.valid(question);    	
    	if(!result.isValid()) {
    		model.addAttribute("errMessage", result.getErrorMessage());
    		return "/user/login";
    	}
    	
    	question.update(updateQuestion);
        this.questionRepository.save(question);
       	
        return "redirect:/questions/"+id;
    }

    /**
     * 질문 내용 보기 화면 이동
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Long id) {
    	final Question question = this.questionRepository.getOne(id);
    	Result result = this.valid(question);    	
    	if(!result.isValid()) {
    		model.addAttribute("errMessage", result.getErrorMessage());
    		return "/user/login";
    	}
    	model.addAttribute("question", this.questionRepository.getOne(id));
        return goview("/show");
    }

    /**
     * 질문 삭제
     * @param model
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public String delete(Model model, @PathVariable Long id) {
    	final Question question = this.questionRepository.getOne(id);
    	Result result = this.valid(question);    	
    	if(!result.isValid()) {
    		model.addAttribute("errMessage", result.getErrorMessage());
    		return "/user/login";
    	}
    	this.questionRepository.delete(question);
    	return "redirect:/";
    }
    
    
    private Result valid (Question question) {
    	if(!HttpSessionUtils.isLoginUser(session))
    		return Result.fail("로그인이 필요 합니다.");
            
    	if(!ObjectUtils.isEmpty(question) && !question.isSameWriter(HttpSessionUtils.getUserFormSession(session))) {
    		return Result.fail("자신이 작성한 글만 수정, 삭제가 가능 합니다.");
    	}
    	return Result.OK();
    }
    

    /**
     * Web View Path
     * @param path
     * @return
     */
    private String goview (String path) {
        return "/qna"+path;
    }

}