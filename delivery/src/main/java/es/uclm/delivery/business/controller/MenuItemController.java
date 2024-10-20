package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.MenuItem;
import es.uclm.delivery.persistence.MenuItemDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MenuItemController {

    private static final Logger log = LoggerFactory.getLogger(MenuItem.class);

    @Autowired
    private MenuItemDAO menuItemDAO;

    @GetMapping("/menuItem")
    public String MenuItemForm(Model model) {

        model.addAttribute("menuItem", new MenuItem());

        log.info(menuItemDAO.findAll().toString());

        return "menuItem_form";
    }

    @PostMapping("/menuItem")
    public String menuItemSubmit(@ModelAttribute MenuItem menuItem, Model model) {

        MenuItem savedmenuItem = menuItemDAO.save(menuItem);

        model.addAttribute("menuItem", savedmenuItem);
        model.addAttribute("successMessage", "menuItem saved successfully!");

        log.info("Saved deliveryService: " + savedmenuItem);

        return "menuItem_form";
    }
}
