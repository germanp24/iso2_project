package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Client;
import es.uclm.delivery.persistence.ClientDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientDAO clientDAO;

    @Test
    void testClientForm() throws Exception {
        mockMvc.perform(get("/registerClient"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerClient"))
                .andExpect(model().attributeExists("registerClient"));
    }

    @Test
    void testSubmitClient_Success() throws Exception {
        Client newClient = new Client();
        newClient.setName("John");
        newClient.setSurnames("Doe");
        newClient.setDni("12345678A");

        Mockito.when(clientDAO.save(Mockito.any(Client.class))).thenReturn(newClient);

        mockMvc.perform(post("/registerClient")
                        .param("email", "client@example.com")
                        .param("password", "password123")
                        .flashAttr("registerClient", newClient))
                .andExpect(status().isOk())
                .andExpect(view().name("registerClient"))
                .andExpect(model().attribute("successMessage", "Client registrado con éxito!"));
    }
}
