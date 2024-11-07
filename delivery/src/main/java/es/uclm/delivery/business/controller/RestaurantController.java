package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.RestaurantDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RestaurantController {

    private static final Logger log = LoggerFactory.getLogger(Restaurant.class);

    @Autowired
    private RestaurantDAO restaurantDAO;

    @GetMapping("/cifRestaurant")
    public String RestaurantForm(Model model) {

        model.addAttribute("restaurant", new Restaurant());

        log.info(restaurantDAO.findAll().toString());

        return "cifRestaurant";
    }

    @PostMapping("/cifRestaurant")
    public String restaurantSubmit(@ModelAttribute Restaurant restaurant, Model model) {
        if (restaurantDAO.findByCif(restaurant.getCif()) != null) {
            model.addAttribute("errorMessage", "El CIF ya está registrado.");
            return "cifRestaurant";
        }

        Restaurant savedRestaurant = restaurantDAO.save(restaurant);
        model.addAttribute("restaurant", savedRestaurant);
        model.addAttribute("successMessage", "¡Restaurante guardado con éxito!");

        log.info("Restaurante guardado: " + savedRestaurant);

        return "index";
    }

} 