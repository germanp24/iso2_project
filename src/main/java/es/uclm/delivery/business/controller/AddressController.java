package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Address;
import es.uclm.delivery.persistence.AddressDAO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddressController {

    private final AddressDAO addressDAO;

    public AddressController(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @GetMapping("/address")
    public String addressForm(Model model) {

        model.addAttribute("address", new Address());
        return "address_form";

    }

    @PostMapping("/address")
    public String addressSubmit(@ModelAttribute Address address, Model model) {
        Address savedAddress = addressDAO.save(address);

        model.addAttribute("address", savedAddress);
        model.addAttribute("successMessage", "Address saved successfully!");

        return "address_form";
    }
}
