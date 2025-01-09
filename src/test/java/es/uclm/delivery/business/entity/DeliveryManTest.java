package es.uclm.delivery.business.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeliveryManTest {

    private DeliveryMan deliveryMan;
    private Usuary usuary;

    @BeforeEach
    void setUp() {
        usuary = new Usuary("password", "email@example.com", "DELIVERYMAN");
        deliveryMan = new DeliveryMan("12345678A", "John", "Doe", "Smith", 90, "Car", "123456789", usuary);
    }

    @Test
    void testGetIdDeliveryMan() {
        deliveryMan.setIdDeliveryMan(1L);
        assertEquals(1L, deliveryMan.getIdDeliveryMan());
    }

    @Test
    void testSetIdDeliveryMan() {
        deliveryMan.setIdDeliveryMan(2L);
        assertEquals(2L, deliveryMan.getIdDeliveryMan());
    }

    @Test
    void testGetDni() {
        assertEquals("12345678A", deliveryMan.getDni());
    }

    @Test
    void testSetDni() {
        deliveryMan.setDni("87654321B");
        assertEquals("87654321B", deliveryMan.getDni());
    }

    @Test
    void testGetNames() {
        assertEquals("John", deliveryMan.getNames());
    }

    @Test
    void testSetNames() {
        deliveryMan.setNames("Jane");
        assertEquals("Jane", deliveryMan.getNames());
    }

    @Test
    void testSetSurnames_M() {
        deliveryMan.setSurnames_M("Johnson");
        assertEquals("Johnson", deliveryMan.getSurnames_M());
    }

    @Test
    void testSetSurnames_F() {
        deliveryMan.setSurnames_F("Brown");
        assertEquals("Brown", deliveryMan.getSurnames_F());
    }

    @Test
    void testGetPhone() {
        assertEquals("123456789", deliveryMan.getPhone());
    }

    @Test
    void testSetPhone() {
        deliveryMan.setPhone("987654321");
        assertEquals("987654321", deliveryMan.getPhone());
    }

    @Test
    void testGetTipoAuto() {
        assertEquals("Car", deliveryMan.getTipoAuto());
    }

    @Test
    void testSetTipoAuto() {
        deliveryMan.setTipoAuto("Bike");
        assertEquals("Bike", deliveryMan.getTipoAuto());
    }

    @Test
    void testGetEfficiency() {
        assertEquals(90, deliveryMan.getEfficiency());
    }

    @Test
    void testSetEfficiency() {
        deliveryMan.setEfficiency(95);
        assertEquals(95, deliveryMan.getEfficiency());
    }

    @Test
    void testGetUsuary() {
        assertEquals(usuary, deliveryMan.getUsuary());
    }

    @Test
    void testSetUsuary() {
        Usuary newUsuary = new Usuary("newpassword", "newemail@example.com", "DELIVERYMAN");
        deliveryMan.setUsuary(newUsuary);
        assertEquals(newUsuary, deliveryMan.getUsuary());
    }

    @Test
    void testGetRestaurant() {
        Set<Restaurant> restaurants = new HashSet<>();
        deliveryMan.setRestaurant(restaurants);
        assertEquals(restaurants, deliveryMan.getRestaurant());
    }

    @Test
    void testSetRestaurant() {
        Set<Restaurant> restaurants = new HashSet<>();
        deliveryMan.setRestaurant(restaurants);
        assertEquals(restaurants, deliveryMan.getRestaurant());
    }
}