package es.uclm.delivery.persistence;

import es.uclm.delivery.business.entity.DeliveryMan;
import es.uclm.delivery.business.entity.Usuary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DeliveryManDAOTest {

    @Autowired
    private DeliveryManDAO deliveryManDAO;

    @Test
    void testSaveAndRetrieveDeliveryMan() {
        // Crear un repartidor con su usuario asociado
        Usuary usuary = new Usuary("password123", "delivery@example.com", "DELIVERYMAN", null, null, null);
        DeliveryMan deliveryMan = new DeliveryMan("12345678Z", "John", "Doe", 85, "Moto", usuary);

        // Guardar el repartidor
        DeliveryMan savedDeliveryMan = deliveryManDAO.save(deliveryMan);

        // Verificar que fue guardado correctamente
        assertNotNull(savedDeliveryMan.getIdDeliveryMan());
        assertEquals("12345678Z", savedDeliveryMan.getNif());
        assertEquals(85, savedDeliveryMan.getEfficiency());
        assertEquals("delivery@example.com", savedDeliveryMan.getUsuary().getEmail());
    }

    @Test
    void testRetrieveDeliveryManById() {
        // Crear y guardar un repartidor
        Usuary usuary = new Usuary("password456", "delivery2@example.com", "DELIVERYMAN", null, null, null);
        DeliveryMan deliveryMan = new DeliveryMan("87654321X", "Jane", "Smith", 90, "Car", usuary);
        DeliveryMan savedDeliveryMan = deliveryManDAO.save(deliveryMan);

        // Buscar el repartidor por ID
        DeliveryMan foundDeliveryMan = deliveryManDAO.findById(savedDeliveryMan.getIdDeliveryMan()).orElse(null);

        // Validar los datos del repartidor encontrado
        assertNotNull(foundDeliveryMan);
        assertEquals("Jane", foundDeliveryMan.getName());
        assertEquals("delivery2@example.com", foundDeliveryMan.getUsuary().getEmail());
    }
}
