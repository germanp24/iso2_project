package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.MenuCard;
import es.uclm.delivery.persistence.MenuCardDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MenuCardController {

    private static final Logger log = LoggerFactory.getLogger(MenuCardController.class);

    @Autowired
    private MenuCardDAO menuCardDAO; // Inyectamos el DAO para interactuar con la base de datos

    // Método para cargar el formulario de address
    @GetMapping("/menuCard")
    public String menuCardForm(Model model) {
        // Añadimos un nuevo objeto Address vacío al modelo para el formulario
        model.addAttribute("menuCard", new MenuCard());

        // Logueamos la lista de direcciones recuperadas desde la base de datos
        log.info(menuCardDAO.findAll().toString());

        return "menuCard_form"; // Devolver el nombre de la vista (formulario de address)
    }

    // Método para procesar la creación de una nueva dirección
    @PostMapping("/menuCard")
    public String menuCardSubmit(@ModelAttribute MenuCard menuCard, Model model) {
        // Guardar la nueva dirección en la base de datos
        MenuCard savedMenuCard = menuCardDAO.save(menuCard);

        // Añadir la dirección guardada al modelo para que se muestre en la vista de
        // resultado
        model.addAttribute("menuCard", savedMenuCard);
        model.addAttribute("successMessage", "MenuCard saved successfully!");

        // Logueamos la dirección que ha sido guardada
        log.info("Saved menuCard: " + savedMenuCard);

        // Devolver el nombre de la vista para mostrar el resultado
        return "menuCard_form";
    }
}
