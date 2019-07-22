package com.clientui.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
