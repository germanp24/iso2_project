package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.DeliveryService;
import es.uclm.delivery.persistence.DeliveryServiceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DeliveryServiceController {

    private static final Logger log = LoggerFactory.getLogger(DeliveryService.class);

    @Autowired
    private DeliveryServiceDAO deliveryServiceDAO;

    @GetMapping("/deliveryService")
    public String DeliveryServiceForm(Model model) {

        model.addAttribute("deliveryService", new DeliveryService());

        log.info(deliveryServiceDAO.findAll().toString());

        return "deliveryService_form";
    }

    @PostMapping("/deliveryService")
    public String deliveryServiceSubmit(@ModelAttribute DeliveryService deliveryService, Model model) {

        DeliveryService saveddeliveryService = deliveryServiceDAO.save(deliveryService);

        model.addAttribute("deliveryService", saveddeliveryService);
        model.addAttribute("successMessage", "deliveryService saved successfully!");

        log.info("Saved deliveryService: " + saveddeliveryService);

        return "deliveryService_form";
    }
}
