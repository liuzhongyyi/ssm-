package com.lzy.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.Subject;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login()
    {
        return "redirect:login.jsp";
    }
}
