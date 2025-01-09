package es.uclm.delivery.business.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestaurantTest {

    private Restaurant restaurant;
    private List<MenuItem> menu;
    private Set<DeliveryMan> deliveryMen;
    private Set<RestaurantAdministrator> restaurantAdministrators;

    @BeforeEach
    void setUp() {
        menu = new ArrayList<>();
        deliveryMen = new HashSet<>();
        restaurantAdministrators = new HashSet<>();
        restaurant = new Restaurant("123456A", "Test Restaurant", "imageUrl", "123 Main St", "Springfield");
        restaurant.setMenu(menu);
        restaurant.setDeliveryMan(deliveryMen);
        restaurant.setRestaurantAdministrator(restaurantAdministrators);
    }

    @Test
    void testGetCif() {
        assertEquals("123456A", restaurant.getCif());
    }

    @Test
    void testSetCif() {
        restaurant.setCif("654321B");
        assertEquals("654321B", restaurant.getCif());
    }

    @Test
    void testGetName() {
        assertEquals("Test Restaurant", restaurant.getName());
    }

    @Test
    void testSetName() {
        restaurant.setName("New Restaurant");
        assertEquals("New Restaurant", restaurant.getName());
    }

    @Test
    void testGetImageUrl() {
        assertEquals("imageUrl", restaurant.getImageUrl());
    }

    @Test
    void testSetImageUrl() {
        restaurant.setImageUrl("newImageUrl");
        assertEquals("newImageUrl", restaurant.getImageUrl());
    }

    @Test
    void testGetStreet() {
        assertEquals("123 Main St", restaurant.getStreet());
    }

    @Test
    void testSetStreet() {
        restaurant.setStreet("456 Elm St");
        assertEquals("456 Elm St", restaurant.getStreet());
    }

    @Test
    void testGetLocality() {
        assertEquals("Springfield", restaurant.getLocality());
    }

    @Test
    void testSetLocality() {
        restaurant.setLocality("Shelbyville");
        assertEquals("Shelbyville", restaurant.getLocality());
    }

    @Test
    void testGetMenu() {
        assertEquals(menu, restaurant.getMenu());
    }

    @Test
    void testSetMenu() {
        List<MenuItem> newMenu = new ArrayList<>();
        restaurant.setMenu(newMenu);
        assertEquals(newMenu, restaurant.getMenu());
    }

    @Test
    void testGetDeliveryMan() {
        assertEquals(deliveryMen, restaurant.getDeliveryMan());
    }

    @Test
    void testSetDeliveryMan() {
        Set<DeliveryMan> newDeliveryMen = new HashSet<>();
        restaurant.setDeliveryMan(newDeliveryMen);
        assertEquals(newDeliveryMen, restaurant.getDeliveryMan());
    }

    @Test
    void testGetRestaurantAdministrator() {
        assertEquals(restaurantAdministrators, restaurant.getRestaurantAdministrator());
    }

    @Test
    void testSetRestaurantAdministrator() {
        Set<RestaurantAdministrator> newRestaurantAdministrators = new HashSet<>();
        restaurant.setRestaurantAdministrator(newRestaurantAdministrators);
        assertEquals(newRestaurantAdministrators, restaurant.getRestaurantAdministrator());
    }

    @Test
    void testToString() {
        String expected = "Restaurant [cif=123456A, name=Test Restaurant, imageUrl=imageUrl, street=123 Main St, locality=Springfield]";
        assertEquals(expected, restaurant.toString());
    }
}