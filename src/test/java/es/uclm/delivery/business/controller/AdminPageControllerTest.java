package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Restaurant;
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

public class AdminPageControllerTest {

    @Mock
    private RestaurantDAO restaurantDAO;

    @Mock
    private Model model;

    @InjectMocks
    private AdminPageController adminPageController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowAdminPage() {
        List<Restaurant> restaurants = new ArrayList<>();
        when(restaurantDAO.findAll()).thenReturn(restaurants);

        String viewName = adminPageController.showAdminPage(model);

        verify(model, times(1)).addAttribute("restaurants", restaurants);
        assertEquals("adminPage", viewName);
    }

    @Test
    public void testUpdateRestaurant() {
        Restaurant restaurant = new Restaurant();
        String viewName = adminPageController.updateRestaurant(restaurant);

        verify(restaurantDAO, times(1)).save(restaurant);
        assertEquals("redirect:/adminPage", viewName);
    }

    @Test
    public void testAddRestaurant() {
        Restaurant restaurant = new Restaurant();
        String viewName = adminPageController.addRestaurant(restaurant);

        verify(restaurantDAO, times(1)).save(restaurant);
        assertEquals("redirect:/adminPage", viewName);
    }

    @Test
    public void testDeleteRestaurant() {
        String cif = "12345678A";
        String viewName = adminPageController.deleteRestaurant(cif);

        verify(restaurantDAO, times(1)).deleteById(cif);
        assertEquals("redirect:/adminPage", viewName);
    }
}