package es.uclm.delivery.persistence;

import es.uclm.delivery.business.entity.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PaymentDAOTest {

    @Autowired
    private PaymentDAO paymentDAO;

    @Test
    void testSaveAndRetrievePayment() {
        // Crear un Payment
        UUID transactionId = UUID.randomUUID();
        Payment payment = new Payment(transactionId, Date.valueOf("2024-12-01"), 12345L);

        // Guardar el Payment
        Payment savedPayment = paymentDAO.save(payment);

        // Validar que fue guardado correctamente
        assertNotNull(savedPayment.getTransactionId());
        assertEquals(12345L, savedPayment.getOrderNumber());
        assertEquals(Date.valueOf("2024-12-01"), savedPayment.getTransactionDate());

        // Buscar por ID
        Optional<Payment> foundPayment = paymentDAO.findById(transactionId);
        assertTrue(foundPayment.isPresent());
        assertEquals(12345L, foundPayment.get().getOrderNumber());
    }

    @Test
    void testDeletePayment() {
        // Crear y guardar un Payment
        UUID transactionId = UUID.randomUUID();
        Payment payment = new Payment(transactionId, Date.valueOf("2024-12-02"), 54321L);
        paymentDAO.save(payment);

        // Eliminar el Payment
        paymentDAO.deleteById(transactionId);

        // Validar que ya no existe
        Optional<Payment> foundPayment = paymentDAO.findById(transactionId);
        assertFalse(foundPayment.isPresent());
    }

    @Test
    void testUpdatePayment() {
        // Crear y guardar un Payment
        UUID transactionId = UUID.randomUUID();
        Payment payment = new Payment(transactionId, Date.valueOf("2024-12-03"), 67890L);
        paymentDAO.save(payment);

        // Actualizar el número de orden
        payment.setOrderNumber(98765L);
        paymentDAO.save(payment);

        // Validar los cambios
        Optional<Payment> updatedPayment = paymentDAO.findById(transactionId);
        assertTrue(updatedPayment.isPresent());
        assertEquals(98765L, updatedPayment.get().getOrderNumber());
    }

    @Test
    void testFindAllPayments() {
        // Crear y guardar varios Payments
        Payment payment1 = new Payment(UUID.randomUUID(), Date.valueOf("2024-12-04"), 11111L);
        Payment payment2 = new Payment(UUID.randomUUID(), Date.valueOf("2024-12-05"), 22222L);
        paymentDAO.save(payment1);
        paymentDAO.save(payment2);

        // Validar que se pueden recuperar todos
        assertEquals(2, paymentDAO.findAll().size());
    }
}
