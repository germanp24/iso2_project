package es.uclm.delivery.business.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerOrderTest {

    private CustomerOrder customerOrder;

    @BeforeEach
    void setUp() {
        customerOrder = new CustomerOrder("123 Main St", Date.valueOf("2023-10-01"), "Pizza");
    }

    @Test
    void testGetOrderNumber() {
        customerOrder.setOrderNumber(1);
        assertEquals(1, customerOrder.getOrderNumber());
    }

    @Test
    void testSetOrderNumber() {
        customerOrder.setOrderNumber(2);
        assertEquals(2, customerOrder.getOrderNumber());
    }

    @Test
    void testGetAddress() {
        assertEquals("123 Main St", customerOrder.getAddress());
    }

    @Test
    void testSetAddress() {
        customerOrder.setAddress("456 Elm St");
        assertEquals("456 Elm St", customerOrder.getAddress());
    }

    @Test
    void testGetDate() {
        assertEquals(Date.valueOf("2023-10-01"), customerOrder.getDate());
    }

    @Test
    void testSetDate() {
        customerOrder.setDate(Date.valueOf("2023-11-01"));
        assertEquals(Date.valueOf("2023-11-01"), customerOrder.getDate());
    }

    @Test
    void testGetOrderedFood() {
        assertEquals("Pizza", customerOrder.getOrderedFood());
    }

    @Test
    void testSetOrderedFood() {
        customerOrder.setOrderedFood("Burger");
        assertEquals("Burger", customerOrder.getOrderedFood());
    }

    @Test
    void testToString() {
        String expected = "CustomerOrder [orderNumber=0, date=2023-10-01, address=123 Main St, orderedFood=Pizza]";
        assertEquals(expected, customerOrder.toString());
    }
}