package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.DeliveryMan;
import es.uclm.delivery.business.entity.Usuary;
import es.uclm.delivery.persistence.DeliveryManDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeliveryManController {

    private static final Logger log = LoggerFactory.getLogger(DeliveryManController.class);

    private static final String REG_DELIVMAN = "registerDeliv";

    private final DeliveryManDAO deliveryManDAO;

    public DeliveryManController(DeliveryManDAO deliveryManDAO) {
        this.deliveryManDAO = deliveryManDAO;
    }

    @GetMapping("/registerDeliv")
    public String repartidorForm(Model model) {
        model.addAttribute(REG_DELIVMAN, new DeliveryMan());
        if (log.isInfoEnabled()) {
            log.info(deliveryManDAO.findAll().toString());
        }
        return REG_DELIVMAN;
    }

    @PostMapping("/registerDeliv")
    public String repartidorSubmit(@ModelAttribute DeliveryMan deliveryMan, @RequestParam String email,
            @RequestParam String password, Model model) {
        Usuary usuary = new Usuary(password, email, "DELIVERYMAN", null, deliveryMan, null);

        deliveryMan.setUsuary(usuary);
        deliveryManDAO.save(deliveryMan);

        model.addAttribute(REG_DELIVMAN, deliveryMan);
        model.addAttribute("successMessage", "DeliveryMan registrado con éxito!");

        log.info("Cliente y usuario registrado: {} {}", deliveryMan, usuary);

        return REG_DELIVMAN;
    }
}
