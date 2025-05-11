package org.example.projetgestionstockp.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class EmployeController {
    @Autowired
    private PersonneRepository personneRepository;
    @GetMapping("/employe")
    public String employePage(HttpSession session, Model model) {
        Personne user = (Personne) session.getAttribute("user");

        if (user == null || !user.getRole().toString().equals("Employe")) {
            return "redirect:/accueil"; // Rediriger si non autorisé
        }

        model.addAttribute("employe", user);
        return "employe"; // Page employe.html
    }

    @GetMapping("/profil")
    public String voirProfil(HttpSession session, Model model) {
        Personne user = (Personne) session.getAttribute("user");

        if (user == null || !user.getRole().toString().equals("Employe")) {
            return "redirect:/accueil";
        }

        model.addAttribute("personne", user);
        return "profil"; // Page profil.html
    }

    @GetMapping("/employe/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/accueil";
    }

    @GetMapping("/modifier-informations")
    public String afficherFormulaireModification(HttpSession session, Model model) {
        Personne user = (Personne) session.getAttribute("user");

        if (user == null || !user.getRole().toString().equals("Employe")) {
            return "redirect:/accueil";
        }

        model.addAttribute("personne", user);
        return "modifier-informations"; // Formulaire de modification
    }


    @PostMapping("/modifier-informations")
    public String modifierInformations(@ModelAttribute Personne personne, HttpSession session) {
        Personne user = (Personne) session.getAttribute("user");

        if (user == null || !user.getRole().toString().equals("Employe")) {
            return "redirect:/accueil";
        }

        // Mise à jour des informations de l'employé
        user.setNom(personne.getNom());
        user.setPrenom(personne.getPrenom());
        user.setEmail(personne.getEmail());
        user.setTelephone(personne.getTelephone());
        user.setAdresse(personne.getAdresse());
        user.setNumCarte(personne.getNumCarte());
        user.setService(personne.getService());

        // Sauvegarde de l'employé mis à jour
        personneRepository.save(user);

        // Mettre à jour la session avec les nouvelles informations de l'utilisateur
        session.setAttribute("user", user);

        return "redirect:/profil"; // Rediriger vers la page de profil après la mise à jour
    }


}
