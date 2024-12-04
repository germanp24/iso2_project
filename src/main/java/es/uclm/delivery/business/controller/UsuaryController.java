package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Usuary;
import es.uclm.delivery.persistence.UsuaryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuaryController {

    private static final Logger log = LoggerFactory.getLogger(UsuaryController.class);

    private static final String LOGIN = "login";

    private final UsuaryDAO usuaryDAO;

    public UsuaryController(UsuaryDAO usuaryDAO) {
        this.usuaryDAO = usuaryDAO;
    }

    @GetMapping("/login")
    public String usuaryForm(Model model) {

        model.addAttribute(LOGIN, new Usuary());

        if (log.isInfoEnabled()) {
            log.info(usuaryDAO.findAll().toString());
        }

        return LOGIN;
    }

    @PostMapping("/login")
    public String verifyUsuary(@RequestParam String email, @RequestParam String password, Model model) {
        Usuary usuary = usuaryDAO.findByEmailAndPassword(email, password).orElse(null);

        if (usuary != null) {
            model.addAttribute("userRole", usuary.getRole());
            return "redirect:/index";
        } else {
            model.addAttribute("error", "Credenciales incorrectas. Por favor, intenta de nuevo.");
            return LOGIN;
        }
    }
}