package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.MenuCard;
import es.uclm.delivery.persistence.MenuCardDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MenuCardController {

    private static final Logger log = LoggerFactory.getLogger(MenuCardController.class);

    private final MenuCardDAO menuCardDAO;

    public MenuCardController(MenuCardDAO menuCardDAO) {
        this.menuCardDAO = menuCardDAO;
    }

    @GetMapping("/menuCard")
    public String menuCardForm(Model model) {
        model.addAttribute("menuCard", new MenuCard());

        if (log.isInfoEnabled()) {
            log.info(menuCardDAO.findAll().toString());
        }

        return "menuCard_form";
    }

    @PostMapping("/menuCard")
    public String menuCardSubmit(@ModelAttribute MenuCard menuCard, Model model) {
        MenuCard savedMenuCard = menuCardDAO.save(menuCard);

        model.addAttribute("menuCard", savedMenuCard);
        model.addAttribute("successMessage", "MenuCard saved successfully!");

        log.info("Saved menuCard: {}", savedMenuCard);

        return "menuCard_form";
    }
}
