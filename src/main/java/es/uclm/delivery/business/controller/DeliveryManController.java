package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.DeliveryMan;
import es.uclm.delivery.business.entity.Restaurant;
import es.uclm.delivery.business.entity.Usuary;
import es.uclm.delivery.persistence.DeliveryManDAO;
import es.uclm.delivery.persistence.RestaurantDAO;
import es.uclm.delivery.persistence.UsuaryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DeliveryManController {

    private static final Logger log = LoggerFactory.getLogger(DeliveryManController.class);

    private static final String REG_DELIVMAN = "registerDeliv";

    private final DeliveryManDAO deliveryManDAO;
    private final RestaurantDAO restaurantDAO;
    private final UsuaryDAO usuaryDAO;


    public DeliveryManController(DeliveryManDAO deliveryManDAO, RestaurantDAO restaurantDAO, UsuaryDAO usuaryDAO) {
        this.deliveryManDAO = deliveryManDAO;
        this.restaurantDAO = restaurantDAO;
        this.usuaryDAO = usuaryDAO;
    }


    @GetMapping("/registerDeliv")
    public String repartidorForm(Model model) {
        model.addAttribute(REG_DELIVMAN, new DeliveryMan());

        // Cargar todas las locations para el formulario
        List<String> locations = restaurantDAO.findAll()
                .stream()
                .map(Restaurant::getLocality)
                .distinct()
                .collect(Collectors.toList());
        model.addAttribute("locations", locations);

        if (log.isInfoEnabled()) {
            log.info("Localidades cargadas: {}", locations);
        }
        return REG_DELIVMAN;
    }

    @PostMapping("/registerDeliv")
    public String repartidorSubmit(@ModelAttribute DeliveryMan deliveryMan, @RequestParam String email,
            @RequestParam String password,@RequestParam String tipoAuto ,@RequestParam String localit  ,Model model) {

        if (deliveryManDAO.findByDni(deliveryMan.getDni()) != null) {
            model.addAttribute("errorMessage", "Error: Ya existe un repartidor registrado con este DNI.");
            model.addAttribute(REG_DELIVMAN, deliveryMan);
            return REG_DELIVMAN;
        }

        Usuary usuary = new Usuary(password, email, "DELIVERYMAN");
        deliveryMan.setUsuary(usuary);
        deliveryMan.setTipoAuto(tipoAuto);

        List<Restaurant> restaurant = restaurantDAO.findByLocality(localit);
        deliveryMan.getRestaurant().addAll(restaurant);

        DeliveryMan saveDeliveryMan =  deliveryManDAO.save(deliveryMan);

        model.addAttribute(REG_DELIVMAN, deliveryMan);
        model.addAttribute("successMessage", "¡Repartidor guardado con éxito!");

        return REG_DELIVMAN;
    }
}
