package org.example.projetgestionstockp.Controller;

import org.example.projetgestionstockp.Model.Reclamation;
import org.example.projetgestionstockp.Service.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller

public class ReclamationController {
    @Autowired
    private ReclamationService reclamationService;

    @GetMapping("/employe/faire-reclamation")
    public String afficherFormulaireReclamation(Model model) {
        model.addAttribute("reclamation", new Reclamation());
        return "faire-reclamation";
    }

    @GetMapping("/admin/reclamations")
    public String afficherListeReclamations(Model model) {
        model.addAttribute("reclamations", reclamationService.getAllReclamation());
        return "admin-reclamations";
    }
    @PostMapping("/employe/faire-reclamation")
    public String soumettreReclamation(Reclamation reclamation, RedirectAttributes redirectAttributes) {
        reclamation.setVisibleParAdmin(false);
        redirectAttributes.addFlashAttribute("message", "Réclamation envoyer avec succé !");

        reclamationService.saveReclamation(reclamation);
        return "redirect:/employe";
    }

}
