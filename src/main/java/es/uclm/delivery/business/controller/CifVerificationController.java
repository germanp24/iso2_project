package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.RestaurantDAO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CifVerificationController {

    private static final String VERIFY_CIF = "verifyCif";
    private final RestaurantDAO restaurantDAO;

    public CifVerificationController(RestaurantDAO restaurantDAO) {
        this.restaurantDAO = restaurantDAO;
    }

    @GetMapping("/verifyCif")
    public String showVerifyCifPage(@RequestParam String type, Model model) {
        model.addAttribute("type", type);
        return VERIFY_CIF;
    }

    @PostMapping("/verifyCif")
    public String verifyCif(@RequestParam String cif, @RequestParam String type, Model model) {

        if (!type.equals("admin") && !type.equals("deliv")) {
            model.addAttribute("error", "Tipo de usuario inválido.");
            model.addAttribute("type", type);
            return VERIFY_CIF;
        }

        Restaurant restaurant = restaurantDAO.findByCif(cif);

        if (restaurant != null) {
            if ("admin".equals(type)) {
                return "redirect:/registerAdmin";
            } else if ("deliv".equals(type)) {
                return "redirect:/registerDeliv";
            }
        }

        model.addAttribute("error", true);
        model.addAttribute("type", type);
        return VERIFY_CIF;
    }
}