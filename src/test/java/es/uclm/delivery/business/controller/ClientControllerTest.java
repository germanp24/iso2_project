package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Client;
import es.uclm.delivery.business.entity.Usuary;
import es.uclm.delivery.persistence.ClientDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClientControllerTest {

    @Mock
    private ClientDAO clientDAO;

    @Mock
    private Model model;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testClientForm() {
        String viewName = clientController.clientForm(model);

        verify(model, times(1)).addAttribute(eq("registerClient"), any(Client.class));
        assertEquals("registerClient", viewName);
    }

    @Test
    void testClientHome() {
        Client client = new Client();
        client.setName("John Doe");
        when(clientDAO.findByUsuary_Email("email@example.com")).thenReturn(Optional.of(client));

        String viewName = clientController.clientHome("email@example.com", model);

        verify(model, times(1)).addAttribute("clientName", "John Doe");
        verify(model, times(1)).addAttribute("email", "email@example.com");
        assertEquals("client/home", viewName);
    }

    @Test
    void testClientSubmit() {
        Client client = new Client();
        String viewName = clientController.clientSubmit(client, "email@example.com", "password", model);

        verify(clientDAO, times(1)).save(client);
        verify(model, times(1)).addAttribute("registerClient", client);
        verify(model, times(1)).addAttribute("successMessage", "Client registrado con éxito!");
        assertEquals("client/home", viewName);
    }

    @Test
    void testClientProfileForm() {
        Client client = new Client();
        when(clientDAO.findByUsuary_Email("email@example.com")).thenReturn(Optional.of(client));

        String viewName = clientController.clientProfileForm("email@example.com", model);

        verify(model, times(1)).addAttribute("client", client);
        verify(model, times(1)).addAttribute("email", "email@example.com");
        assertEquals("client/clientProfile", viewName);
    }

    @Test
    void testClientProfileSubmit() {
        Client client = new Client();
        String viewName = clientController.clientProfileSubmit(client, "email@example.com");

        assertEquals("redirect:/clientProfile?email=email@example.com", viewName);
    }

    @Test
    void testClientAccountForm() {
        Client client = new Client();
        Usuary usuary = new Usuary();
        usuary.setPassword("password");
        client.setUsuary(usuary);
        when(clientDAO.findByUsuary_Email("email@example.com")).thenReturn(Optional.of(client));

        String viewName = clientController.clientAccountForm("email@example.com", model);

        verify(model, times(1)).addAttribute("client", client);
        verify(model, times(1)).addAttribute("email", "email@example.com");
        verify(model, times(1)).addAttribute("password", "password");
        assertEquals("client/clientAccount", viewName);
    }

    @Test
    void testClientAccountSubmit() {
        Client client = new Client();
        client.setName("John");
        client.setSurnames_M("Doe");
        client.setSurnames_F("Smith");

        Usuary usuary = new Usuary();
        usuary.setPassword("password");
        client.setUsuary(usuary);

        when(clientDAO.findByUsuary_Email("email@example.com")).thenReturn(Optional.of(client));

        String viewName = clientController.clientAccountSubmit(client, "newpassword", "email@example.com");

        verify(clientDAO, times(1)).save(client);
        assertEquals("redirect:/clientAccount?email=email@example.com", viewName);
    }
}