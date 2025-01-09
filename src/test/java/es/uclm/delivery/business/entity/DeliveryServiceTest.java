package es.uclm.delivery.business.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeliveryServiceTest {

    private DeliveryService deliveryService;
    private DeliveryMan deliveryMan;
    private CustomerOrder customerOrder;

    @BeforeEach
    void setUp() {
        deliveryMan = new DeliveryMan();
        customerOrder = new CustomerOrder();
        deliveryService = new DeliveryService(LocalDateTime.of(2023, 10, 1, 12, 0),
                LocalDateTime.of(2023, 10, 1, 14, 0), "123 Main St", "456 Elm St", deliveryMan, customerOrder);
    }

    @Test
    void testGetDeliveryNumber() {
        deliveryService.setDeliveryNumber("DEL123");
        assertEquals("DEL123", deliveryService.getDeliveryNumber());
    }

    @Test
    void testSetDeliveryNumber() {
        deliveryService.setDeliveryNumber("DEL456");
        assertEquals("DEL456", deliveryService.getDeliveryNumber());
    }

    @Test
    void testGetDeliveryDate() {
        assertEquals(LocalDateTime.of(2023, 10, 1, 12, 0), deliveryService.getDeliveryDate());
    }

    @Test
    void testSetDeliveryDate() {
        deliveryService.setDeliveryDate(LocalDateTime.of(2023, 11, 1, 12, 0));
        assertEquals(LocalDateTime.of(2023, 11, 1, 12, 0), deliveryService.getDeliveryDate());
    }

    @Test
    void testGetDeliveryDatePickup() {
        assertEquals(LocalDateTime.of(2023, 10, 1, 14, 0), deliveryService.getDeliveryDatePickup());
    }

    @Test
    void testSetDeliveryDatePickup() {
        deliveryService.setDeliveryDatePickup(LocalDateTime.of(2023, 11, 1, 14, 0));
        assertEquals(LocalDateTime.of(2023, 11, 1, 14, 0), deliveryService.getDeliveryDatePickup());
    }

    @Test
    void testGetPickupAddress() {
        assertEquals("123 Main St", deliveryService.getPickupAddress());
    }

    @Test
    void testSetPickupAddress() {
        deliveryService.setPickupAddress("789 Oak St");
        assertEquals("789 Oak St", deliveryService.getPickupAddress());
    }

    @Test
    void testGetDestination() {
        assertEquals("456 Elm St", deliveryService.getDestination());
    }

    @Test
    void testSetDestination() {
        deliveryService.setDestination("101 Pine St");
        assertEquals("101 Pine St", deliveryService.getDestination());
    }

    @Test
    void testGetDeliveryMan() {
        assertEquals(deliveryMan, deliveryService.getDeliveryMan());
    }

    @Test
    void testSetDeliveryMan() {
        DeliveryMan newDeliveryMan = new DeliveryMan();
        deliveryService.setDeliveryMan(newDeliveryMan);
        assertEquals(newDeliveryMan, deliveryService.getDeliveryMan());
    }

    @Test
    void testGetCustomerOrder() {
        assertEquals(customerOrder, deliveryService.getCustomerOrder());
    }

    @Test
    void testSetCustomerOrder() {
        CustomerOrder newCustomerOrder = new CustomerOrder();
        deliveryService.setCustomerOrder(newCustomerOrder);
        assertEquals(newCustomerOrder, deliveryService.getCustomerOrder());
    }

    @Test
    void testToString() {
        String expected = "DeliveryService [delivery_date=2023-10-01T12:00, deliveryDatePickup=2023-10-01T14:00, pickupAddress=123 Main St, destination=456 Elm St]";
        assertEquals(expected, deliveryService.toString());
    }
}