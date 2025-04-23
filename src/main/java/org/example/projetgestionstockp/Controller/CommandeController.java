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

        model.addAttribute("magasinier", user);  // Ajout du magasinier au modèle
        return "gerer-commande"; // Affiche la page de gestion des commandes
    }

    @GetMapping("/ajouter-commande")
    public String formAjouterCommande(Model model) {
        model.addAttribute("commande", new Commande());
        model.addAttribute("fournisseurs", fournisseurRepository.findAll());
        return "ajouter-commande";
    }


    @PostMapping("/ajouter-commande")
    public String ajouterCommande(@ModelAttribute Commande commande, @RequestParam("fournisseurId") Long fournisseurId) {
        Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElse(null);

        if (fournisseur != null) {
            commande.setFournisseur(fournisseur);
            commandeRepository.save(commande);
        }
        notificationService.ajouterNotification(" Nouvelle commande enregistrée (#" + commande.getId() + ")", null);

        return "redirect:/consulter-commande"; // Attention ici, c'est bien `/commandes` ou `/consulter-commandes` ?
    }


    @GetMapping("/consulter-commande")
    public String consulterCommandes(Model model) {
        model.addAttribute("commandes", commandeRepository.findAll());
        return "consulter-commande";
    }


    @GetMapping("/supprimer-commande/{id}")
    public String supprimerCommande(@PathVariable Long id) {
        commandeRepository.findById(id).ifPresent(commande -> {
            commandeRepository.deleteById(id);
            notificationService.ajouterNotification(
                    "Commande supprimée (#" + commande.getId() + ")", null);
        });

        return "redirect:/consulter-commande";
    }

    // Afficher le formulaire de modification d'une commande
    @GetMapping("/modifier-commande/{id}")
    public String modifierCommande(@PathVariable Long id, Model model) {
        Optional<Commande> commande = commandeRepository.findById(id);

        if (commande.isPresent()) {
            model.addAttribute("commande", commande.get());
            model.addAttribute("fournisseurs", fournisseurRepository.findAll());
            return "modifier-commande"; // Retourne la vue du formulaire de modification
        } else {
            return "redirect:/consulter-commande"; // Si la commande n'existe pas, rediriger vers la liste des commandes
        }
    }
    // Enregistrer les modifications de la commande
    @PostMapping("/modifier-commande/{id}")
    public String modifierCommande(@PathVariable Long id, @ModelAttribute Commande commande, @RequestParam("fournisseurId") Long fournisseurId) {
        Optional<Commande> existingCommande = commandeRepository.findById(id);

        if (existingCommande.isPresent()) {
            Commande updatedCommande = existingCommande.get();
            updatedCommande.setDate(commande.getDate());
            updatedCommande.setStatut(commande.getStatut());

            // Vous pouvez également mettre à jour le fournisseur si nécessaire
            Fournisseur fournisseur = fournisseurRepository.findById(fournisseurId).orElse(null);
            if (fournisseur != null) {
                updatedCommande.setFournisseur(fournisseur);
            }

            commandeRepository.save(updatedCommande);
        }

        return "redirect:/consulter-commande"; // Redirige vers la page de gestion des commandes après la modification
    }




}
