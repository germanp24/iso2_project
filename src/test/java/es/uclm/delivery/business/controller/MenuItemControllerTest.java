package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.MenuItem;
import es.uclm.delivery.persistence.MenuItemDAO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MenuItemController.class)
class MenuItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuItemDAO menuItemDAO;

    @Test
    void testMenuItemForm() throws Exception {
        mockMvc.perform(get("/menuRestaurants"))
                .andExpect(status().isOk())
                .andExpect(view().name("menuRestaurants"))
                .andExpect(model().attributeExists("menuItem"));
    }

    @Test
    void testSubmitMenuItem_Success() throws Exception {
        MenuItem newMenuItem = new MenuItem("Pizza Margherita", 8.99, "Italian");

        Mockito.when(menuItemDAO.save(Mockito.any(MenuItem.class))).thenReturn(newMenuItem);

        mockMvc.perform(post("/menuRestaurants")
                        .flashAttr("menuItem", newMenuItem))
                .andExpect(status().isOk())
                .andExpect(view().name("menuRestaurants"))
                .andExpect(model().attribute("successMessage", "menuItem saved successfully!"));
    }
}
