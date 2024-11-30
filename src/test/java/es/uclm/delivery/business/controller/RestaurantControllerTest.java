package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.controller.RestaurantController;
import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.RestaurantDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantDAO restaurantDAO;

    @Test
    void testRestaurantForm() throws Exception {
        Mockito.when(restaurantDAO.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/cifRestaurant"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cifRestaurant"))
                .andExpect(view().name("cifRestaurant"));
    }

    @Test
    void testRestaurantSubmit_WithDuplicateCIF() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setCif("12345");
        restaurant.setName("Restaurante 1");

        Mockito.when(restaurantDAO.findByCif("12345")).thenReturn(restaurant);

        mockMvc.perform(post("/cifRestaurant")
                        .param("cif", "12345")
                        .param("name", "Restaurante 1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cifRestaurant"))
                .andExpect(model().attribute("successMessage", "¡El CIF ya está registrado!"))
                .andExpect(view().name("cifRestaurant"));
    }

    @Test
    void testRestaurantSubmit_WithNewCIF() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setCif("54321");
        restaurant.setName("Restaurante Nuevo");

        Mockito.when(restaurantDAO.findByCif("54321")).thenReturn(null);
        Mockito.when(restaurantDAO.save(any(Restaurant.class))).thenReturn(restaurant);

        mockMvc.perform(post("/cifRestaurant")
                        .param("cif", "54321")
                        .param("name", "Restaurante Nuevo"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cifRestaurant"))
                .andExpect(model().attribute("successMessage", "¡Restaurante guardado con éxito!"))
                .andExpect(view().name("cifRestaurant"));
    }

    @Test
    void testShowRestaurants_WithSearch() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Restaurante A");

        Mockito.when(restaurantDAO.findAll()).thenReturn(List.of(restaurant));

        mockMvc.perform(get("/restaurants").param("search", "A"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("restaurants"))
                .andExpect(model().attribute("searchKeyword", "A"))
                .andExpect(view().name("restaurants"));
    }

    @Test
    void testShowRestaurants_WithoutSearch() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Restaurante B");

        Mockito.when(restaurantDAO.findAll()).thenReturn(List.of(restaurant));

        mockMvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("restaurants"))
                .andExpect(view().name("restaurants"));
    }
}
