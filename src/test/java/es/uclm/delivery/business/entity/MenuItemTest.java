package es.uclm.delivery.business.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuItemTest {

    private MenuItem menuItem;
    private Restaurant restaurant;
    private List<MenuContent> menuContents;

    @BeforeEach
    public void setUp() {
        restaurant = new Restaurant();
        menuContents = new ArrayList<>();
        menuItem = new MenuItem(1L, "Pizza", 10.0, "Fast Food", restaurant, "imgUrl");
        menuItem.setMenuContents(menuContents);
    }

    @Test
    public void testGetId_menu() {
        assertEquals(1L, menuItem.getId_menu());
    }

    @Test
    public void testSetId_menu() {
        menuItem.setId_menu(2L);
        assertEquals(2L, menuItem.getId_menu());
    }

    @Test
    public void testGetFoodName() {
        assertEquals("Pizza", menuItem.getFoodName());
    }

    @Test
    public void testSetFoodName() {
        menuItem.setFoodName("Burger");
        assertEquals("Burger", menuItem.getFoodName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(10.0, menuItem.getPrice());
    }

    @Test
    public void testSetPrice() {
        menuItem.setPrice(15.0);
        assertEquals(15.0, menuItem.getPrice());
    }

    @Test
    public void testGetCategory() {
        assertEquals("Fast Food", menuItem.getCategory());
    }

    @Test
    public void testSetCategory() {
        menuItem.setCategory("Healthy");
        assertEquals("Healthy", menuItem.getCategory());
    }

    @Test
    public void testGetImgMenu() {
        assertEquals("imgUrl", menuItem.getImgMenu());
    }

    @Test
    public void testSetImgMenu() {
        menuItem.setImgMenu("newImgUrl");
        assertEquals("newImgUrl", menuItem.getImgMenu());
    }

    @Test
    public void testGetRestaurant() {
        assertEquals(restaurant, menuItem.getRestaurant());
    }

    @Test
    public void testSetRestaurant() {
        Restaurant newRestaurant = new Restaurant();
        menuItem.setRestaurant(newRestaurant);
        assertEquals(newRestaurant, menuItem.getRestaurant());
    }

    @Test
    public void testGetMenuContents() {
        assertEquals(menuContents, menuItem.getMenuContents());
    }

    @Test
    public void testSetMenuContents() {
        List<MenuContent> newMenuContents = new ArrayList<>();
        menuItem.setMenuContents(newMenuContents);
        assertEquals(newMenuContents, menuItem.getMenuContents());
    }

    @Test
    public void testToString() {
        String expected = "MenuItem [id_menu=1, food_name=Pizza, price=10.0, category=Fast Food, imgMenu=imgUrl]";
        assertEquals(expected, menuItem.toString());
    }
}