package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.DeliveryMan;
import es.uclm.delivery.persistence.DeliveryManDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeliveryManController {

    private static final Logger log = LoggerFactory.getLogger(DeliveryMan.class);

    @Autowired
    private DeliveryManDAO deliveryManDAO;

    @GetMapping("/registerDeliv")
    public String repartidorForm(Model model) {
        // Agregar un nuevo objeto DeliveryMan al modelo
        model.addAttribute("registerDeliv", new DeliveryMan());
        log.info(deliveryManDAO.findAll().toString());
        return "registerDeliv";
    }

    @PostMapping("/registerDeliv")
    public String RepartidorSubmit(@ModelAttribute DeliveryMan deliveryMan, Model model) {
        DeliveryMan savedRepartidor = deliveryManDAO.save(deliveryMan);
        model.addAttribute("registerDeliv", savedRepartidor);
        model.addAttribute("successMessage", "Repartidor saved successfully!");
        log.info("Saved Repartidor: " + savedRepartidor);
        return "registerDeliv";
    }
}
