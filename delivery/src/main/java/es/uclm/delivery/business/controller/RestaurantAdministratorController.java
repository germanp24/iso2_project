package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.RestaurantAdministrator;
import es.uclm.delivery.persistence.RestaurantAdministratorDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RestaurantAdministratorController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantAdministrator.class);

    @Autowired
    private RestaurantAdministratorDAO restaurantAdministratorDAO;

    @GetMapping("/restaurantAdministrator")
    public String RestaurantAdministratorForm(Model model) {

        model.addAttribute("restaurantAdministrator", new RestaurantAdministrator());

        log.info(restaurantAdministratorDAO.findAll().toString());

        return "restaurantAdministrator_form";
    }

    @PostMapping("/restaurantAdministrator")
    public String restaurantAdministratorSubmit(@ModelAttribute RestaurantAdministrator restaurantAdministrator,
            Model model) {

        RestaurantAdministrator savedrestaurantAdministrator = restaurantAdministratorDAO.save(restaurantAdministrator);

        model.addAttribute("restaurantAdministrator", savedrestaurantAdministrator);
        model.addAttribute("successMessage", "restaurantAdministrator saved successfully!");

        log.info("Saved deliveryService: " + savedrestaurantAdministrator);

        return "restaurantAdministrator_form";
    }
}