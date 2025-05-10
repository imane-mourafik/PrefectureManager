package org.example.projetgestionstockp.Controller;

import org.example.projetgestionstockp.Model.Fournisseur;
import org.example.projetgestionstockp.Repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class FournisseurController {

    @Autowired
    private FournisseurRepository fournisseurRepository;


    @GetMapping("/gerer-fournisseur")
    public String gererFournisseurs() {
        return "gerer-fournisseur";
    }


    @GetMapping("/consulter-fournisseur")
    public String consulterFournisseurs(Model model) {
        List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
        model.addAttribute("fournisseurs", fournisseurs);
        return "consulter-fournisseur";
    }

    // Afficher le formulaire d'ajout
    @GetMapping("/ajouter-fournisseur")
    public String afficherFormulaireAjoutFournisseur(Model model) {
        model.addAttribute("fournisseur", new Fournisseur());
        return "ajouter-fournisseur";
    }

    // Ajouter un fournisseur
    @PostMapping("/ajouter-fournisseur")
    public String ajouterFournisseur(@ModelAttribute Fournisseur fournisseur) {
        fournisseurRepository.save(fournisseur);
        return "redirect:/ajouter-fournisseur";
    }

    // Afficher le formulaire de modification d'un fournisseur
    @GetMapping("/modifier-fournisseur/{id}")
    public String afficherFormulaireModificationFournisseur(@PathVariable Long id, Model model) {
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);
        if (fournisseur.isPresent()) {
            model.addAttribute("fournisseur", fournisseur.get());
            return "modifier-fournisseur";
        } else {
            return "redirect:/consulter-fournisseur";
        }
    }


    @PostMapping("/modifier-fournisseur/{id}")
    public String modifierFournisseur(@PathVariable Long id, @ModelAttribute Fournisseur fournisseur) {
        fournisseur.setId(id);
        fournisseurRepository.save(fournisseur);
        return "redirect:/consulter-fournisseur";
    }


    @GetMapping("/supprimer-fournisseur/{id}")
    public String supprimerFournisseur(@PathVariable Long id) {
        fournisseurRepository.deleteById(id);
        return "redirect:/consulter-fournisseur";
    }
    @GetMapping("/rechercher-fournisseurs")
    public String rechercherFournisseurs(@RequestParam("motCle") String motCle, Model model) {
        List<Fournisseur> fournisseurs;
        if (motCle != null && !motCle.isEmpty()) {
            fournisseurs = fournisseurRepository.findByNomContainingIgnoreCase(motCle);
        } else {
            fournisseurs = fournisseurRepository.findAll();
        }

        model.addAttribute("fournisseurs", fournisseurs);
        model.addAttribute("motCle", motCle);
        return "consulter-fournisseur";
    }


}
