package es.uclm.delivery.business.controller;

import org.hibernate.mapping.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class IndexControllerTest {

    @Mock
    private Model model;

    @InjectMocks
    private IndexController indexController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIndexForm() {
        String viewName = indexController.IndexForm(model);

        verify(model, times(1)).addAttribute(eq("index"), any(Index.class));
        assertEquals("index", viewName);
    }

    @Test
    void testIndexSubmit() {
        Index index = new Index();
        String viewName = indexController.indexSubmit(index);

        assertEquals("index", viewName);
    }
}