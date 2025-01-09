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
        Client client = clientDAO.findByUsuary_Email(email)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no asociado"));

        model.addAttribute("clientName", client.getName());
        model.addAttribute("email", email); // Añadir el email al modelo
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

        return "client/home";
    }

    @GetMapping("clientProfile")
    public String clientProfileForm(@RequestParam String email, Model model) {
        Client client = clientDAO.findByUsuary_Email(email)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        model.addAttribute("client", client);
        model.addAttribute("email", email); // Añadimos el email al modelo
        return "client/clientProfile";
    }

    @PostMapping("clientProfile")
    public String clientProfileSubmit(@ModelAttribute Client client, @RequestParam String email) {
        // Procesar el cliente y redirigir con el email
        return "redirect:/clientProfile?email=" + email;
    }

    @GetMapping("clientAccount")
    public String clientAccountForm(@RequestParam String email, Model model) {
        // Obtener el cliente usando el email del usuario
        Client client = clientDAO.findByUsuary_Email(email)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
    
        // Añadir atributos al modelo para la vista
        model.addAttribute("client", client);
        model.addAttribute("email", email);
        model.addAttribute("password", client.getUsuary().getPassword()); // Añadir la contraseña al modelo
        return "client/clientAccount";
    }
    
    @PostMapping("clientAccount")
    public String clientAccountSubmit(@ModelAttribute Client client, 
                                    @RequestParam String password, 
                                    @RequestParam String currentEmail) {
        // Buscar el cliente existente basado en el email actual
        Client existingClient = clientDAO.findByUsuary_Email(currentEmail)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        // Actualizar los datos del cliente
        existingClient.setName(client.getName());
        existingClient.setSurnames_M(client.getSurnames_M());
        existingClient.setSurnames_F(client.getSurnames_F());

        // Actualizar los datos del usuario asociado
        Usuary usuary = existingClient.getUsuary();
        usuary.setPassword(password);

        // Guardar los cambios
        clientDAO.save(existingClient);

        // Redirigir nuevamente con el email
        return "redirect:/clientAccount?email=" + currentEmail;
    }
}
