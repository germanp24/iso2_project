package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.RestaurantAdministrator;
import es.uclm.delivery.persistence.RestaurantAdministratorDAO;
import es.uclm.delivery.business.entity.Usuary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RestaurantAdministratorController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantAdministrator.class);

    @Autowired
    private RestaurantAdministratorDAO restaurantAdministratorDAO;

    @GetMapping("/registerAdmin")
    public String RestaurantAdministratorForm(Model model) {

        model.addAttribute("registerAdmin", new RestaurantAdministrator());

        log.info(restaurantAdministratorDAO.findAll().toString());

        return "registerAdmin";
    }

    @PostMapping("/registerAdmin")
    public String restaurantAdministratorSubmit(@ModelAttribute RestaurantAdministrator restaurantAdministrator, @RequestParam String email, @RequestParam String password, Model model) {
        
        Usuary usuary = new Usuary(password, email, "ADMIN", null, null, restaurantAdministrator);

        restaurantAdministrator.setUsuary(usuary);
        restaurantAdministratorDAO.save(restaurantAdministrator);

        model.addAttribute("registerAdmin", restaurantAdministrator);
        model.addAttribute("successMessage", "restaurantAdministrator saved successfully!");

        log.info("Saved deliveryService: " + restaurantAdministrator + "" + usuary);

        return "registerAdmin";
    }
}