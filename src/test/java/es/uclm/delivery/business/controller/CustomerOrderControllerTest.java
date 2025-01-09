package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.*;
        import es.uclm.delivery.persistence.MenuItemDAO;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
        import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
        import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerOrderControllerTest {

    @Mock
    private MenuItemDAO menuItemDAO;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @InjectMocks
    private CustomerOrderController customerOrderController;

    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerOrderController).build();
    }
    @Test
    public void addProduct() throws Exception {
        String menuItemId = "123";
        int quantity = 2;

        Restaurant restaurant = new Restaurant();
        restaurant.setCif("181818A");

        MenuItem menuItem = new MenuItem();
        menuItem.setId_menu(123L);
        menuItem.setRestaurant(restaurant);

        when(menuItemDAO.findById(menuItemId)).thenReturn(Optional.of(menuItem));

        List<CartItem> emptyCart = new ArrayList<>();

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("cart", emptyCart);

        mockMvc.perform(post("/orderDetails")
                        .param("menuItemId", menuItemId)
                        .param("quantity", String.valueOf(quantity))
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/restaurant/181818A"));

        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");


        assertNotNull(cartItems);
        assertEquals(1, cartItems.size());
        assertEquals(2, cartItems.get(0).getQuantity());
    }

    @Test
    void testSaveCardDetails_LoggedInClient() {
        Client loggedInClient = mock(Client.class);
        when(session.getAttribute("loggedInClient")).thenReturn(loggedInClient);
        when(loggedInClient.getDni()).thenReturn("03955954T");

        String street = "Main St";
        int number = 101;
        String floorNumber = "3A";
        String cardNumber = "1234567890123456";
        String expiryDate = "12/25";
        String cvv = "123";

        String view = customerOrderController.saveCardDetails(street, number, floorNumber, cardNumber, expiryDate, cvv, session);

        assertEquals("orderConfirmation", view);
    }

    @Test
    void testConfirmOrder_LoggedInClient_CashMethod() {
        // Arrange
        Client loggedInClient = mock(Client.class);
        when(session.getAttribute("loggedInClient")).thenReturn(loggedInClient);
        when(loggedInClient.getDni()).thenReturn("12345");

        String method = "cash";

        String view = customerOrderController.confirmOrder(method, session, model);

        assertEquals("redirect:/enterAddress?method=cash&dni=12345", view);
    }

    @Test
    void testConfirmOrder_NotLoggedIn() {

        when(session.getAttribute("loggedInClient")).thenReturn(null);

        String view = customerOrderController.confirmOrder("card", session, model);

        assertEquals("redirect:/login", view);
    }

    @Test
    void testSelectPaymentMethod_LoggedInUser() {
        Usuary loggedInUser = mock(Usuary.class);
        when(session.getAttribute("loggedInUser")).thenReturn(loggedInUser);

        String view = customerOrderController.selectPaymentMethod(session, model);

        assertEquals("selectPaymentMethod", view);
        verify(model).addAttribute("loggedInUser", loggedInUser);
    }

    @Test
    void testSelectPaymentMethod_NotLoggedIn() {
        // Arrange
        when(session.getAttribute("loggedInUser")).thenReturn(null);

        // Act
        String view = customerOrderController.selectPaymentMethod(session, model);

        // Assert
        assertEquals("redirect:/login", view);
    }


}