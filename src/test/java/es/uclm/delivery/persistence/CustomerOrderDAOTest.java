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
        // Crear un CustomerOrder
        CustomerOrder order = new CustomerOrder(1, "12345678A", Date.valueOf("2024-12-01"), "Pizza Margherita");

        // Guardar en el repositorio
        CustomerOrder savedOrder = customerOrderDAO.save(order);

        // Validar que fue guardado correctamente
        assertNotNull(savedOrder);
        assertEquals(1, savedOrder.getOrderNumber());
        assertEquals("12345678A", savedOrder.getDni());
        assertEquals("Pizza Margherita", savedOrder.getOrderedFood());

        // Recuperar por ID
        Optional<CustomerOrder> foundOrder = customerOrderDAO.findById(1);
        assertTrue(foundOrder.isPresent());
        assertEquals("Pizza Margherita", foundOrder.get().getOrderedFood());
    }

    @Test
    void testDeleteCustomerOrder() {
        // Crear y guardar un CustomerOrder
        CustomerOrder order = new CustomerOrder(2, "87654321B", Date.valueOf("2024-12-02"), "Sushi Roll");
        customerOrderDAO.save(order);

        // Eliminar el CustomerOrder
        customerOrderDAO.deleteById(2);

        // Validar que ya no existe
        Optional<CustomerOrder> foundOrder = customerOrderDAO.findById(2);
        assertFalse(foundOrder.isPresent());
    }

    @Test
    void testFindAllCustomerOrders() {
        // Crear y guardar varios CustomerOrders
        CustomerOrder order1 = new CustomerOrder(3, "11111111C", Date.valueOf("2024-12-03"), "Burger");
        CustomerOrder order2 = new CustomerOrder(4, "22222222D", Date.valueOf("2024-12-04"), "Pasta Carbonara");
        customerOrderDAO.save(order1);
        customerOrderDAO.save(order2);

        // Validar que se pueden recuperar todos
        assertEquals(2, customerOrderDAO.findAll().size());
    }

    @Test
    void testUpdateCustomerOrder() {
        // Crear y guardar un CustomerOrder
        CustomerOrder order = new CustomerOrder(5, "33333333E", Date.valueOf("2024-12-05"), "Salad");
        customerOrderDAO.save(order);

        // Actualizar los datos del pedido
        order.setOrderedFood("Grilled Chicken");
        customerOrderDAO.save(order);

        // Validar los cambios
        Optional<CustomerOrder> updatedOrder = customerOrderDAO.findById(5);
        assertTrue(updatedOrder.isPresent());
        assertEquals("Grilled Chicken", updatedOrder.get().getOrderedFood());
    }
}
