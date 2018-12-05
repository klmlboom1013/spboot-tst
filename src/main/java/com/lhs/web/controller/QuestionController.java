package com.lhs.web.controller;

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
        question.setWriter(sessionUser.getUserId());
        question.setTitle(title);
        question.setContents(contents);

        questionRepository.save(question);

        return "redirect:/";
    }



    private String goview (String path) {
        return ROOT_VIEW_PATH +"/"+ path;
    }

}