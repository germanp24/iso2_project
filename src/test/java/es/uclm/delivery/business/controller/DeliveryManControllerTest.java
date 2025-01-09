package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.DeliveryMan;
import es.uclm.delivery.persistence.DeliveryManDAO;
import es.uclm.delivery.persistence.RestaurantDAO;
import es.uclm.delivery.persistence.UsuaryDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DeliveryManControllerTest {

    @Mock
    private DeliveryManDAO deliveryManDAO;

    @Mock
    private RestaurantDAO restaurantDAO;

    @Mock
    private UsuaryDAO usuaryDAO;

    @Mock
    private Model model;

    @InjectMocks
    private DeliveryManController deliveryManController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRepartidorForm() {
        List<String> locations = new ArrayList<>();
        when(restaurantDAO.findAll()).thenReturn(new ArrayList<>());

        String viewName = deliveryManController.repartidorForm(model);

        verify(model, times(1)).addAttribute(eq("registerDeliv"), any(DeliveryMan.class));
        verify(model, times(1)).addAttribute("locations", locations);
        assertEquals("registerDeliv", viewName);
    }

    @Test
    void testRepartidorSubmit() {
        DeliveryMan deliveryMan = new DeliveryMan();
        String email = "test@example.com";
        String password = "password";
        String tipoAuto = "car";
        String localit = "Madrid";

        when(deliveryManDAO.findByDni(deliveryMan.getDni())).thenReturn(null);
        when(restaurantDAO.findByLocality(localit)).thenReturn(new ArrayList<>());

        String viewName = deliveryManController.repartidorSubmit(deliveryMan, email, password, tipoAuto, localit, model);

        verify(deliveryManDAO, times(1)).save(deliveryMan);
        verify(model, times(1)).addAttribute("registerDeliv", deliveryMan);
        verify(model, times(1)).addAttribute("successMessage", "¡Repartidor guardado con éxito!");
        assertEquals("delivery/home", viewName);
    }

    @Test
    void testRepartidorSubmitDniExists() {
        DeliveryMan deliveryMan = new DeliveryMan();
        String email = "test@example.com";
        String password = "password";
        String tipoAuto = "car";
        String localit = "Madrid";

        when(deliveryManDAO.findByDni(deliveryMan.getDni())).thenReturn(deliveryMan);

        String viewName = deliveryManController.repartidorSubmit(deliveryMan, email, password, tipoAuto, localit, model);

        verify(deliveryManDAO, times(0)).save(deliveryMan);
        verify(model, times(1)).addAttribute("registerDeliv", deliveryMan);
        verify(model, times(1)).addAttribute("errorMessage", "Error: Ya existe un repartidor registrado con este DNI.");
        assertEquals("registerDeliv", viewName);
    }
}