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

    @GetMapping("/deliveryMan")
    public String DeliveryMan(Model model) {

        model.addAttribute("deliveryMan", new DeliveryMan());

        log.info(deliveryManDAO.findAll().toString());

        return "deliveryMan_form";
    }

    @PostMapping("/deliveryMan")
    public String deliveryManSubmit(@ModelAttribute DeliveryMan deliveryMan, Model model) {

        DeliveryMan saveddeliveryMan = deliveryManDAO.save(deliveryMan);

        model.addAttribute("deliveryMan", saveddeliveryMan);
        model.addAttribute("successMessage", "deliveryMan saved successfully!");

        log.info("Saved deliveryMan: " + saveddeliveryMan);

        return "deliveryMan_form";
    }
}
