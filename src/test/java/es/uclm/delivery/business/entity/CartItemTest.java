package es.uclm.delivery.business.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CartItemTest {

    private CartItem cartItem;
    private MenuItem menuItem;

    @BeforeEach
    void setUp() {
        menuItem = mock(MenuItem.class);
        when(menuItem.getPrice()).thenReturn(10.0);
        cartItem = new CartItem(menuItem, 2);
    }

    @Test
    void testGetMenuItem() {
        assertEquals(menuItem, cartItem.getMenuItem());
    }

    @Test
    void testSetMenuItem() {
        MenuItem newMenuItem = mock(MenuItem.class);
        cartItem.setMenuItem(newMenuItem);
        assertEquals(newMenuItem, cartItem.getMenuItem());
    }

    @Test
    void testGetQuantity() {
        assertEquals(2, cartItem.getQuantity());
    }

    @Test
    void testSetQuantity() {
        cartItem.setQuantity(5);
        assertEquals(5, cartItem.getQuantity());
    }

    @Test
    void testGetTotalPrice() {
        assertEquals(20.0, cartItem.getTotalPrice());
    }
}