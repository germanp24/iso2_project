package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Usuary;
import es.uclm.delivery.persistence.UsuaryDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuaryController.class)
class UsuaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuaryDAO usuaryDAO;

    @Test
    void testLoginForm() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("login"));
    }

    @Test
    void testVerifyUsuary_Success() throws Exception {
        Usuary usuary = new Usuary();
        usuary.setEmail("valid@example.com");
        usuary.setPassword("validpassword");
        usuary.setRole("CLIENT");

        Mockito.when(usuaryDAO.findByEmailAndPassword("valid@example.com", "validpassword"))
                .thenReturn(Optional.of(usuary));

        mockMvc.perform(post("/login")
                        .param("email", "valid@example.com")
                        .param("password", "validpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }

    @Test
    void testVerifyUsuary_Failure() throws Exception {
        Mockito.when(usuaryDAO.findByEmailAndPassword("invalid@example.com", "wrongpassword"))
                .thenReturn(Optional.empty());

        mockMvc.perform(post("/login")
                        .param("email", "invalid@example.com")
                        .param("password", "wrongpassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("error"));
    }
}
