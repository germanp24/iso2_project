package es.uclm.delivery.business.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuItemTest {

    private MenuItem menuItem;
    private Restaurant restaurant;
    private List<MenuContent> menuContents;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
        menuContents = new ArrayList<>();
        menuItem = new MenuItem(1L, "Pizza", 10.0, "Fast Food", restaurant, "imgUrl");
        menuItem.setMenuContents(menuContents);
    }

    @Test
    void testGetId_menu() {
        assertEquals(1L, menuItem.getId_menu());
    }

    @Test
    void testSetId_menu() {
        menuItem.setId_menu(2L);
        assertEquals(2L, menuItem.getId_menu());
    }

    @Test
    void testGetFoodName() {
        assertEquals("Pizza", menuItem.getFoodName());
    }

    @Test
    void testSetFoodName() {
        menuItem.setFoodName("Burger");
        assertEquals("Burger", menuItem.getFoodName());
    }

    @Test
    void testGetPrice() {
        assertEquals(10.0, menuItem.getPrice());
    }

    @Test
    void testSetPrice() {
        menuItem.setPrice(15.0);
        assertEquals(15.0, menuItem.getPrice());
    }

    @Test
    void testGetCategory() {
        assertEquals("Fast Food", menuItem.getCategory());
    }

    @Test
    void testSetCategory() {
        menuItem.setCategory("Healthy");
        assertEquals("Healthy", menuItem.getCategory());
    }

    @Test
    void testGetImgMenu() {
        assertEquals("imgUrl", menuItem.getImgMenu());
    }

    @Test
    void testSetImgMenu() {
        menuItem.setImgMenu("newImgUrl");
        assertEquals("newImgUrl", menuItem.getImgMenu());
    }

    @Test
    void testGetRestaurant() {
        assertEquals(restaurant, menuItem.getRestaurant());
    }

    @Test
    void testSetRestaurant() {
        Restaurant newRestaurant = new Restaurant();
        menuItem.setRestaurant(newRestaurant);
        assertEquals(newRestaurant, menuItem.getRestaurant());
    }

    @Test
    void testGetMenuContents() {
        assertEquals(menuContents, menuItem.getMenuContents());
    }

    @Test
    void testSetMenuContents() {
        List<MenuContent> newMenuContents = new ArrayList<>();
        menuItem.setMenuContents(newMenuContents);
        assertEquals(newMenuContents, menuItem.getMenuContents());
    }

    @Test
    void testToString() {
        String expected = "MenuItem [id_menu=1, food_name=Pizza, price=10.0, category=Fast Food, imgMenu=imgUrl]";
        assertEquals(expected, menuItem.toString());
    }
}