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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * QuestionController
 */
@Controller
@RequestMapping("/qna")
public class QuestionController {

    private static final String ROOT_VIEW_PATH = "/qna";

    @Autowired
    private HttpSession session;

    @Autowired
    private QuestionRepository questionRepository;



    @GetMapping("/form")
    public String form (Model model) {
        if(!HttpSessionUtils.isLoginUser(session))
			return "redirect:/users/loginForm";

        return goview ("form");
    }

    @PostMapping("/regAction")
    public String regAction (Model model, String title, String contents) {
        if(!HttpSessionUtils.isLoginUser(session))
            return "redirect:/users/loginForm";
        
        if(StringUtils.isEmpty(title))
            return "redirect:/qna/form?errorCode=9990";
        if(StringUtils.isEmpty(contents))
            return "redirect:/qna/form?errorCode=9991";

        final User sessionUser = HttpSessionUtils.getUserFormSession(session);

        Question question = new Question();
        question.setWriter(sessionUser);
        question.setTitle(title);
        question.setContents(contents);
        question.setCreateDate(LocalDateTime.now());

        questionRepository.save(question);

        return "redirect:/";
    }

    @GetMapping("/show/{id}")
    public String show(Model model, @PathVariable Long id) {
        Question question = this.questionRepository.getOne(id);
        model.addAttribute("question", question);
        return goview("/show");
    }


    private String goview (String path) {
        return ROOT_VIEW_PATH +"/"+ path;
    }

}