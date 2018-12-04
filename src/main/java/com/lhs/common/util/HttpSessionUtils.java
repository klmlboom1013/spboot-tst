package com.lhs.common.util;

import javax.servlet.http.HttpSession;

import com.lhs.domain.User;

import org.springframework.util.ObjectUtils;

/**
 * HttpSessionUtils
 */
public class HttpSessionUtils {
    public static final String USER_SESSION_KEY = "sessUser";

    public static boolean isLoginUser (HttpSession session) {
        return !ObjectUtils.isEmpty(session.getAttribute(USER_SESSION_KEY));
    }

    public static User getUserFormSession (HttpSession session) {
        if(!isLoginUser(session)) return null;

        return (User)session.getAttribute(USER_SESSION_KEY);
    }
}