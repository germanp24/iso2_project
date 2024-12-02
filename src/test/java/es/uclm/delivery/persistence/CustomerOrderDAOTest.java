package es.uclm.delivery.persistence;

import es.uclm.delivery.business.entity.CustomerOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerOrderDAOTest {

    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    @Test
    void testSaveAndRetrieveCustomerOrder() {

        CustomerOrder order = new CustomerOrder(1, "12345678A", Date.valueOf("2024-12-01"), "Pizza Margherita");

        CustomerOrder savedOrder = customerOrderDAO.save(order);

        assertNotNull(savedOrder);
        assertEquals(1, savedOrder.getOrderNumber());
        assertEquals("12345678A", savedOrder.getDni());
        assertEquals("Pizza Margherita", savedOrder.getOrderedFood());

        Optional<CustomerOrder> foundOrder = customerOrderDAO.findById(1);
        assertTrue(foundOrder.isPresent());
        assertEquals("Pizza Margherita", foundOrder.get().getOrderedFood());
    }

    @Test
    void testDeleteCustomerOrder() {

        CustomerOrder order = new CustomerOrder(2, "87654321B", Date.valueOf("2024-12-02"), "Sushi Roll");
        customerOrderDAO.save(order);


        customerOrderDAO.deleteById(2);


        Optional<CustomerOrder> foundOrder = customerOrderDAO.findById(2);
        assertFalse(foundOrder.isPresent());
    }

    @Test
    void testFindAllCustomerOrders() {
        CustomerOrder order1 = new CustomerOrder(3, "11111111C", Date.valueOf("2024-12-03"), "Burger");
        CustomerOrder order2 = new CustomerOrder(4, "22222222D", Date.valueOf("2024-12-04"), "Pasta");
        customerOrderDAO.save(order1);
        customerOrderDAO.save(order2);

        assertEquals(2, customerOrderDAO.findAll().size());
    }

    @Test
    void testUpdateCustomerOrder() {

        CustomerOrder order = new CustomerOrder(5, "33333333E", Date.valueOf("2024-12-05"), "Salad");
        customerOrderDAO.save(order);

        order.setOrderedFood("Grilled Chicken");
        customerOrderDAO.save(order);

        Optional<CustomerOrder> updatedOrder = customerOrderDAO.findById(5);
        assertTrue(updatedOrder.isPresent());
        assertEquals("Grilled Chicken", updatedOrder.get().getOrderedFood());
    }
}
