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

        MenuItem menuItem = new MenuItem("Pizza Margherita", 8.99, "Main Course");

        MenuItem savedMenuItem = menuItemDAO.save(menuItem);

        assertNotNull(savedMenuItem);
        assertEquals("Pizza Margherita", savedMenuItem.getFoodName());
        assertEquals(8.99, savedMenuItem.getPrice());
        assertEquals("Main Course", savedMenuItem.getCategory());

        Optional<MenuItem> foundMenuItem = menuItemDAO.findById("Pizza Margherita");
        assertTrue(foundMenuItem.isPresent());
        assertEquals("Main Course", foundMenuItem.get().getCategory());
    }

    @Test
    void testDeleteMenuItem() {
        MenuItem menuItem = new MenuItem("Spaghetti", 12.50, "Main Course");
        menuItemDAO.save(menuItem);

        menuItemDAO.deleteById("Spaghetti");

        Optional<MenuItem> foundMenuItem = menuItemDAO.findById("Spaghetti");
        assertFalse(foundMenuItem.isPresent());
    }

    @Test
    void testUpdateMenuItem() {
        MenuItem menuItem = new MenuItem("Salad", 5.99, "Starter");
        menuItemDAO.save(menuItem);

        menuItem.setPrice(6.50);
        menuItemDAO.save(menuItem);

        Optional<MenuItem> updatedMenuItem = menuItemDAO.findById("Salad");
        assertTrue(updatedMenuItem.isPresent());
        assertEquals(6.50, updatedMenuItem.get().getPrice());
    }

    @Test
    void testFindAllMenuItems() {
        menuItemDAO.save(new MenuItem("Soup", 4.99, "Starter"));
        menuItemDAO.save(new MenuItem("Steak", 15.99, "Main Course"));

        assertEquals(2, menuItemDAO.findAll().size());
    }
}
