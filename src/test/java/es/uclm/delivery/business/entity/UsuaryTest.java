package es.uclm.delivery.business.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UsuaryTest {

    private Usuary usuary;
    private List<Client> clients;
    private List<DeliveryMan> deliveryMen;
    private List<RestaurantAdministrator> restaurantAdministrators;

    @BeforeEach
    void setUp() {
        clients = new ArrayList<>();
        deliveryMen = new ArrayList<>();
        restaurantAdministrators = new ArrayList<>();
        usuary = new Usuary("password", "email@example.com", "ADMIN");
        usuary.setClient(clients);
        usuary.setDeliveryMan(deliveryMen);
        usuary.setRestaurantAdministrator(restaurantAdministrators);
    }

    @Test
    void testGetIdUsuary() {
        usuary.setIdUsuary(1L);
        assertEquals(1L, usuary.getIdUsuary());
    }

    @Test
    void testSetIdUsuary() {
        usuary.setIdUsuary(2L);
        assertEquals(2L, usuary.getIdUsuary());
    }

    @Test
    void testGetPassword() {
        assertEquals("password", usuary.getPassword());
    }

    @Test
    void testSetPassword() {
        usuary.setPassword("newpassword");
        assertEquals("newpassword", usuary.getPassword());
    }

    @Test
    void testGetEmail() {
        assertEquals("email@example.com", usuary.getEmail());
    }

    @Test
    void testSetEmail() {
        usuary.setEmail("newemail@example.com");
        assertEquals("newemail@example.com", usuary.getEmail());
    }

    @Test
    void testGetRole() {
        assertEquals("ADMIN", usuary.getRole());
    }

    @Test
    void testSetRole() {
        usuary.setRole("USER");
        assertEquals("USER", usuary.getRole());
    }

    @Test
    void testGetClient() {
        assertEquals(clients, usuary.getClient());
    }

    @Test
    void testSetClient() {
        List<Client> newClients = new ArrayList<>();
        usuary.setClient(newClients);
        assertEquals(newClients, usuary.getClient());
    }

    @Test
    void testGetDeliveryMan() {
        assertEquals(deliveryMen, usuary.getDeliveryMan());
    }

    @Test
    void testSetDeliveryMan() {
        List<DeliveryMan> newDeliveryMen = new ArrayList<>();
        usuary.setDeliveryMan(newDeliveryMen);
        assertEquals(newDeliveryMen, usuary.getDeliveryMan());
    }

    @Test
    void testGetRestaurantAdministrator() {
        assertEquals(restaurantAdministrators, usuary.getRestaurantAdministrator());
    }

    @Test
    void testSetRestaurantAdministrator() {
        List<RestaurantAdministrator> newRestaurantAdministrators = new ArrayList<>();
        usuary.setRestaurantAdministrator(newRestaurantAdministrators);
        assertEquals(newRestaurantAdministrators, usuary.getRestaurantAdministrator());
    }

    @Test
    void testToString() {
        String expected = "Usuary [email=email@example.com, id_usuary=null, password=password, role=ADMIN]";
        assertEquals(expected, usuary.toString());
    }
}