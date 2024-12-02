package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.RestaurantDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantDAO restaurantDAO;

    @Test
    void testRestaurantForm() throws Exception {
        mockMvc.perform(get("/cifRestaurant"))
                .andExpect(status().isOk())
                .andExpect(view().name("cifRestaurant"))
                .andExpect(model().attributeExists("cifRestaurant"));
    }

    @Test
    void testSubmitNewRestaurant() throws Exception {
        Restaurant newRestaurant = new Restaurant("CIF123", "Restaurant A", "Admin1", "url/image.jpg", "Main St", "City A");

        Mockito.when(restaurantDAO.findByCif("CIF123")).thenReturn(null);
        Mockito.when(restaurantDAO.save(Mockito.any(Restaurant.class))).thenReturn(newRestaurant);

        mockMvc.perform(post("/cifRestaurant")
                        .flashAttr("restaurant", newRestaurant))
                .andExpect(status().isOk())
                .andExpect(view().name("cifRestaurant"))
                .andExpect(model().attribute("successMessage", "¡Restaurante guardado con éxito!"));
    }

    @Test
    void testSubmitDuplicateRestaurant() throws Exception {
        Restaurant existingRestaurant = new Restaurant("CIF123", "Restaurant A", "Admin1", "url/image.jpg", "Main St", "City A");

        Mockito.when(restaurantDAO.findByCif("CIF123")).thenReturn(existingRestaurant);

        mockMvc.perform(post("/cifRestaurant")
                        .flashAttr("restaurant", existingRestaurant))
                .andExpect(status().isOk())
                .andExpect(view().name("cifRestaurant"))
                .andExpect(model().attribute("successMessage", "¡El CIF ya está registrado!"));
    }

    @Test
    void testShowRestaurants() throws Exception {
        List<Restaurant> restaurants = List.of(
                new Restaurant("CIF123", "Restaurant A", "Admin1", "url/image.jpg", "Main St", "City A"),
                new Restaurant("CIF456", "Restaurant B", "Admin2", "url/image2.jpg", "Side St", "City B")
        );

        Mockito.when(restaurantDAO.findAll()).thenReturn(restaurants);

        mockMvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(view().name("restaurants"))
                .andExpect(model().attributeExists("restaurants"))
                .andExpect(model().attribute("restaurants", restaurants));
    }
}
