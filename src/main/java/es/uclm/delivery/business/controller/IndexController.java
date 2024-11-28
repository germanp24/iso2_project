package es.uclm.delivery.business.controller;

import org.hibernate.mapping.Index;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    private static final String INDEX = "verifyCif";

    @GetMapping("/index")
    public String indexForm(Model model) {

        model.addAttribute(INDEX, new Index());

        return INDEX;
    }

    @PostMapping("/index")
    public String indexSubmit(@ModelAttribute Index index, Model model) {

        return INDEX;
    }
}
