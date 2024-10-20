package es.uclm.delivery.business.controller;

import es.uclm.delivery.business.entity.Usuary;
import es.uclm.delivery.persistence.UsuaryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuaryController {

    private static final Logger log = LoggerFactory.getLogger(Usuary.class);

    @Autowired
    private UsuaryDAO usuaryDAO;

    @GetMapping("/usuary")
    public String UsuaryForm(Model model) {

        model.addAttribute("usuary", new Usuary());

        log.info(usuaryDAO.findAll().toString());

        return "usuary_form";
    }

    @PostMapping("/usuary")
    public String usuarySubmit(@ModelAttribute Usuary usuary, Model model) {

        Usuary savedusuary = usuaryDAO.save(usuary);

        model.addAttribute("usuary", savedusuary);
        model.addAttribute("successMessage", "usuary saved successfully!");

        log.info("Saved deliveryService: " + savedusuary);

        return "usuary_form";
    }
}