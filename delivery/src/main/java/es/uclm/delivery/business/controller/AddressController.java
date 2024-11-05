package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Address;
import es.uclm.delivery.persistence.AddressDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddressController {

    private static final Logger log = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressDAO addressDAO; // Inyectamos el DAO para interactuar con la base de datos

    // Método para cargar el formulario de address
    @GetMapping("/address")
    public String addressForm(Model model) {
        // Añadimos un nuevo objeto Address vacío al modelo para el formulario
        model.addAttribute("address", new Address());

        // Logueamos la lista de direcciones recuperadas desde la base de datos
        log.info(addressDAO.findAll().toString());

        return "address_form"; // Devolver el nombre de la vista (formulario de address)
    }

    // Método para procesar la creación de una nueva dirección
    @PostMapping("/address")
    public String addressSubmit(@ModelAttribute Address address, Model model) {
        // Guardar la nueva dirección en la base de datos
        Address savedAddress = addressDAO.save(address);

        // Añadir la dirección guardada al modelo para que se muestre en la vista de
        // resultado
        model.addAttribute("address", savedAddress);
        model.addAttribute("successMessage", "Address saved successfully!");

        // Logueamos la dirección que ha sido guardada
        log.info("Saved address: " + savedAddress);

        // Devolver el nombre de la vista para mostrar el resultado
        return "address_form";
    }
}
