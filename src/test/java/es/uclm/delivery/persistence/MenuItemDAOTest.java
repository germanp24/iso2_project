package es.uclm.delivery.persistence;

import es.uclm.delivery.business.entity.MenuItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MenuItemDAOTest {

    @Autowired
    private MenuItemDAO menuItemDAO;

    @Test
    void testSaveAndRetrieveMenuItem() {
        // Crear un elemento del menú
        MenuItem menuItem = new MenuItem("Pizza Margherita", 8.99, "Main Course");

        // Guardar el elemento
        MenuItem savedMenuItem = menuItemDAO.save(menuItem);

        // Verificar que fue guardado correctamente
        assertNotNull(savedMenuItem);
        assertEquals("Pizza Margherita", savedMenuItem.getFoodName());
        assertEquals(8.99, savedMenuItem.getPrice());
        assertEquals("Main Course", savedMenuItem.getCategory());

        // Buscar por nombre
        Optional<MenuItem> foundMenuItem = menuItemDAO.findById("Pizza Margherita");
        assertTrue(foundMenuItem.isPresent());
        assertEquals("Main Course", foundMenuItem.get().getCategory());
    }

    @Test
    void testDeleteMenuItem() {
        // Crear y guardar un elemento
        MenuItem menuItem = new MenuItem("Spaghetti Bolognese", 12.50, "Main Course");
        menuItemDAO.save(menuItem);

        // Eliminar el elemento
        menuItemDAO.deleteById("Spaghetti Bolognese");

        // Verificar que ya no existe
        Optional<MenuItem> foundMenuItem = menuItemDAO.findById("Spaghetti Bolognese");
        assertFalse(foundMenuItem.isPresent());
    }

    @Test
    void testUpdateMenuItem() {
        // Crear y guardar un elemento
        MenuItem menuItem = new MenuItem("Salad", 5.99, "Starter");
        menuItemDAO.save(menuItem);

        // Actualizar el precio
        menuItem.setPrice(6.50);
        menuItemDAO.save(menuItem);

        // Verificar los cambios
        Optional<MenuItem> updatedMenuItem = menuItemDAO.findById("Salad");
        assertTrue(updatedMenuItem.isPresent());
        assertEquals(6.50, updatedMenuItem.get().getPrice());
    }

    @Test
    void testFindAllMenuItems() {
        // Crear y guardar varios elementos
        menuItemDAO.save(new MenuItem("Soup", 4.99, "Starter"));
        menuItemDAO.save(new MenuItem("Steak", 15.99, "Main Course"));

        // Verificar que se recuperan todos
        assertEquals(2, menuItemDAO.findAll().size());
    }
}
