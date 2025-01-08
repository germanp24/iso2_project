package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.MenuItemDAO;
import es.uclm.delivery.persistence.RestaurantDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RestaurantController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantController.class);
    private static final String RESTAURANT = "restaurant";

    private RestaurantDAO restaurantDAO;

    public RestaurantController(RestaurantDAO restaurantDAO) {
        this.restaurantDAO = restaurantDAO;
    }

    @GetMapping("/restaurant")
    public String RestaurantForm(Model model) {

        model.addAttribute("restaurant", new Restaurant());
        log.info("Mostrando formulario de registro de restaurantes.");
        return RESTAURANT;
    }
  
    @PostMapping("/restaurant")
    public String restaurantSubmit(@ModelAttribute Restaurant restaurant, Model model) {
        if (restaurantDAO.findByCif(restaurant.getCif()) != null) {
            model.addAttribute("errorMessage", "El CIF ya está registrado.");
            return RESTAURANT;
        }

        Restaurant savedRestaurant = restaurantDAO.save(restaurant);
        model.addAttribute("restaurant", savedRestaurant);
        model.addAttribute("successMessage", "¡Restaurante guardado con éxito!");

        log.info("Restaurante guardado: " + savedRestaurant);

        return RESTAURANT;
    }

    @GetMapping("/restaurants")
    public String showRestaurants(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "locality", required = false) String locality,
            Model model) {

        List<Restaurant> restaurants;
      
        // Si la localidad está presente, filtrar por localidad
        if (locality != null && !locality.isEmpty()) {
            if (search != null && !search.isEmpty()) {
                restaurants = restaurantDAO.findAll().stream()
                        .filter(r -> r.getLocality().equalsIgnoreCase(locality) && r.getName().toLowerCase().contains(search.toLowerCase()))
                        .collect(Collectors.toList());
            } else {
                restaurants = restaurantDAO.findAll().stream()
                        .filter(r -> r.getLocality().equalsIgnoreCase(locality))
                        .collect(Collectors.toList());
            }
            model.addAttribute("searchKeyword", search);
            model.addAttribute("locality", locality);
        } else {
            if (search != null && !search.isEmpty()) {
                restaurants = restaurantDAO.findAll().stream()
                        .filter(r -> r.getName().toLowerCase().contains(search.toLowerCase()))
                        .collect(Collectors.toList());
                model.addAttribute("searchKeyword", search);
            } else {
                restaurants = restaurantDAO.findAll();
            }
        }

        model.addAttribute("restaurants", restaurants);
        log.info("Mostrando lista de restaurantes: " + restaurants);

        return "restaurants";
    }


}
