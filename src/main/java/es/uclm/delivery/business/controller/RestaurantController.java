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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RestaurantController {

    private static final Logger log = LoggerFactory.getLogger(Restaurant.class);

    @Autowired
    private RestaurantDAO restaurantDAO;

    @GetMapping("/restaurant")
    public String RestaurantForm(Model model) {

        model.addAttribute("restaurant", new Restaurant());

        log.info(restaurantDAO.findAll().toString());

        return "restaurant";
    }

    @PostMapping("/restaurant")
    public String restaurantSubmit(@ModelAttribute Restaurant restaurant, Model model) {
        if (restaurantDAO.findByCif(restaurant.getCif()) != null) {
            model.addAttribute("errorMessage", "El CIF ya está registrado.");
            return "restaurant";
        }

        Restaurant savedRestaurant = restaurantDAO.save(restaurant);
        model.addAttribute("restaurant", savedRestaurant);
        model.addAttribute("successMessage", "¡Restaurante guardado con éxito!");

        log.info("Restaurante guardado: " + savedRestaurant);

        return "restaurant";
    }

    @GetMapping("/restaurants")
    public String showRestaurants(@RequestParam(value = "search", required = false) String search, Model model) {
        List<Restaurant> restaurants;

        if (search != null && !search.isEmpty()) {
            // Filtrar restaurantes cuyo nombre contenga el término de búsqueda (insensible a mayúsculas)
            restaurants = restaurantDAO.findAll().stream()
                .filter(r -> r.getName().toLowerCase().contains(search.toLowerCase()))
                .toList();
            model.addAttribute("searchKeyword", search); // Mantiene el término de búsqueda en el input del formulario
        } else {
            // Si no hay término de búsqueda, se muestran todos los restaurantes
            restaurants = restaurantDAO.findAll();
        }

        model.addAttribute("restaurants", restaurants); // Enviar lista al modelo
        log.info("Mostrando lista de restaurantes: " + restaurants);
        return "restaurants"; // Redirige a la plantilla restaurants.html
    }

} 