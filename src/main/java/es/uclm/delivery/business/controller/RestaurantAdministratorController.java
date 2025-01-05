package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.business.entity.RestaurantAdministrator;
import es.uclm.delivery.persistence.RestaurantAdministratorDAO;
import es.uclm.delivery.business.entity.Usuary;
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
import java.util.stream.Collectors;

@Controller
public class RestaurantAdministratorController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantAdministratorController.class);

    private static final String REG_ADMIN = "registerAdmin";

    private final RestaurantAdministratorDAO restaurantAdministratorDAO;
    private final RestaurantDAO restaurantDAO;

    public RestaurantAdministratorController(RestaurantAdministratorDAO restaurantAdministratorDAO, RestaurantDAO restaurantDAO) {
        this.restaurantAdministratorDAO = restaurantAdministratorDAO;
        this.restaurantDAO = restaurantDAO;
    }

    @GetMapping("/registerAdmin")
    public String restaurantAdministratorForm(Model model) {

        model.addAttribute(REG_ADMIN, new RestaurantAdministrator());

        List<String> nameRestaurant = restaurantDAO.findAll()
                .stream()
                .map(Restaurant::getName)
                .distinct()
                .collect(Collectors.toList());
        model.addAttribute("nameRestaurant", nameRestaurant);

        if (log.isInfoEnabled()) {
            log.info("Restaurantes almacenados: {}", nameRestaurant);
        }

        return REG_ADMIN;
    }

    @PostMapping("/registerAdmin")
    public String restaurantAdministratorSubmit(@ModelAttribute RestaurantAdministrator restaurantAdministrator,
            @RequestParam String email, @RequestParam String password, @RequestParam String nameRest ,Model model) {
        if (restaurantAdministratorDAO.findByDni(restaurantAdministrator.getDni())!= null){
            model.addAttribute("errorMessage", "El CIF ya está registrado.");
            model.addAttribute(REG_ADMIN, restaurantAdministrator);
            return REG_ADMIN;
        }

        Usuary usuary = new Usuary(password, email,"ADMIN");
        restaurantAdministrator.setUsuary(usuary);
        restaurantAdministratorDAO.save(restaurantAdministrator);

        List<Restaurant> restaurants = restaurantDAO.findByName(nameRest);
        restaurantAdministrator.getRestaurant().addAll(restaurants);

        model.addAttribute(REG_ADMIN, restaurantAdministrator);
        model.addAttribute("successMessage", "restaurantAdministrator saved successfully!");

        log.info("Saved deliveryService: {}, Usuario ID: {}", restaurantAdministrator.getId_admin(),
                usuary.getIdUsuary());
        return REG_ADMIN;
    }
}