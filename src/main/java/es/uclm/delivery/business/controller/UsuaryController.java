package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Usuary;
import es.uclm.delivery.persistence.UsuaryDAO;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
// Se usa para mantener la sesión del usuario en memoria durante la navegación
@SessionAttributes("usuary")
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
            // Determina los roles asociados
            model.addAttribute("usuary", usuary);

            // Redirigir según el rol
            if (usuary.getClient() != null && !usuary.getClient().isEmpty()) {
                model.addAttribute("hasClient", true);
                return "redirect:/client/home?email=" + email; // Redirige a home de Cliente
            }
            if (usuary.getDeliveryMan() != null && !usuary.getDeliveryMan().isEmpty()) {
                return "redirect:/delivery/home?email=" + email; // Redirige a home de Repartidor
            }
            if (usuary.getRestaurantAdministrator() != null && !usuary.getRestaurantAdministrator().isEmpty()) {
                return "redirect:/admin/dashboard"; // Redirige a home de Administrador
            }

            model.addAttribute("error", "No se ha encontrado un rol asociado a este usuario.");
            return "login"; // Si no se encuentra ningún rol, vuelve a la página de login
        } else {
            model.addAttribute("error", "Credenciales incorrectas. Por favor, intenta de nuevo.");
            return "login";
        }
    }
}