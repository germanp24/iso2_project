package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Payment;
import es.uclm.delivery.persistence.PaymentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {

    private static final Logger log = LoggerFactory.getLogger(Payment.class);

    @Autowired
    private PaymentDAO paymentDAO;

    @GetMapping("/payment")
    public String PaymentForm(Model model) {

        model.addAttribute("payment", new Payment());

        log.info(paymentDAO.findAll().toString());

        return "payment_form";
    }

    @PostMapping("/payment")
    public String paymentSubmit(@ModelAttribute Payment payment, Model model) {

        Payment savedpayment = paymentDAO.save(payment);

        model.addAttribute("payment", savedpayment);
        model.addAttribute("successMessage", "payment saved successfully!");

        log.info("Saved deliveryService: " + savedpayment);

        return "payment_form";
    }
}