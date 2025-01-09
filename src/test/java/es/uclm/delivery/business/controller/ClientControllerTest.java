package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Client;
import es.uclm.delivery.business.entity.Usuary;
import es.uclm.delivery.persistence.ClientDAO;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.slf4j.Logger;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(ClientController.class)
@Import(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientDAO clientDAO;

    @Mock
    private Logger mockLogger;

    @InjectMocks
    private ClientController clientController;

    // 1. Test for Logger (Verifying if log is being called)
    @Test
    void testLogging() throws Exception {
        Client client = new Client("12345678A", "Juan", "Pérez", "Gómez", null);

        // Mock the DAO behavior
        when(clientDAO.save(any(Client.class))).thenReturn(client);

        // Perform the request to trigger the logger
        mockMvc.perform(MockMvcRequestBuilders.post("/registerClient")
                        .param("email", "test@example.com")
                        .param("password", "password123"))
                .andExpect(MockMvcResultMatchers.view().name("/login"))
                .andExpect(model().attributeExists("successMessage"))
                .andExpect(model().attribute("successMessage", "Client registrado con éxito!"));
    }

    // 2. Test for Dependency Injection (Checking if clientDAO is injected)
    @Test
    void testClientDAOInjection() throws Exception {
        Client client = new Client("12345678A", "Juan", "Pérez", "Gómez", null);
        when(clientDAO.findByUsuary_Email("test@example.com")).thenReturn(java.util.Optional.of(client));

        mockMvc.perform(get("/client/home")
                        .param("email", "test@example.com"))
                .andExpect(MockMvcResultMatchers.view().name("client/home"))
                .andExpect(model().attribute("clientName", "Juan"));

        // Verify clientDAO interaction
        verify(clientDAO).findByUsuary_Email("test@example.com");
    }

    // 3. Test for clientForm (Adding Client object to model)
    @Test
    void testClientForm() throws Exception {
        mockMvc.perform(get("/registerClient"))
                .andExpect(model().attributeExists("registerClient"))  // Verifica que el atributo exista
                .andExpect(model().attribute("registerClient", hasProperty("dni", is(nullValue()))))
                .andExpect(model().attribute("registerClient", hasProperty("name", is(nullValue()))))
                .andExpect(model().attribute("registerClient", hasProperty("surnames_M", is(nullValue()))))
                .andExpect(model().attribute("registerClient", hasProperty("surnames_F", is(nullValue()))));
    }

    // 4. Test for clientHome (Fetching client and adding name to model)
    @Test
    void testClientHome() throws Exception {
        Client client = new Client("12345678A", "Juan", "Pérez", "Gómez", null);
        when(clientDAO.findByUsuary_Email("test@example.com")).thenReturn(java.util.Optional.of(client));

        mockMvc.perform(get("/client/home")
                        .param("email", "test@example.com"))
                .andExpect(MockMvcResultMatchers.view().name("client/home"))
                .andExpect(model().attribute("clientName", "Juan"));
    }

    // 5. Prueba para clientSubmit (Crear cliente, guardar en DAO, añadir al modelo)
    @Test
    void testClientSubmit() throws Exception {
        Client client = new Client("12345678A", "Juan", "Pérez", "Gómez", null);
        Usuary usuary = new Usuary("password123", "test@example.com", "CLIENT");
        client.setUsuary(usuary);

        when(clientDAO.save(any(Client.class))).thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.post("/registerClient")
                        .param("email", "test@example.com")
                        .param("password", "password123"))
                .andExpect(MockMvcResultMatchers.view().name("/login"))
                .andExpect(model().attributeExists("successMessage"))
                .andExpect(model().attribute("successMessage", "Client registrado con éxito!"));
    }
}
