package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.MenuItemDAO;
import es.uclm.delivery.persistence.RestaurantDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RestaurantControllerTest {

    @Mock
    private RestaurantDAO restaurantDAO;

    @Mock
    private MenuItemDAO menuItemDAO;

    @Mock
    private Model model;

    @InjectMocks
    private RestaurantController restaurantController;

    private List<Restaurant> restaurants;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurants = Arrays.asList(
                new Restaurant("181818A", "Restaurant A","url.com" ,"paseo","Madrid"),
                new Restaurant("121212Q", "Restaurant B","url.com" ,"paseo", "Barcelona"),
                new Restaurant("565656M", "Café Central", "url.com" ,"paseo","Madrid"));
    }

    @Test
    void testRestaurantForm() {
        String viewName = restaurantController.RestaurantForm(model);

        verify(model, times(1)).addAttribute(eq("restaurant"), any(Restaurant.class));
        assertEquals("restaurant", viewName);
    }

    @Test
    void testRestaurantSubmit() {
        Restaurant restaurant = new Restaurant();
        restaurant.setCif("12345678A");

        when(restaurantDAO.findByCif(restaurant.getCif())).thenReturn(null);
        when(restaurantDAO.save(restaurant)).thenReturn(restaurant);

        String viewName = restaurantController.restaurantSubmit(restaurant, model);

        verify(restaurantDAO, times(1)).findByCif(restaurant.getCif());
        verify(restaurantDAO, times(1)).save(restaurant);
        verify(model, times(1)).addAttribute("restaurant", restaurant);
        verify(model, times(1)).addAttribute("successMessage", "¡Restaurante guardado con éxito!");
        assertEquals("restaurant", viewName);
    }

    @Test
    void testRestaurantSubmitCifExists() {
        Restaurant restaurant = new Restaurant();
        restaurant.setCif("12345678A");

        when(restaurantDAO.findByCif(restaurant.getCif())).thenReturn(restaurant);

        String viewName = restaurantController.restaurantSubmit(restaurant, model);

        verify(restaurantDAO, times(1)).findByCif(restaurant.getCif());
        verify(restaurantDAO, times(0)).save(restaurant);
        verify(model, times(1)).addAttribute("errorMessage", "El CIF ya está registrado.");
        assertEquals("restaurant", viewName);
    }
    @Test
    void testShowRestaurants_WithLocalityAndSearch() {
        // Arrange
        String search = "Pizzeria";
        String locality = "Madrid";
        Restaurant restaurant1 = new Restaurant("CIF1", "Pizzeria Roma", "http://image.com", "Calle 1", "Madrid");
        Restaurant restaurant2 = new Restaurant("CIF2", "Pizza Express", "http://image2.com", "Calle 2", "Madrid");

        // Modifica el comportamiento de restaurantDAO.findAll para devolver ambos restaurantes
        List<Restaurant> restaurants = Arrays.asList(restaurant1, restaurant2);
        when(restaurantDAO.findAll()).thenReturn(restaurants);

        // Act
        String view = restaurantController.showRestaurants(search, locality, model);

        // Assert
        // Verifica que se añaden los atributos correctos
        verify(model).addAttribute("restaurants", Arrays.asList(restaurant1)); // Solo debería haber un restaurante
        verify(model).addAttribute("searchKeyword", search);
        verify(model).addAttribute("locality", locality);
        assertEquals("restaurants", view);
    }


    @Test
    void testShowRestaurants_WithLocalityOnly() {
        // Arrange
        String search = null;
        String locality = "Madrid";
        Restaurant restaurant1 = new Restaurant("CIF1", "Pizzeria Roma", "http://image.com", "Calle 1", "Madrid");
        Restaurant restaurant2 = new Restaurant("CIF2", "Pizza Express", "http://image2.com", "Calle 2", "Madrid");

        List<Restaurant> restaurants = Arrays.asList(restaurant1, restaurant2);
        when(restaurantDAO.findAll()).thenReturn(restaurants);

        // Act
        String view = restaurantController.showRestaurants(search, locality, model);

        // Assert
        verify(model).addAttribute("restaurants", Arrays.asList(restaurant1, restaurant2));
        verify(model).addAttribute("locality", locality);
        assertEquals("restaurants", view);
    }

    @Test
    void testShowRestaurants_WithSearchOnly() {
        // Arrange
        String search = "Pizzeria";
        String locality = null;
        Restaurant restaurant1 = new Restaurant("CIF1", "Pizzeria Roma", "http://image.com", "Calle 1", "Madrid");
        Restaurant restaurant2 = new Restaurant("CIF2", "Pizza Express", "http://image2.com", "Calle 2", "Barcelona");

        List<Restaurant> restaurants = Arrays.asList(restaurant1, restaurant2);
        when(restaurantDAO.findAll()).thenReturn(restaurants);

        // Act
        String view = restaurantController.showRestaurants(search, locality, model);

        // Assert
        verify(model).addAttribute("restaurants", Arrays.asList(restaurant1));
        verify(model).addAttribute("searchKeyword", search);
        assertEquals("restaurants", view);
    }

    @Test
    void testShowRestaurants_NoSearchNoLocality() {
        // Arrange
        String search = null;
        String locality = null;
        Restaurant restaurant1 = new Restaurant("CIF1", "Pizzeria Roma", "http://image.com", "Calle 1", "Madrid");
        Restaurant restaurant2 = new Restaurant("CIF2", "Pizza Express", "http://image2.com", "Calle 2", "Barcelona");

        List<Restaurant> restaurants = Arrays.asList(restaurant1, restaurant2);
        when(restaurantDAO.findAll()).thenReturn(restaurants);

        // Act
        String view = restaurantController.showRestaurants(search, locality, model);

        // Assert
        verify(model).addAttribute("restaurants", restaurants);
        assertEquals("restaurants", view);
    }
}