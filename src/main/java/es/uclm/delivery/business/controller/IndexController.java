package es.uclm.delivery.business.controller;

import org.hibernate.mapping.Index;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class IndexController {
    
    @GetMapping("/index")
    public String IndexForm(Model model) {

        model.addAttribute("index", new Index());

        return "index";
    }

    @PostMapping("/index")
    public String indexSubmit(@ModelAttribute Index index, Model model) {

        return "index";
    }
}
