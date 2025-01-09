package es.uclm.delivery.business.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuContentTest {

    private MenuContent menuContent;
    private MenuItem menuItem;

    @BeforeEach
    public void setUp() {
        menuItem = new MenuItem();
        menuContent = new MenuContent("1", "Pizza", menuItem);
    }

    @Test
    void testGetId_menuContent() {
        assertEquals("1", menuContent.getId_menuContent());
    }

    @Test
    void testSetId_menuContent() {
        menuContent.setId_menuContent("2");
        assertEquals("2", menuContent.getId_menuContent());
    }

    @Test
    void testGetContentFood() {
        assertEquals("Pizza", menuContent.getContentFood());
    }

    @Test
    void testSetContentFood() {
        menuContent.setContentFood("Burger");
        assertEquals("Burger", menuContent.getContentFood());
    }

    @Test
    void testGetMenuItem() {
        assertEquals(menuItem, menuContent.getMenuItem());
    }

    @Test
    void testSetMenuItem() {
        MenuItem newMenuItem = new MenuItem();
        menuContent.setMenuItem(newMenuItem);
        assertEquals(newMenuItem, menuContent.getMenuItem());
    }
}