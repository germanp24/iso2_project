package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.RestaurantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@Controller
public class AdminPageController {

    @Autowired
    private RestaurantDAO restaurantDAO;

    @GetMapping("/adminPage")
    public String showAdminPage(Model model) {
        // // Restaurantes de prueba
        // List<Restaurant> restaurants = Arrays.asList(
        //     new Restaurant("CIF001", "Pizza Express", "admin1"),
        //     new Restaurant("CIF002", "Sushi World", "admin2"),
        //     new Restaurant("CIF003", "Burger House", "admin3"),
        //     new Restaurant("CIF004", "Tacos Locos", "admin4"),
        //     new Restaurant("CIF005", "Salad Bar", "admin5"),
        //     new Restaurant("CIF006", "Italiano Ristorante", "admin6")
        // );

        // model.addAttribute("restaurants", restaurants);
        return "adminPage";
    }

    @GetMapping("/editRestaurant/{id}")
    public String showEditRestaurantForm(@PathVariable("id") String id, Model model) {
        Restaurant restaurant = restaurantDAO.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid restaurant Id:" + id));
        model.addAttribute("restaurant", restaurant);
        return "editRestaurant";
    }

    @PostMapping("/updateRestaurant/{id}")
    public String updateRestaurant(@PathVariable("id") String id, @ModelAttribute Restaurant restaurant, Model model) {
        restaurantDAO.save(restaurant);
        return "redirect:/adminPage";
    }
}