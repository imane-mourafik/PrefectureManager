package org.example.projetgestionstockp.Controller;

import org.example.projetgestionstockp.Model.Annonce;
import org.example.projetgestionstockp.Service.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
public class AnnonceController {

    @Autowired
    private AnnonceService service;
    @Autowired
    private AnnonceService annonceService;

    @GetMapping("/admin/annonces")
    public String pageAdminAnnonces(Model model) {
        model.addAttribute("annonce", new Annonce());
        return "admin-annonce"; // à créer
    }

    @PostMapping("/admin/annonces")
    public String publierAnnonce(@ModelAttribute Annonce annonce, RedirectAttributes redirectAttributes) {
        annonce.setDatePublication(LocalDateTime.now());
        annonceService.publierAnnonce(annonce);
        redirectAttributes.addFlashAttribute("message", "Annonce publiée avec succès !");
        return "redirect:/admin/annonces";
    }

    @GetMapping("/employe/annonces")
    public String voirAnnoncesEmploye(Model model) {
        model.addAttribute("annonces", service.getAllAnnonces());
        return "employe-annonce";
    }

}
