package com.clientui.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {


    /**
     * Page d'accueil du web site
     * @return
     */
    @RequestMapping(value = { "/"}, method = RequestMethod.GET)
    public String index(Model model) {

        return "Accueil";
    }

}
