package es.uclm.delivery.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import es.uclm.delivery.business.entity.Register;

@Controller
public class registerController {

    @GetMapping("/register")
        public String registerForm(Model model) {
            model.addAttribute("register", new Register(null, null, null, null, null));
            return "register";
        }

    @PostMapping("/register")
        public String registerSubmit(@ModelAttribute Register register, Model model) {
            model.addAttribute("register", register);
            return "result";
        }
}
