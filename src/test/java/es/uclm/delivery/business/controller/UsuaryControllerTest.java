package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Client;
import es.uclm.delivery.business.entity.DeliveryMan;
import es.uclm.delivery.business.entity.Usuary;
import es.uclm.delivery.persistence.UsuaryDAO;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;


import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UsuaryControllerTest {

    @Mock
    private UsuaryDAO usuaryDAO;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @InjectMocks
    private UsuaryController usuaryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUsuaryForm() {
        String viewName = usuaryController.usuaryForm(model);

        verify(model, times(1)).addAttribute(eq("login"), any(Usuary.class));
        assertEquals("login", viewName);
    }

    //Cliente
    @Test
    void verifyUsuarytest() throws Exception {
        Usuary u = new Usuary();
        u.setEmail("test@test.com");
        u.setPassword("test");

        Client c = new Client();
        c.setDni("03955954t");
        u.setClient(Collections.singletonList(c));

        when(usuaryDAO.findByEmailAndPassword("test@test.com", "test")).thenReturn(Optional.of(u));
        String result = usuaryController.verifyUsuary("test@test.com", "test", session, model);
        assertEquals("redirect:/client/home?email=test@test.com", result);
    }

    //Repartidor
    @Test
    void verifyUsuarytestD() throws Exception {
        Usuary u = new Usuary();
        u.setEmail("delivery@example.com");
        u.setPassword("password");

        DeliveryMan d = new DeliveryMan();
        d.setDni("03955954t");
        u.setDeliveryMan(Collections.singletonList(d));

        when(usuaryDAO.findByEmailAndPassword("delivery@example.com", "password")).thenReturn(Optional.of(u));

        String result = usuaryController.verifyUsuary("delivery@example.com", "password", session, model);
        assertEquals("redirect:/delivery/home?email=delivery@example.com", result);
    }

    @Test
    void testVerifyUsuary_Failure_NoRole() {
        Usuary usuary = new Usuary();
        usuary.setEmail("norole@example.com");
        usuary.setPassword("password");

        when(usuaryDAO.findByEmailAndPassword("norole@example.com", "password")).thenReturn(Optional.of(usuary));

        String result = usuaryController.verifyUsuary("norole@example.com", "password", session, model);

        verify(model).addAttribute("error", "No se ha encontrado un rol asociado a este usuario.");
        assertEquals("login", result);
    }

    @Test
    void testVerifyUsuary_Failure_InvalidCredentials() {
        when(usuaryDAO.findByEmailAndPassword("invalid@example.com", "wrongpassword")).thenReturn(Optional.empty());

        String result = usuaryController.verifyUsuary("invalid@example.com", "wrongpassword", session, model);

        verify(model).addAttribute("error", "Credenciales incorrectas. Por favor, intenta de nuevo.");
        assertEquals("login", result);
    }
}
