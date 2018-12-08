package com.lhs.web.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import com.lhs.common.util.HttpSessionUtils;
import com.lhs.domain.Question;
import com.lhs.domain.QuestionRepository;
import com.lhs.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * QuestionController
 */
@Controller
@RequestMapping("/qna")
public class QuestionController {

    @Autowired
    private HttpSession session;

    @Autowired
    private QuestionRepository questionRepository;


    @GetMapping("/form")
    public String form (Model model) {
        if(!HttpSessionUtils.isLoginUser(session))
			return "redirect:/users/loginForm";
        return goview ("/form");
    }

    @PostMapping("/form")
    public String form (Model model, @ModelAttribute Question createQuestion) {
        if(!HttpSessionUtils.isLoginUser(session))
            return "redirect:/users/loginForm";
        if(StringUtils.isEmpty(createQuestion.getTitle()))
            return "redirect:/qna/form?errorCode=9990";
        if(StringUtils.isEmpty(createQuestion.getContents()))
            return "redirect:/qna/form?errorCode=9991";
        final User sessionUser = HttpSessionUtils.getUserFormSession(session);
        
        createQuestion.setWriter(sessionUser);
        createQuestion.setCreateDate(LocalDateTime.now());
        questionRepository.save(createQuestion);

        return "redirect:/";
    }

    @GetMapping("/form/{id}")
    public String updateForm(Model model, @PathVariable Long id) {
        if(!HttpSessionUtils.isLoginUser(session)) {
            return "redirect:/users/loginForm";
        }

        final Question question = this.questionRepository.getOne(id);

        if(!question.sameWriter(HttpSessionUtils.getUserFormSession(session))){
            return "redirect:/users/loginForm";
        }
        
        model.addAttribute("question", question);
        return goview("/updateForm");
    }

    @PutMapping("/{id}")
    public String update (Model model, @PathVariable Long id, Question updateQuestion){
        if(!HttpSessionUtils.isLoginUser(session)) {
            return "redirect:/users/loginForm";
        }

        final Question question = questionRepository.getOne(id);

        if(!question.sameWriter(HttpSessionUtils.getUserFormSession(session))){
            return "redirect:/users/loginForm";
        }
        
        question.update(updateQuestion);
        this.questionRepository.save(question);
        
        return "redirect:/qna/"+id;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable Long id) {
        model.addAttribute("question", this.questionRepository.getOne(id));
        return goview("/show");
    }

    @DeleteMapping("/{id}")
    public String delete(Model model, @PathVariable Long id) {
        if(!HttpSessionUtils.isLoginUser(session)) {
            return "redirect:/users/loginForm";
        }

        final Question question = this.questionRepository.getOne(id);

        if(!question.sameWriter(HttpSessionUtils.getUserFormSession(session))){
            return "redirect:/users/loginForm";
        }

        this.questionRepository.delete(question);
        return "redirect:/";
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