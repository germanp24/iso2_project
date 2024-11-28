package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.RestaurantDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RestaurantController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);
    private static final String CIF_RESTAURANT = "cifRestaurant";

    private final RestaurantDAO restaurantDAO;

    public RestaurantController(RestaurantDAO restaurantDAO) {
        this.restaurantDAO = restaurantDAO;
    }

    @GetMapping("/cifRestaurant")
    public String restaurantForm(Model model) {

        model.addAttribute(CIF_RESTAURANT, new Restaurant());
        if (log.isInfoEnabled()) {
            log.info(restaurantDAO.findAll().toString());
        }

        return CIF_RESTAURANT;
    }

    @PostMapping("/cifRestaurant")
    public String restaurantSubmit(@ModelAttribute Restaurant restaurant, Model model) {
        Restaurant existingRestaurant = restaurantDAO.findByCif(restaurant.getCif());

        if (existingRestaurant != null) {
            model.addAttribute(CIF_RESTAURANT, restaurant);
            model.addAttribute("successMessage", "¡El CIF ya está registrado!");
            log.warn("Intento de registro con un CIF duplicado: {}", restaurant.getCif());
            return CIF_RESTAURANT;
        }

        Restaurant savedRestaurant = restaurantDAO.save(restaurant);
        model.addAttribute(CIF_RESTAURANT, savedRestaurant);
        model.addAttribute("successMessage", "¡Restaurante guardado con éxito!");
        log.info("Restaurante guardado: {}", savedRestaurant);

        return CIF_RESTAURANT;
    }

    @GetMapping("/restaurants")
    public String showRestaurants(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Restaurant> restaurants;

        if (search != null && !search.isEmpty()) {
            restaurants = restaurantDAO.findAll().stream()
                    .filter(r -> r.getName().toLowerCase().contains(search.toLowerCase()))
                    .toList();
            model.addAttribute("searchKeyword", search);
        } else {
            restaurants = restaurantDAO.findAll();
        }

        model.addAttribute("restaurants", restaurants);
        log.info("Mostrando lista de restaurantes: {}", restaurants);
        return "restaurants";
    }

}