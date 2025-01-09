package es.uclm.delivery.business.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantAdministratorTest {

    private RestaurantAdministrator restaurantAdministrator;
    private Usuary usuary;

    @BeforeEach
    void setUp() {
        usuary = new Usuary("password", "email@example.com", "ADMIN");
        restaurantAdministrator = new RestaurantAdministrator("12345678A", "John", "Doe", "Smith", "123456789", usuary);
    }

    @Test
    void testGetId_admin() {
        restaurantAdministrator.setId_admin(1L);
        assertEquals(1L, restaurantAdministrator.getId_admin());
    }

    @Test
    void testSetId_admin() {
        restaurantAdministrator.setId_admin(2L);
        assertEquals(2L, restaurantAdministrator.getId_admin());
    }

    @Test
    void testGetDni() {
        assertEquals("12345678A", restaurantAdministrator.getDni());
    }

    @Test
    void testSetDni() {
        restaurantAdministrator.setDni("87654321B");
        assertEquals("87654321B", restaurantAdministrator.getDni());
    }

    @Test
    void testGetName() {
        assertEquals("John", restaurantAdministrator.getName());
    }

    @Test
    void testSetName() {
        restaurantAdministrator.setName("Jane");
        assertEquals("Jane", restaurantAdministrator.getName());
    }

    @Test
    void testSetSurnames_M() {
        restaurantAdministrator.setSurnames_M("Johnson");
        assertEquals("Johnson", restaurantAdministrator.getSurnames_M());
    }

    @Test
    void testSetSurnames_F() {
        restaurantAdministrator.setSurnames_F("Brown");
        assertEquals("Brown", restaurantAdministrator.getSurnames_F());
    }

    @Test
    void testGetPhone() {
        assertEquals("123456789", restaurantAdministrator.getPhone());
    }

    @Test
    void testSetPhone() {
        restaurantAdministrator.setPhone("987654321");
        assertEquals("987654321", restaurantAdministrator.getPhone());
    }

    @Test
    void testGetUsuary() {
        assertEquals(usuary, restaurantAdministrator.getUsuary());
    }

    @Test
    void testSetUsuary() {
        Usuary newUsuary = new Usuary("newpassword", "newemail@example.com", "ADMIN");
        restaurantAdministrator.setUsuary(newUsuary);
        assertEquals(newUsuary, restaurantAdministrator.getUsuary());
    }
}