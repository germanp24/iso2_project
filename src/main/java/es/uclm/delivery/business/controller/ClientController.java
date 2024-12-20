package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Client;
import es.uclm.delivery.business.entity.Usuary;
import es.uclm.delivery.persistence.ClientDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    private static final String REG_CLIENT = "registerClient";
    private final ClientDAO clientDAO;

    public ClientController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @GetMapping("/registerClient")
    public String clientForm(Model model) {

        model.addAttribute(REG_CLIENT, new Client());
        if (log.isInfoEnabled()) {
            log.info(clientDAO.findAll().toString());
        }
        return REG_CLIENT;

    }

    @PostMapping("/registerClient")
    public String clientSubmit(@ModelAttribute Client client, @RequestParam String email, @RequestParam String password,
            Model model) {

        Usuary usuary = new Usuary(password, email, "CLIENT", client, null, null);

        client.setUsuary(usuary);
        clientDAO.save(client);

        model.addAttribute(REG_CLIENT, client);
        model.addAttribute("successMessage", "Client registrado con éxito!");

        log.info("Cliente registrado con éxito. Cliente ID: {}, Usuario ID: {}", client.getIdClient(),
                usuary.getIdUsuary());

        return "homeClient";
    }
}
