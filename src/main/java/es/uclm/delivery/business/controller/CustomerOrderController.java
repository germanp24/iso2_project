package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.CustomerOrder;
import es.uclm.delivery.business.entity.MenuItem;
import es.uclm.delivery.business.entity.CreditCard;
import es.uclm.delivery.business.entity.Client;
import es.uclm.delivery.business.entity.Address;

import es.uclm.delivery.persistence.CustomerOrderDAO;
import es.uclm.delivery.persistence.MenuItemDAO;
import es.uclm.delivery.persistence.ClientDAO;
import es.uclm.delivery.persistence.CreditCardDAO;

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
    private final ClientDAO clientDAO;
    private final CreditCardDAO creditCardDAO;

    public CustomerOrderController(CustomerOrderDAO customerOrderDAO, MenuItemDAO menuItemDAO,
                                   ClientDAO clientDAO, CreditCardDAO creditCardDAO) {
        this.customerOrderDAO = customerOrderDAO;
        this.menuItemDAO = menuItemDAO;
        this.clientDAO = clientDAO;
        this.creditCardDAO = creditCardDAO;
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

    // Mapeo para manejar la selección del método de pago, solo accesible con DNI
    @GetMapping("/selectPaymentMethod")
    public String selectPaymentMethod(@RequestParam String dni, Model model) {

        // Si el usuario existe, proceder con la selección del método de pago
        model.addAttribute("dni", dni);
        return "selectPaymentMethod"; // Renderiza la vista para seleccionar el método de pago
    }

    // Mapeo para manejar la confirmación del método de pago
    @GetMapping("/confirmOrder")
    public String confirmOrder(@RequestParam String dni, @RequestParam String method, Model model) {
        model.addAttribute("dni", dni);
        // Redirigir a /enterAddress directamente
        if ("cash".equals(method)) {
            return "redirect:/enterAddress?method=cash&dni=" + dni; // Redirige correctamente a enterAddress
        } else if ("card".equals(method)) {
            return "redirect:/enterCardDetails?method=card&dni=" + dni; // Redirige a enterCardDetails si es tarjeta
        }
        return "redirect:/";
    }

    @GetMapping("/enterAddress")
    public String enterAddress(@RequestParam String dni, @RequestParam String method, Model model) {
        model.addAttribute("dni", dni); // Pasar el DNI al modelo
        model.addAttribute("method", method); // Pasar el método de pago al modelo
        return "enterAddress"; // Vista para ingresar la dirección
    }   
    
    @GetMapping("/enterCardDetails")
    public String enterCardDetails(@RequestParam String dni, @RequestParam String method, Model model) {
        model.addAttribute("dni", dni); // Pasar el DNI al modelo
        model.addAttribute("method", method); // Pasar el método de pago al modelo
        return "enterCardDetails"; // Vista para ingresar la dirección
    }

    // Nuevo método para guardar la dirección cuando se paga en efectivo
    @PostMapping("/saveAddress")
    public String saveAddress(
        @RequestParam String dni,
        @RequestParam String street,
        @RequestParam int number,
        @RequestParam(required = false) String floorNumber
    ) {
        // Construcción del objeto Address
        Address address = new Address();
        address.setStreet(street);
        address.setNumber(number);
        address.setFloorNumber(floorNumber);
        
        // Guardar la dirección (adaptar al uso del servicio correspondiente)
        System.out.println("Dirección recibida: " + address);
        
        // Redirigir a una página de éxito o próxima acción
        return "redirect:/success";
    }

    // Nuevo método para guardar los detalles de la tarjeta de crédito
    @PostMapping("/saveCardDetails")
    public String saveCardDetails(@RequestParam String dni, @RequestParam String address,
                                   @RequestParam String cardNumber, @RequestParam String expiryDate,
                                   @RequestParam String cvv, Model model) {
        // Obtener el cliente por DNI
        Client client = clientDAO.findByDni(dni);

        // Guardar la tarjeta de crédito
        CreditCard creditCard = new CreditCard(cardNumber, expiryDate, cvv, client);
        creditCardDAO.save(creditCard);

        // Crear la orden
        CustomerOrder order = new CustomerOrder();
        order.setDni(dni);
        order.setAddress(address);
        customerOrderDAO.save(order);

        return "redirect:/orderConfirmation";
    }
}
