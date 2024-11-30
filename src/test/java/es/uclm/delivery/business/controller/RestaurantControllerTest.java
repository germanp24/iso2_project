package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.RestaurantDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class RestaurantControllerTest {

    private RestaurantDAO restaurantDAO; // Mock del DAO
    private RestaurantController restaurantController; // Controlador a probar
    private Model model; // Mock para el modelo

    @BeforeEach
    void setUp() {
        restaurantDAO = mock(RestaurantDAO.class); // Simulación del DAO
        restaurantController = new RestaurantController(restaurantDAO); // Inyectamos el DAO simulado al controlador
        model = mock(Model.class); // Simulación del modelo
    }

    @Test
    void testRestaurantForm() {
        // Configuración de los mocks
        when(restaurantDAO.findAll()).thenReturn(new ArrayList<>());

        // Llamada al método
        String viewName = restaurantController.restaurantForm(model);

        // Verificaciones
        verify(model).addAttribute(eq("cifRestaurant"), any(Restaurant.class)); // Se añade la entidad al modelo
        verify(restaurantDAO).findAll(); // Se llama al método DAO
        assertEquals("cifRestaurant", viewName); // Verificamos la vista retornada
    }

    @Test
    void testRestaurantSubmit_WithExistingRestaurant() {
        // Configuración del mock
        Restaurant existingRestaurant = new Restaurant();
        existingRestaurant.setCif("12345678A");

        when(restaurantDAO.findByCif("12345678A")).thenReturn(existingRestaurant);

        Restaurant restaurant = new Restaurant();
        restaurant.setCif("12345678A");

        // Llamada al método
        String viewName = restaurantController.restaurantSubmit(restaurant, model);

        // Verificaciones
        verify(model).addAttribute("cifRestaurant", restaurant);
        verify(model).addAttribute("successMessage", "¡El CIF ya está registrado!");
        verify(restaurantDAO).findByCif("12345678A");
        assertEquals("cifRestaurant", viewName); // Validamos que la vista sea la esperada
    }

    @Test
    void testRestaurantSubmit_WithNewRestaurant() {
        // Configuración del mock
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setCif("87654321B");

        when(restaurantDAO.findByCif("87654321B")).thenReturn(null);
        when(restaurantDAO.save(newRestaurant)).thenReturn(newRestaurant);

        // Llamada al método
        String viewName = restaurantController.restaurantSubmit(newRestaurant, model);

        // Verificaciones
        verify(model).addAttribute("cifRestaurant", newRestaurant);
        verify(model).addAttribute("successMessage", "¡Restaurante guardado con éxito!");
        verify(restaurantDAO).save(newRestaurant);
        assertEquals("cifRestaurant", viewName); // Validamos la vista retornada
    }
}
