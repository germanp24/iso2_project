package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.CartItem;
import es.uclm.delivery.business.entity.MenuItem;
import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.persistence.MenuItemDAO;
import es.uclm.delivery.persistence.RestaurantDAO;
import es.uclm.delivery.persistence.UsuaryDAO;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuItemController {

    private static final Logger log = LoggerFactory.getLogger(MenuItemController.class);

    private final MenuItemDAO menuItemDAO;
    private final RestaurantDAO restaurantDAO;

    public MenuItemController(MenuItemDAO menuItemDAO, RestaurantDAO restaurantDAO) {
        this.menuItemDAO = menuItemDAO;
        this.restaurantDAO = restaurantDAO;
    }

    @GetMapping("/restaurant/{cif}")
    public String restaurantDetails(@PathVariable String cif, HttpSession session, Model model) {
        log.info("Cargando detalles del restaurante con CIF: {}", cif);

        // Buscar el restaurante por CIF
        Restaurant restaurant = restaurantDAO.findById(cif)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante no encontrado"));

        // Obtener los menús asociados al restaurante
        List<MenuItem> menuItems = menuItemDAO.findByRestaurantCif(cif);

        // Obtener el carrito de la sesión (si existe) solo si el usuario está autenticado
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        double totalPrice = cart.stream().mapToDouble(CartItem::getTotalPrice).sum();

        // Añadir datos al modelo
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("menuItems", menuItems);
        model.addAttribute("cart", cart); // Añadir carrito al modelo
        model.addAttribute("totalPrice", totalPrice);

        return "menuDetails"; // Asegúrate de que tu vista sea la correcta
    }

    @GetMapping("/addMenuRestaurant")
    public String menuItemForm(Model model) {
        model.addAttribute("menuItem", new MenuItem());
        model.addAttribute("menuItems", menuItemDAO.findAll());
        model.addAttribute("restaurants", restaurantDAO.findAll());

        return "addMenuRestaurant"; // Asegúrate de que esta vista esté configurada
    }


    @PostMapping("/addMenuRestaurant")
    public String menuItemSubmit(@ModelAttribute MenuItem menuItem, Model model) {
        try {
            // Validar si el restaurante existe
            Restaurant restaurant = restaurantDAO.findById(menuItem.getRestaurant().getCif())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid restaurant selected"));

            menuItem.setRestaurant(restaurant);

            // Asociar los contenidos al menú
            menuItem.getMenuContents().forEach(content -> content.setMenuItem(menuItem));

            // Guardar el MenuItem
            menuItemDAO.save(menuItem);

            model.addAttribute("successMessage", "Menu item saved successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error saving menu item: " + e.getMessage());
            log.error("Error saving menu item", e);
        }
        return "redirect:/addMenuRestaurant"; // Redirigir para evitar reenvío del formulario
    }

}
