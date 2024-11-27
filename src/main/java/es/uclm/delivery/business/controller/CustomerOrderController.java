package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.CustomerOrder;
import es.uclm.delivery.persistence.CustomerOrderDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerOrderController {

    private static final Logger log = LoggerFactory.getLogger(CustomerOrderController.class);

    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    @GetMapping("/customerOrder")
    public String CustomerOrderForm(Model model) {

        model.addAttribute("customerOrder", new CustomerOrder());

        log.info(customerOrderDAO.findAll().toString());

        return "customerOrder_form";
    }

    @PostMapping("/customerOrder")
    public String customerOrderSubmit(@ModelAttribute CustomerOrder customerOrder, Model model) {

        CustomerOrder savedcustomerOrder = customerOrderDAO.save(customerOrder);

        model.addAttribute("customerOrder", savedcustomerOrder);
        model.addAttribute("successMessage", "customerOrder saved successfully!");

        log.info("Saved customerOrder: " + savedcustomerOrder);

        return "customerOrder_form";
    }
}
