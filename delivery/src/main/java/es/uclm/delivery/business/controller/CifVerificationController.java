package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.RestaurantDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CifVerificationController {

    @Autowired
    private RestaurantDAO restaurantDAO;

    @GetMapping("/verifyCif")
    public String showVerifyCifPage(@RequestParam String type, Model model) {
        model.addAttribute("type", type);
        return "verifyCif"; // Muestra la página de verificación de CIF
    }

    @PostMapping("/verifyCif")
    public String verifyCif(@RequestParam String cif, @RequestParam String type, Model model) {
        if (cif == null || cif.isEmpty()) {
            model.addAttribute("error", "CIF no puede estar vacío.");
            return "verifyCif";
        }

        if (!type.equals("admin") && !type.equals("deliv")) {
            model.addAttribute("error", "Tipo de usuario inválido.");
            model.addAttribute("type", type);
            return "verifyCif";
        }

        // Busca si el CIF existe en la base de datos
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
        return "verifyCif";
    }
}