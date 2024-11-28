package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.RestaurantDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AdminPageController {

    private RestaurantDAO restaurantDAO;

    public AdminPageController(RestaurantDAO restaurantDAO) {
        this.restaurantDAO = restaurantDAO;
    }

    @GetMapping("/adminPage")
    public String showAdminPage(Model model) {
        model.addAttribute("restaurants", restaurantDAO.findAll());
        return "adminPage";
    }

    @GetMapping("/editRestaurant/{id}")
    public String showEditRestaurantForm(@PathVariable("id") String id, Model model) {
        Restaurant restaurant = restaurantDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant Id:" + id));
        model.addAttribute("restaurant", restaurant);
        return "editRestaurant";
    }

    @PostMapping("/updateRestaurant/{id}")
    public String updateRestaurant(@PathVariable("id") String id, @ModelAttribute Restaurant restaurant, Model model) {
        restaurantDAO.save(restaurant);
        return "redirect:/adminPage";
    }
}