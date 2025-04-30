package org.example.projetgestionstockp.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.projetgestionstockp.Model.Commande;
import org.example.projetgestionstockp.Model.Fournisseur;
import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Repository.CommandeRepository;
import org.example.projetgestionstockp.Repository.FournisseurRepository;
import org.example.projetgestionstockp.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CommandeController {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private NotificationService notificationService;

    // Afficher le menu de gestion des commandes
    @GetMapping("/commandes")
    public String afficherMenuGestionCommandes(HttpSession session, Model model) {
        Personne user = (Personne) session.getAttribute("user");

        if (user == null || !user.getRole().toString().equals("Magasinier")) {
            return "redirect:/accueil";
        }

        model.addAttribute("magasinier", user);
        return "gerer-commande";
    }

    // Formulaire d'ajout
    @GetMapping("/ajouter-commande")
    public String formAjouterCommande(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) return "redirect:/accueil";

        model.addAttribute("commande", new Commande());
        model.addAttribute("fournisseurs", fournisseurRepository.findAll());
        return "ajouter-commande";
    }




    // Consulter les commandes
    @GetMapping("/consulter-commande")
    public String consulterCommandes(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) return "redirect:/accueil";

        model.addAttribute("commandes", commandeRepository.findAll());
        return "consulter-commande";
    }

    // Traitement de l'ajout de commande
    @PostMapping("/ajouter-commande")
    public String ajouterCommande(@ModelAttribute Commande commande, @RequestParam("fournisseurId") Long fournisseurId, HttpSession session) {
        Personne user = (Personne) session.getAttribute("user");  // Récupération de l'utilisateur

        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElse(null);

        if (fournisseur != null) {
            commande.setFournisseur(fournisseur);
            commandeRepository.save(commande);

            String utilisateurNom = user != null ? user.getNom() : "Inconnu"; // Nom de l'utilisateur
            notificationService.ajouterNotification("Nouvelle commande enregistrée (#" + commande.getId() + ") par " + utilisateurNom, null);
        }

        return "redirect:/consulter-commande";
    }

    // Suppression de la commande
    @GetMapping("/supprimer-commande/{id}")
    public String supprimerCommande(@PathVariable Long id, HttpSession session) {
        Personne user = (Personne) session.getAttribute("user");  // Récupération de l'utilisateur

        commandeRepository.findById(id).ifPresent(commande -> {
            commandeRepository.deleteById(id);

            String utilisateurNom = user != null ? user.getNom() : "Inconnu"; // Nom de l'utilisateur

        });

        return "redirect:/consulter-commande";
    }


    // Afficher le formulaire de modification
    @GetMapping("/modifier-commande/{id}")
    public String modifierCommande(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("user") == null) return "redirect:/accueil";

        Optional<Commande> commande = commandeRepository.findById(id);
        if (commande.isPresent()) {
            model.addAttribute("commande", commande.get());
            model.addAttribute("fournisseurs", fournisseurRepository.findAll());
            return "modifier-commande";
        } else {
            return "redirect:/consulter-commande";
        }
    }

    // Sauvegarder les modifications
    @PostMapping("/modifier-commande/{id}")
    public String modifierCommande(@PathVariable Long id, @ModelAttribute Commande commande, @RequestParam("fournisseurId") Long fournisseurId) {
        Optional<Commande> existingCommande = commandeRepository.findById(id);

        if (existingCommande.isPresent()) {
            Commande updatedCommande = existingCommande.get();
            updatedCommande.setDate(commande.getDate());
            updatedCommande.setStatut(commande.getStatut());

            Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElse(null);
            if (fournisseur != null) {
                updatedCommande.setFournisseur(fournisseur);
            }

            commandeRepository.save(updatedCommande);
        }

        return "redirect:/consulter-commande";
    }
}
