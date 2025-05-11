package org.example.projetgestionstockp.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.projetgestionstockp.Model.Conge;
import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Service.CongeService;
import org.example.projetgestionstockp.Service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employe")
public class CongeController {

    @Autowired
    private CongeService congeService;

    @Autowired
    private PersonneService personneService;

    @GetMapping("/demande-conge")
    public String showForm(Model model) {
        model.addAttribute("conge", new Conge());
        return "demande-conge";
    }
    @PostMapping("/demande-conge")
    public String envoyerDemande(@ModelAttribute("conge") Conge conge, HttpSession session, Model model) {
        // Récupérer l'utilisateur connecté
        Personne employe = (Personne) session.getAttribute("user");

        if (employe == null) {
            return "redirect:/accueil";
        }

        // Associer l'employé à la demande
        conge.setEmploye(employe);

        // Envoie la demande et récupère la décision
        String decision = congeService.envoyerDemande(conge);

        model.addAttribute("decision", decision);
        model.addAttribute("messageSuccess", "Votre demande a été envoyée avec succès !");
        return "demande-conge";
    }





    @GetMapping("/confirmation")
    public String confirmationpage(HttpSession session, Model model) {
        Personne employe = (Personne) session.getAttribute("user");

        if (employe == null) {
            return "redirect:/accueil"; // Rediriger si l'utilisateur n'est pas connecté
        }

        // Récupérer les demandes de congé de cet employé
        List<Conge> conges = congeService.getDemandesParEmploye(employe);

        model.addAttribute("conges", conges);
        return "ConfirmationE"; // Affiche la page avec les réponses
    }

}
