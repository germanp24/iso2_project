package es.uclm.delivery.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import es.uclm.delivery.business.entity.Login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import es.uclm.delivery.persistence.LoginDAO;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginDAO loginDAO;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Login login, Model model) {
        Login existingLogin = loginDAO.findByEmail(login.getEmail());
        if (existingLogin != null && existingLogin.getPassword().equals(login.getPassword())) {
            model.addAttribute("login", login);
            return "result"; // Página de éxito
        } else {
            model.addAttribute("error", "Email o contraseña incorrectos");
            return "login"; // Volver a la página de login con un mensaje de error
        }
    }
}