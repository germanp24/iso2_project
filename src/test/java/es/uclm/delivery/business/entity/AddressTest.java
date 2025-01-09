package es.uclm.delivery.business.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressTest {

    private Address address;
    private CustomerOrder customerOrder;

    @BeforeEach
    void setUp() {
        customerOrder = new CustomerOrder();
        address = new Address("Main St", 123, "2B", "Springfield", customerOrder);
    }

    @Test
    void testGetIdAddress() {
        address.setIdAddress(1L);
        assertEquals(1L, address.getIdAddress());
    }

    @Test
    void testToString() {
        String expected = "Address [street=Main St, number=123, floorNumber=2B, locality=Springfield, customerOrder=" + customerOrder + "]";
        assertEquals(expected, address.toString());
    }
}