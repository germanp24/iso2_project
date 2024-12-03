package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.DeliveryMan;
import es.uclm.delivery.persistence.DeliveryManDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DeliveryManController.class)
class DeliveryManControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeliveryManDAO deliveryManDAO;

    @Test
    void testDeliveryManForm() throws Exception {
        mockMvc.perform(get("/registerDeliv"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerDeliv"))
                .andExpect(model().attributeExists("registerDeliv"));
    }

    @Test
    void testSubmitDeliveryMan_Success() throws Exception {
        DeliveryMan newDeliveryMan = new DeliveryMan();
        newDeliveryMan.setNif("12345678Z");
        newDeliveryMan.setName_a("John");
        newDeliveryMan.setSurnames("Doe");
        newDeliveryMan.setEfficiency(85);
        newDeliveryMan.setTransport("Moto");

        Mockito.when(deliveryManDAO.save(Mockito.any(DeliveryMan.class))).thenReturn(newDeliveryMan);

        mockMvc.perform(post("/registerDeliv")
                        .param("email", "delivery@example.com")
                        .param("password", "password123")
                        .flashAttr("registerDeliv", newDeliveryMan))
                .andExpect(status().isOk())
                .andExpect(view().name("registerDeliv"))
                .andExpect(model().attribute("successMessage", "DeliveryMan registrado con éxito!"));
    }
}
