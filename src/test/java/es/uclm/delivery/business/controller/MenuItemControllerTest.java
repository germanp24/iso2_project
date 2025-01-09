package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.CartItem;
import es.uclm.delivery.business.entity.MenuItem;
import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.MenuItemDAO;
import es.uclm.delivery.persistence.RestaurantDAO;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MenuItemControllerTest {

    @Mock
    private MenuItemDAO menuItemDAO;

    @Mock
    private RestaurantDAO restaurantDAO;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @InjectMocks
    private MenuItemController menuItemController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRestaurantDetails() {
        String cif = "12345678A";
        Restaurant restaurant = mock(Restaurant.class);
        List<MenuItem> menuItems = new ArrayList<>();
        List<CartItem> cart = new ArrayList<>();

        when(restaurantDAO.findById(cif)).thenReturn(Optional.of(restaurant));
        when(menuItemDAO.findByRestaurantCif(cif)).thenReturn(menuItems);
        when(session.getAttribute("cart")).thenReturn(cart);

        String viewName = menuItemController.restaurantDetails(cif, session, model);

        verify(restaurantDAO, times(1)).findById(cif);
        verify(menuItemDAO, times(1)).findByRestaurantCif(cif);
        verify(session, times(1)).getAttribute("cart");
        verify(model, times(1)).addAttribute("restaurant", restaurant);
        verify(model, times(1)).addAttribute("menuItems", menuItems);
        verify(model, times(1)).addAttribute("cart", cart);
        verify(model, times(1)).addAttribute("totalPrice", 0.0);
        assertEquals("menuDetails", viewName);
    }

    @Test
    void testMenuItemForm() {
        List<MenuItem> menuItems = new ArrayList<>();
        List<Restaurant> restaurants = new ArrayList<>();

        when(menuItemDAO.findAll()).thenReturn(menuItems);
        when(restaurantDAO.findAll()).thenReturn(restaurants);

        String viewName = menuItemController.menuItemForm(model);

        verify(model, times(1)).addAttribute(eq("menuItem"), any(MenuItem.class));
        verify(model, times(1)).addAttribute("menuItems", menuItems);
        verify(model, times(1)).addAttribute("restaurants", restaurants);
        assertEquals("addMenuRestaurant", viewName);
    }
}