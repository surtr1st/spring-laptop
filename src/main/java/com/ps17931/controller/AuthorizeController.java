package com.ps17931.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth")
public class AuthorizeController {

    private final HttpServletRequest request;

    @Autowired
    private AuthorizeController(HttpServletRequest request) {
        this.request = request;
    }

    @RequestMapping("/login/form")
    public String doLogin() {
        return (request.getRemoteUser() != null) ? "redirect:/index" : "security/login";
    }

    @RequestMapping("/login/success")
    public String toLoginSuccess() {
        return "redirect:/index";
    }

    @RequestMapping("/login/failed")
    public String toLoginFailed(Model model) {
        model.addAttribute("message", "Login Failed");
        return "forward:/auth/login/form";
    }

    @RequestMapping("/login/403")
    public String toForbidden() {
        return "security/403";
    }

    @RequestMapping("/logout")
    public String doLogOut() {
        return "forward:/index";
    }
}
