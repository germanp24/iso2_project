package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.RestaurantAdministrator;
import es.uclm.delivery.persistence.RestaurantAdministratorDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantAdministratorController.class)
class RestaurantAdministratorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantAdministratorDAO restaurantAdministratorDAO;

    @Test
    void testAdministratorForm() throws Exception {
        mockMvc.perform(get("/registerAdmin"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerAdmin"))
                .andExpect(model().attributeExists("registerAdmin"));
    }

    @Test
    void testSubmitAdministrator_Success() throws Exception {
        RestaurantAdministrator newAdmin = new RestaurantAdministrator();
        newAdmin.setDni("12345678A");
        newAdmin.setName("John");
        newAdmin.setSurnames("Doe");

        Mockito.when(restaurantAdministratorDAO.save(Mockito.any(RestaurantAdministrator.class))).thenReturn(newAdmin);

        mockMvc.perform(post("/registerAdmin")
                        .param("email", "admin@example.com")
                        .param("password", "password123")
                        .flashAttr("registerAdmin", newAdmin))
                .andExpect(status().isOk())
                .andExpect(view().name("registerAdmin"))
                .andExpect(model().attribute("successMessage", "restaurantAdministrator saved successfully!"));
    }
}
