package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Usuary;
import es.uclm.delivery.persistence.UsuaryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuaryController {

    private static final Logger log = LoggerFactory.getLogger(Usuary.class);

    @Autowired
    private UsuaryDAO usuaryDAO;

    @GetMapping("/login")
    public String usuaryForm(Model model) {

        model.addAttribute("login", new Usuary());

        log.info(usuaryDAO.findAll().toString());

        return "login";
    }

    @PostMapping("/login")
    public String verifyUsuary(@RequestParam String email, @RequestParam String password, Model model) {
        // Busca si el usuario existe en la base de datos
        Usuary usuary = usuaryDAO.findByEmailAndPassword(email, password).orElse(null);

        if (usuary != null) {
            model.addAttribute("userRole", usuary.getRole()); // Añade el rol al modelo para mostrar en la vista si es necesario
            return "redirect:/index"; // Redirige a la página de bienvenida si el usuario existe
        } else {
            model.addAttribute("error", "Credenciales incorrectas. Por favor, intenta de nuevo.");
            return "login"; // Regresa a la página de login con mensaje de error
        }
    }
}