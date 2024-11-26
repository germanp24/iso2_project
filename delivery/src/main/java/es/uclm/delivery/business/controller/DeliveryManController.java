package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.DeliveryMan;
import es.uclm.delivery.business.entity.Usuary;
import es.uclm.delivery.persistence.DeliveryManDAO;

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
    public String RepartidorSubmit(@ModelAttribute DeliveryMan deliveryMan, @RequestParam String email,@RequestParam String password ,Model model) {
        Usuary usuary = new Usuary(password, email, "DELIVERYMAN", null, deliveryMan, null);

        deliveryMan.setUsuary(usuary); // Enlaza el repartidor con Usuary
        deliveryManDAO.save(deliveryMan);

        model.addAttribute("registerDeliv", deliveryMan);
        model.addAttribute("successMessage", "DeliveryMan registrado con éxito!");

        log.info("Cliente y usuario registrado: " + deliveryMan + " " + usuary);

        return "registerDeliv";
    }
}
