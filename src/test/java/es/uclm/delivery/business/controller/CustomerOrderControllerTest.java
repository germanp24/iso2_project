package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.CartItem;
import es.uclm.delivery.business.entity.MenuItem;
import es.uclm.delivery.persistence.MenuItemDAO;
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

public class CustomerOrderControllerTest {

    @Mock
    private MenuItemDAO menuItemDAO;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @InjectMocks
    private CustomerOrderController customerOrderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddToCart() {
        String menuItemId = "1";
        int quantity = 2;
        MenuItem menuItem = mock(MenuItem.class);

        when(menuItemDAO.findById(menuItemId)).thenReturn(Optional.of(menuItem));
        when(session.getAttribute("cart")).thenReturn(null);

        String viewName = customerOrderController.addToCart(menuItemId, quantity, session, model);

        verify(menuItemDAO, times(1)).findById(menuItemId);
        verify(session, times(1)).getAttribute("cart");
        verify(session, times(1)).setAttribute(eq("cart"), anyList());
        assertEquals("orderDetails", viewName);
    }

    @Test
    public void testAddToCartWithExistingCart() {
        String menuItemId = "1";
        int quantity = 2;
        MenuItem menuItem = mock(MenuItem.class);

        List<CartItem> cart = new ArrayList<>();
        when(menuItemDAO.findById(menuItemId)).thenReturn(Optional.of(menuItem));
        when(session.getAttribute("cart")).thenReturn(cart);

        String viewName = customerOrderController.addToCart(menuItemId, quantity, session, model);

        verify(menuItemDAO, times(1)).findById(menuItemId);
        verify(session, times(1)).getAttribute("cart");
        verify(session, times(1)).setAttribute(eq("cart"), anyList());
        assertEquals("orderDetails", viewName);
    }

    @Test
    public void testAddToCartMenuItemNotFound() {
        String menuItemId = "1";
        int quantity = 2;

        when(menuItemDAO.findById(menuItemId)).thenReturn(Optional.empty());

        try {
            customerOrderController.addToCart(menuItemId, quantity, session, model);
        } catch (IllegalArgumentException e) {
            assertEquals("Menu item not found", e.getMessage());
        }

        verify(menuItemDAO, times(1)).findById(menuItemId);
        verify(session, times(0)).getAttribute("cart");
        verify(session, times(0)).setAttribute(eq("cart"), anyList());
    }
}