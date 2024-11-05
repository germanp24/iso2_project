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

    @GetMapping("/restaurant")
    public String RestaurantForm(Model model) {

        model.addAttribute("restaurant", new Restaurant());

        log.info(restaurantDAO.findAll().toString());

        return "restaurant_form";
    }

    @PostMapping("/restaurant")
    public String restaurantSubmit(@ModelAttribute Restaurant restaurant, Model model) {

        Restaurant savedrestaurant = restaurantDAO.save(restaurant);

        model.addAttribute("restaurant", savedrestaurant);
        model.addAttribute("successMessage", "restaurant saved successfully!");

        log.info("Saved deliveryService: " + savedrestaurant);

        return "restaurant_form";
    }
}