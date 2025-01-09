package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.*;

import es.uclm.delivery.persistence.*;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerOrderController {

    private static final Logger log = LoggerFactory.getLogger(CustomerOrderController.class);
    private static final String ERROR = "error";

    private final MenuItemDAO menuItemDAO;

    public CustomerOrderController(MenuItemDAO menuItemDAO) {
        this.menuItemDAO = menuItemDAO;

    }

    @PostMapping("/orderDetails")
    public String addToCart(@RequestParam String menuItemId, @RequestParam int quantity, HttpSession session, Model model) {

        MenuItem menuItem = menuItemDAO.findById(menuItemId)
                .orElseThrow(() -> new IllegalArgumentException("Menu item not found"));

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        Optional<CartItem> existingItem = cart.stream()
                .filter(item -> item.getMenuItem().getId_menu().equals(Long.parseLong(menuItemId)))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            cart.add(new CartItem(menuItem, quantity));
        }
        session.setAttribute("cart", cart);

        return "redirect:/restaurant/" + menuItem.getRestaurant().getCif();
    }

    @GetMapping("/selectPaymentMethod")
    public String selectPaymentMethod(HttpSession session, Model model) {

        Usuary loggedInUser = (Usuary) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            model.addAttribute(ERROR, "Debes iniciar sesión para agregar productos al carrito.");
            return "redirect:/login";
        }

        // Si el usuario existe, proceder con la selección del método de pago
        model.addAttribute("loggedInUser", loggedInUser);
        return "selectPaymentMethod"; // Renderiza la vista para seleccionar el método de pago
    }

    @GetMapping("/confirmOrder")
    public String confirmOrder(@RequestParam String method, HttpSession session, Model model) {

        Client loggedInClient = (Client) session.getAttribute("loggedInClient");

        if (loggedInClient == null) {
            model.addAttribute(ERROR, "Debes iniciar sesión para confirmar tu pedido.");
            return "redirect:/login";
        }

        String dni = loggedInClient.getDni();
        model.addAttribute("dni", dni);

        if ("cash".equals(method)) {
            return "redirect:/enterAddress?method=cash&dni=" + dni;
        } else if ("card".equals(method)) {
            return "redirect:/enterCardDetails?method=card&dni=" + dni;
        }

        return "redirect:/";
    }


    @GetMapping("/enterAddress")
    public String enterAddress(@RequestParam String method, HttpSession session, Model model) {
        Client loggedInClient = (Client) session.getAttribute("loggedInClient");

        if (loggedInClient == null) {
            model.addAttribute(ERROR, "Debes iniciar sesión para continuar.");
            return "redirect:/login";
        }

        String dni = loggedInClient.getDni();
        model.addAttribute("dni", dni);
        model.addAttribute("method", method);

        return "enterAddress";
    }

    @GetMapping("/enterCardDetails")
    public String enterCardDetails(@RequestParam String method, HttpSession session, Model model) {

        Client loggedInClient = (Client) session.getAttribute("loggedInClient");

        if (loggedInClient == null) {
            model.addAttribute(ERROR, "Debes iniciar sesión para continuar.");
            return "redirect:/login";
        }

        String dni = loggedInClient.getDni();
        model.addAttribute("dni", dni);
        model.addAttribute("method", method);

        return "enterCardDetails";
    }


    @PostMapping("/saveAddress")
    public String saveAddress(
            @RequestParam String street,
            @RequestParam int number,
            @RequestParam(required = false) String floorNumber,
            HttpSession session
    ) {

        Client loggedInClient = (Client) session.getAttribute("loggedInClient");

        if (loggedInClient == null) {
            return "redirect:/login";
        }

        Address address = new Address();
        address.setStreet(street);
        address.setNumber(number);
        address.setFloorNumber(floorNumber);

        return "orderConfirmation";
    }


    // Nuevo método para guardar los detalles de la tarjeta de crédito
    @PostMapping("/saveCardDetails")
    public String saveCardDetails(
            @RequestParam String street,
            @RequestParam int number,
            @RequestParam(required = false) String floorNumber,
            @RequestParam String cardNumber,
            @RequestParam String expiryDate,
            @RequestParam String cvv,
            HttpSession session
    ) {

        Client loggedInClient = (Client) session.getAttribute("loggedInClient");

        if (loggedInClient == null) {

            return "redirect:/login";
        }

        String dni = loggedInClient.getDni();

        Address address = new Address();
        address.setStreet(street);
        address.setNumber(number);
        address.setFloorNumber(floorNumber);

        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(cardNumber);
        creditCard.setCardExpiry(expiryDate);
        creditCard.setCardCvv(cvv);

        return "orderConfirmation";
    }



}
