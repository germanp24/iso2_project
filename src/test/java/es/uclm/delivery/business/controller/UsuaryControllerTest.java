package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Usuary;
import es.uclm.delivery.persistence.UsuaryDAO;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UsuaryControllerTest {

    @Mock
    private UsuaryDAO usuaryDAO;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @InjectMocks
    private UsuaryController usuaryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUsuaryForm() {
        String viewName = usuaryController.usuaryForm(model);

        verify(model, times(1)).addAttribute(eq("login"), any(Usuary.class));
        assertEquals("login", viewName);
    }
}