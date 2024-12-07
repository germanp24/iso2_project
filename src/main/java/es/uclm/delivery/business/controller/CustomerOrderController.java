package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.CustomerOrder;
import es.uclm.delivery.business.entity.MenuItem;
import es.uclm.delivery.persistence.CustomerOrderDAO;
import es.uclm.delivery.persistence.MenuItemDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CustomerOrderController {

    private final CustomerOrderDAO customerOrderDAO;
    private final MenuItemDAO menuItemDAO;

    public CustomerOrderController(CustomerOrderDAO customerOrderDAO, MenuItemDAO menuItemDAO) {
        this.customerOrderDAO = customerOrderDAO;
        this.menuItemDAO = menuItemDAO;
    }

    // Mapeo para acceder a la página de detalles del pedido sin necesidad de DNI
    @GetMapping("/orderDetails")
    public String showOrderDetails(Model model) {
        // Aquí no hay un DNI específico, simplemente se muestran todos los productos seleccionados
        List<MenuItem> selectedMenuItems = menuItemDAO.findAll();  // Obtener todos los productos
        double totalPrice = selectedMenuItems.stream()
                .mapToDouble(MenuItem::getPrice)
                .sum();

        model.addAttribute("menuItems", selectedMenuItems);
        model.addAttribute("totalPrice", totalPrice); // Precio total
        return "orderDetails"; // Devuelve la vista orderDetails.html
    }

    // Mapeo para acceder a la página de detalles con el DNI en la URL
    @GetMapping("/orderDetails/{dni}")
    public String showOrderDetailsByDni(@PathVariable String dni, Model model) {
        // Aquí el DNI es necesario para filtrar los productos asociados
        List<CustomerOrder> orders = customerOrderDAO.findAll().stream()
                .filter(order -> order.getDni().equals(dni))
                .collect(Collectors.toList());

        // Lista de productos seleccionados por el cliente con ese DNI
        List<MenuItem> selectedMenuItems = new ArrayList<>();
        for (CustomerOrder order : orders) {
            String[] orderedFoods = order.getOrderedFood().split(",");
            for (String foodName : orderedFoods) {
                menuItemDAO.findById(foodName).ifPresent(selectedMenuItems::add);
            }
        }

        // Calcular el precio total
        double totalPrice = selectedMenuItems.stream()
                .mapToDouble(MenuItem::getPrice)
                .sum();

        model.addAttribute("menuItems", selectedMenuItems);
        model.addAttribute("totalPrice", totalPrice); // Precio total
        model.addAttribute("dni", dni); // Pasar el DNI si lo tienen

        return "orderDetails"; // Devuelve la vista orderDetails.html
    }
}
