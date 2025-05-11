package org.example.projetgestionstockp.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.projetgestionstockp.Model.Conge;
import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Service.CongeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminPage(HttpSession session, Model model) {
        Personne user = (Personne) session.getAttribute("user");

        if (user == null || !user.getRole().toString().equals("Administrateur")) {
            return "redirect:/accueil";
        }

        // On envoie l'objet user à la page
        model.addAttribute("admin", user);

        return "admin"; // correspond à admin.html
    }
    @GetMapping("/Admin/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "feedback"; // montre la page feedback.jsp après déconnexion
    }



    @Autowired
    private CongeService congeService;

    @GetMapping("/admin/confirmation")
    public String voirDemandes(Model model) {
        List<Conge> demandes = congeService.getAllDemandes();
        model.addAttribute("demandes", demandes);
        return "confirmationA"; // Page où l'administrateur peut voir et gérer les demandes
    }
    @PostMapping("/admin/traiter-demande")
    public String traiterDemande(@RequestParam("congeId") Long congeId, @RequestParam("action") String action, Model model) {
        boolean accepter = "accepter".equals(action);
        String message = congeService.traiterDemande(congeId, accepter);
        model.addAttribute("message", message);
        return "confirmationA"; // Retourne à la même page après traitement
    }


}
