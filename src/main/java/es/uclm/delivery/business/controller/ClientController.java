package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Client;
import es.uclm.delivery.business.entity.Usuary;
import es.uclm.delivery.persistence.ClientDAO;

import es.uclm.delivery.persistence.UsuaryDAO;
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
    private final UsuaryDAO usuaryDAO;

    public ClientController(ClientDAO clientDAO, UsuaryDAO usuaryDAO) {

        this.clientDAO = clientDAO;
        this.usuaryDAO = usuaryDAO;
    }

    @GetMapping("/registerClient")
    public String clientForm(Model model) {

        model.addAttribute(REG_CLIENT, new Client());
        if (log.isInfoEnabled()) {
            log.info(clientDAO.findAll().toString());
        }
        return REG_CLIENT;

    }

    @GetMapping("/client/home")
    public String clientHome(@RequestParam String email, Model model) {
        Usuary usuary = usuaryDAO.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        model.addAttribute("clientName", usuary.getEmail());
        log.info("Cargando home para el cliente: {}", usuary.getEmail());
        return "client/home";
    }


    @PostMapping("/registerClient")
    public String clientSubmit(@ModelAttribute Client client,
           @RequestParam String email, @RequestParam String password,
            Model model) {

        Usuary usuary = new Usuary(password,email,"CLIENT");
        client.setUsuary(usuary);
        clientDAO.save(client);

        model.addAttribute(REG_CLIENT, client);
        model.addAttribute("successMessage", "Client registrado con éxito!");

        log.info("Cliente registrado con éxito. Cliente ID: {}, Usuario ID: {}", client.getIdClient(),
                usuary.getIdUsuary());

        return "homeClient";
    }

    @GetMapping("/clientProfile")
    public String clientProfileForm(Model model) {

        model.addAttribute("client", new Client());
        
        return "clientProfile";
    }

    @PostMapping("/clientProfile")
    public String clientProfileSubmit(@ModelAttribute Client client) {
        return "clientProfile";
    }

    @GetMapping("/clientAccount")
    public String clientAccountForm(Model model) {

        model.addAttribute("client", new Client());
        
        return "clientAccount";
    }

    @PostMapping("/clientAccount")
    public String clientAccountSubmit(@ModelAttribute Client client) {
        return "clientAccount";
    }
}
