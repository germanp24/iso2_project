package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.RestaurantAdministrator;
import es.uclm.delivery.persistence.RestaurantAdministratorDAO;
import es.uclm.delivery.persistence.RestaurantDAO;
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

class RestaurantAdministratorControllerTest {

    @Mock
    RestaurantAdministratorDAO restaurantAdministratorDAO;

    @Mock
    RestaurantDAO restaurantDAO;

    @Mock
    Model model;

    @InjectMocks
    RestaurantAdministratorController restaurantAdministratorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRestaurantAdministratorForm() {
        List<String> nameRestaurant = new ArrayList<>();
        when(restaurantDAO.findAll()).thenReturn(new ArrayList<>());

        String viewName = restaurantAdministratorController.restaurantAdministratorForm(model);

        verify(model, times(1)).addAttribute(eq("registerAdmin"), any(RestaurantAdministrator.class));
        verify(model, times(1)).addAttribute("nameRestaurant", nameRestaurant);
        assertEquals("registerAdmin", viewName);
    }

    @Test
    void testRestaurantAdministratorSubmit() {
        RestaurantAdministrator restaurantAdministrator = new RestaurantAdministrator();
        String email = "test@example.com";
        String password = "password";
        String nameRest = "Test Restaurant";

        when(restaurantAdministratorDAO.findByDni(restaurantAdministrator.getDni())).thenReturn(null);
        when(restaurantDAO.findByName(nameRest)).thenReturn(new ArrayList<>());

        String viewName = restaurantAdministratorController.restaurantAdministratorSubmit(restaurantAdministrator, email, password, nameRest, model);

        verify(restaurantAdministratorDAO, times(1)).save(restaurantAdministrator);
        verify(model, times(1)).addAttribute("registerAdmin", restaurantAdministrator);
        verify(model, times(1)).addAttribute("successMessage", "restaurantAdministrator saved successfully!");
        //assertEquals("registerAdmin", viewName);
    }

    @Test
    void testRestaurantAdministratorSubmitDniExists() {
        RestaurantAdministrator restaurantAdministrator = new RestaurantAdministrator();
        String email = "test@example.com";
        String password = "password";
        String nameRest = "Test Restaurant";

        when(restaurantAdministratorDAO.findByDni(restaurantAdministrator.getDni())).thenReturn(restaurantAdministrator);

        String viewName = restaurantAdministratorController.restaurantAdministratorSubmit(restaurantAdministrator, email, password, nameRest, model);

        verify(restaurantAdministratorDAO, times(0)).save(restaurantAdministrator);
        verify(model, times(1)).addAttribute("registerAdmin", restaurantAdministrator);
        verify(model, times(1)).addAttribute("errorMessage", "El CIF ya está registrado.");
        assertEquals("registerAdmin", viewName);
    }
}