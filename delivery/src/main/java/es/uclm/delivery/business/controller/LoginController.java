package es.uclm.delivery.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import es.uclm.delivery.business.entity.Usuary;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("login", new Usuary(null, null));
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Usuary user, Model model) {
        model.addAttribute("login", user);
        return "result";
    }
}