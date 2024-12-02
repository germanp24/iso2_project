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
        UUID transactionId = UUID.randomUUID();
        Payment payment = new Payment(transactionId, Date.valueOf("2024-12-01"), 12345L);

        Payment savedPayment = paymentDAO.save(payment);

        assertNotNull(savedPayment.getTransactionId());
        assertEquals(12345L, savedPayment.getOrderNumber());
        assertEquals(Date.valueOf("2024-12-01"), savedPayment.getTransactionDate());

        Optional<Payment> foundPayment = paymentDAO.findById(transactionId);
        assertTrue(foundPayment.isPresent());
        assertEquals(12345L, foundPayment.get().getOrderNumber());
    }

    @Test
    void testDeletePayment() {
        UUID transactionId = UUID.randomUUID();
        Payment payment = new Payment(transactionId, Date.valueOf("2024-12-02"), 54321L);
        paymentDAO.save(payment);

        paymentDAO.deleteById(transactionId);

        Optional<Payment> foundPayment = paymentDAO.findById(transactionId);
        assertFalse(foundPayment.isPresent());
    }

    @Test
    void testUpdatePayment() {
        UUID transactionId = UUID.randomUUID();
        Payment payment = new Payment(transactionId, Date.valueOf("2024-12-03"), 67890L);
        paymentDAO.save(payment);

        payment.setOrderNumber(98765L);
        paymentDAO.save(payment);

        Optional<Payment> updatedPayment = paymentDAO.findById(transactionId);
        assertTrue(updatedPayment.isPresent());
        assertEquals(98765L, updatedPayment.get().getOrderNumber());
    }

    @Test
    void testFindAllPayments() {
        Payment payment1 = new Payment(UUID.randomUUID(), Date.valueOf("2024-12-04"), 11111L);
        Payment payment2 = new Payment(UUID.randomUUID(), Date.valueOf("2024-12-05"), 22222L);
        paymentDAO.save(payment1);
        paymentDAO.save(payment2);

        assertEquals(2, paymentDAO.findAll().size());
    }
}
