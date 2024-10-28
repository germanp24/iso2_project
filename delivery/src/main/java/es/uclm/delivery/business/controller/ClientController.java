package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Client;
import es.uclm.delivery.persistence.ClientDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientDAO clientDAO;

    @GetMapping("/client")
    public String clientForm(Model model) {

        model.addAttribute("client", new Client());

        log.info(clientDAO.findAll().toString());

        return "client_form";
    }

    @PostMapping("/client")
    public String clientSubmit(@ModelAttribute Client client, Model model) {

        Client savedClient = clientDAO.save(client);

        model.addAttribute("client", savedClient);
        model.addAttribute("successMessage", "Client saved successfully!");

        log.info("Saved client: " + savedClient);

        return "client_form";
    }
}