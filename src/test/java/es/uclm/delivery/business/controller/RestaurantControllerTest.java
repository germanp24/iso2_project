package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.MenuItemDAO;
import es.uclm.delivery.persistence.RestaurantDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RestaurantControllerTest {

    @Mock
    private RestaurantDAO restaurantDAO;

    @Mock
    private MenuItemDAO menuItemDAO;

    @Mock
    private Model model;

    @InjectMocks
    private RestaurantController restaurantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRestaurantForm() {
        String viewName = restaurantController.RestaurantForm(model);

        verify(model, times(1)).addAttribute(eq("restaurant"), any(Restaurant.class));
        assertEquals("restaurant", viewName);
    }

    @Test
    void testRestaurantSubmit() {
        Restaurant restaurant = new Restaurant();
        restaurant.setCif("12345678A");

        when(restaurantDAO.findByCif(restaurant.getCif())).thenReturn(null);
        when(restaurantDAO.save(restaurant)).thenReturn(restaurant);

        String viewName = restaurantController.restaurantSubmit(restaurant, model);

        verify(restaurantDAO, times(1)).findByCif(restaurant.getCif());
        verify(restaurantDAO, times(1)).save(restaurant);
        verify(model, times(1)).addAttribute("restaurant", restaurant);
        verify(model, times(1)).addAttribute("successMessage", "¡Restaurante guardado con éxito!");
        assertEquals("restaurant", viewName);
    }

    @Test
    void testRestaurantSubmitCifExists() {
        Restaurant restaurant = new Restaurant();
        restaurant.setCif("12345678A");

        when(restaurantDAO.findByCif(restaurant.getCif())).thenReturn(restaurant);

        String viewName = restaurantController.restaurantSubmit(restaurant, model);

        verify(restaurantDAO, times(1)).findByCif(restaurant.getCif());
        verify(restaurantDAO, times(0)).save(restaurant);
        verify(model, times(1)).addAttribute("errorMessage", "El CIF ya está registrado.");
        assertEquals("restaurant", viewName);
    }
}