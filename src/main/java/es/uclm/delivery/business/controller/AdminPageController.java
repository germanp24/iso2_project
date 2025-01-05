package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.RestaurantDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class AdminPageController {

    private final RestaurantDAO restaurantDAO;

    public AdminPageController(RestaurantDAO restaurantDAO) {
        this.restaurantDAO = restaurantDAO;
    }

    @GetMapping("/adminPage")
    public String showAdminPage(Model model) {
        List<Restaurant> restaurants = restaurantDAO.findAll();
        System.out.println("RESTAURANTES OBTENIDOS DE LA BASE DE DATOS: " + restaurants);
        model.addAttribute("restaurants", restaurants);
        return "adminPage";
    }

    @PostMapping("/updateRestaurant")
    public String updateRestaurant(@ModelAttribute Restaurant restaurant) {
        restaurantDAO.save(restaurant);
        return "redirect:/adminPage";
    }

    @PostMapping("/addRestaurant")
    public String addRestaurant(@ModelAttribute Restaurant restaurant) {
        restaurantDAO.save(restaurant);
        return "redirect:/adminPage";
    }

    @PostMapping("/deleteRestaurant")
    public String deleteRestaurant(@RequestParam String cif) {
        restaurantDAO.deleteById(cif);
        return "redirect:/adminPage";
    }
}