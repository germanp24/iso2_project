package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeClientController {

    @GetMapping("/homeClient")
    public String homeClientForm(Model model) {

        model.addAttribute("client", new Client());
        
        return "homeClient";
    }

    @PostMapping("/homeClient")
    public String homeClientSubmit(@ModelAttribute Client client) {
        return "homeClient";
    }
}
