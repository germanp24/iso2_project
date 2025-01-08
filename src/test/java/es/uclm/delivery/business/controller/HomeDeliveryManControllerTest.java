package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.DeliveryMan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HomeDeliveryManControllerTest {

    @Mock
    private Model model;

    @InjectMocks
    private HomeDeliveryManController homeDeliveryManController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHomeDeliveryManForm() {
        String viewName = homeDeliveryManController.homeDeliveryManForm(model);

        verify(model, times(1)).addAttribute(eq("deliveryMan"), any(DeliveryMan.class));
        assertEquals("homeDeliveryMan", viewName);
    }

    @Test
    public void testHomeDeliveryManSubmit() {
        DeliveryMan deliveryMan = new DeliveryMan();
        String viewName = homeDeliveryManController.homeDeliveryManSubmit(deliveryMan);

        assertEquals("homeDeliveryMan", viewName);
    }
}