package es.uclm.delivery.persistence;

import es.uclm.delivery.business.entity.DeliveryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DeliveryServiceDAOTest {

    @Autowired
    private DeliveryServiceDAO deliveryServiceDAO;

    @Test
    void testSaveAndRetrieveDeliveryService() {
        DeliveryService deliveryService = new DeliveryService(
                "DS123",
                "12345678A",
                "ORD001",
                LocalDateTime.of(2024, 12, 1, 14, 30),
                LocalDateTime.of(2024, 12, 1, 15, 30)
        );


        DeliveryService savedDeliveryService = deliveryServiceDAO.save(deliveryService);

        assertNotNull(savedDeliveryService.getDeliveryNumber());
        assertEquals("12345678A", savedDeliveryService.getNif());
        assertEquals("ORD001", savedDeliveryService.getOrderNumber());

        Optional<DeliveryService> foundDeliveryService = deliveryServiceDAO.findById("DS123");
        assertTrue(foundDeliveryService.isPresent());
        assertEquals("ORD001", foundDeliveryService.get().getOrderNumber());
    }

    @Test
    void testDeleteDeliveryService() {
        DeliveryService deliveryService = new DeliveryService(
                "DS124",
                "87654321B",
                "ORD002",
                LocalDateTime.of(2024, 12, 2, 10, 0),
                LocalDateTime.of(2024, 12, 2, 11, 0)
        );
        deliveryServiceDAO.save(deliveryService);

        deliveryServiceDAO.deleteById("DS124");

        Optional<DeliveryService> foundDeliveryService = deliveryServiceDAO.findById("DS124");
        assertFalse(foundDeliveryService.isPresent());
    }

    @Test
    void testFindAllDeliveryServices() {

        DeliveryService ds1 = new DeliveryService(
                "DS125",
                "11111111C",
                "ORD003",
                LocalDateTime.of(2024, 12, 3, 8, 0),
                LocalDateTime.of(2024, 12, 3, 9, 0)
        );
        DeliveryService ds2 = new DeliveryService(
                "DS126",
                "22222222D",
                "ORD004",
                LocalDateTime.of(2024, 12, 4, 8, 30),
                LocalDateTime.of(2024, 12, 4, 9, 30)
        );

        deliveryServiceDAO.save(ds1);
        deliveryServiceDAO.save(ds2);

        assertEquals(2, deliveryServiceDAO.findAll().size());
    }

    @Test
    void testUpdateDeliveryService() {
        DeliveryService deliveryService = new DeliveryService(
                "DS127",
                "33333333E",
                "ORD005",
                LocalDateTime.of(2024, 12, 5, 7, 0),
                LocalDateTime.of(2024, 12, 5, 8, 0)
        );
        deliveryServiceDAO.save(deliveryService);

        deliveryService.setDeliveryDate(LocalDateTime.of(2024, 12, 5, 8, 30));
        deliveryServiceDAO.save(deliveryService);

        Optional<DeliveryService> updatedDeliveryService = deliveryServiceDAO.findById("DS127");
        assertTrue(updatedDeliveryService.isPresent());
        assertEquals(LocalDateTime.of(2024, 12, 5, 8, 30), updatedDeliveryService.get().getDeliveryDate());
    }
}
