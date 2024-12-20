package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.DeliveryMan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeDeliveryManController {

    @GetMapping("/homeDeliveryMan")
    public String homeDeliveryManForm(Model model) {
        model.addAttribute("deliveryMan", new DeliveryMan());
        return "homeDeliveryMan";
    }

    @PostMapping("/homeDeliveryMan")
    public String homeDeliveryManSubmit(@ModelAttribute DeliveryMan deliveryMan) {
        return "homeDeliveryMan";
    }
}
