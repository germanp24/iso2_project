package es.uclm.delivery.persistence;

import es.uclm.delivery.business.entity.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RestaurantDAOTest {

    @Autowired
    private RestaurantDAO restaurantDAO;

    @Test
    void testSaveAndFindByCif() {
        // Crear un restaurante y guardarlo
        Restaurant restaurant = new Restaurant("CIF123", "Restaurant A", "Admin1", "url/image.jpg", "Main St", "City A");
        restaurantDAO.save(restaurant);

        // Buscar por CIF
        Restaurant foundRestaurant = restaurantDAO.findByCif("CIF123");
        assertNotNull(foundRestaurant);
        assertEquals("Restaurant A", foundRestaurant.getName());
        assertEquals("City A", foundRestaurant.getLocality());
    }

    @Test
    void testFindByCif_NotFound() {
        // Buscar un restaurante que no existe
        Restaurant foundRestaurant = restaurantDAO.findByCif("NON_EXISTENT_CIF");
        assertNull(foundRestaurant);
    }
}
