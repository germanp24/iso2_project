package es.uclm.delivery.persistence;

import es.uclm.delivery.business.entity.RestaurantAdministrator;
import es.uclm.delivery.business.entity.Usuary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RestaurantAdministratorDAOTest {

    @Autowired
    private RestaurantAdministratorDAO restaurantAdministratorDAO;

    @Test
    void testSaveAndRetrieveAdministrator() {
        // Crear un administrador con su usuario asociado
        Usuary usuary = new Usuary("password123", "admin@example.com", "ADMIN", null, null, null);
        RestaurantAdministrator administrator = new RestaurantAdministrator("12345678A", "John", "Doe", usuary);

        // Guardar el administrador
        RestaurantAdministrator savedAdmin = restaurantAdministratorDAO.save(administrator);

        // Verificar que fue guardado correctamente
        assertNotNull(savedAdmin.getId_admin());
        assertEquals("12345678A", savedAdmin.getDni());
        assertEquals("admin@example.com", savedAdmin.getUsuary().getEmail());
    }

    @Test
    void testRetrieveAdministratorById() {
        // Crear y guardar un administrador
        Usuary usuary = new Usuary("securepass", "admin2@example.com", "ADMIN", null, null, null);
        RestaurantAdministrator administrator = new RestaurantAdministrator("87654321B", "Jane", "Smith", usuary);
        RestaurantAdministrator savedAdmin = restaurantAdministratorDAO.save(administrator);

        // Buscar el administrador por ID
        RestaurantAdministrator foundAdmin = restaurantAdministratorDAO.findById(savedAdmin.getId_admin()).orElse(null);

        // Validar los datos del administrador encontrado
        assertNotNull(foundAdmin);
        assertEquals("Jane", foundAdmin.getName());
        assertEquals("admin2@example.com", foundAdmin.getUsuary().getEmail());
    }
}
