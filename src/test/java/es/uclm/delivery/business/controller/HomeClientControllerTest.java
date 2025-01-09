package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HomeClientControllerTest {

    @Mock
    private Model model;

    @InjectMocks
    private HomeClientController homeClientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHomeClientForm() {
        String viewName = homeClientController.homeClientForm(model);

        verify(model, times(1)).addAttribute(eq("client"), any(Client.class));
        assertEquals("homeClient", viewName);
    }

    @Test
    void testHomeClientSubmit() {
        Client client = new Client();
        String viewName = homeClientController.homeClientSubmit(client);

        assertEquals("homeClient", viewName);
    }
}