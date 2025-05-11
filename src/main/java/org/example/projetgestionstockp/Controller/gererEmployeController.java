package org.example.projetgestionstockp.Controller;

import jakarta.validation.Valid;
import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Repository.PersonneRepository;
import org.example.projetgestionstockp.Service.PersonneService;
import org.example.projetgestionstockp.Utils.PythonSalairePredictor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gererEmployes")
public class gererEmployeController {

    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private PersonneService personneService;


    @GetMapping
    public String gererEmployes(Model model) {

        List<Personne> employes = personneRepository.findAll();
        model.addAttribute("employes", employes);
        return "gererEmployes";
    }


    @GetMapping("/ajouter-employe")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("personne", new Personne());
        return "ajouter-employe";
    }


    @PostMapping("/ajouter-employe")
    public String ajouterEmploye(@ModelAttribute Personne personne) {
        // Prédire le salaire avec l'IA
        double salaire = PythonSalairePredictor.calculerSalaire(
                personne.getExperience(),
                personne.getService(),  // Assure-toi que le service est bien défini
                personne.getRole().name()
        );

        // Appliquer le salaire prédit à la personne
        personne.setSalaire(salaire);

        // Enregistrer dans la base
        personneRepository.save(personne);

        return "redirect:/gererEmployes";
    }

    @GetMapping("/consulter-employe")
    public String consulterEmployes(Model model) {
        model.addAttribute("employes", personneRepository.findAll());
        return "consulter-employe";
    }


    // Supprimer un employé avec justification
    @PostMapping("/supprimer/{id}")
    public String supprimerEmploye(
            @PathVariable Long id,
            @RequestParam("justification") String justification) {

        personneService.supprimerPersonne(id, justification);
        return "redirect:/gererEmployes/consulter-employe";
    }




    @GetMapping("/modifier/{id}")
    public String afficherFormulaireModification(@PathVariable Long id, Model model) {
        Personne employe = personneRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employé non trouvé"));
        model.addAttribute("personne", employe);
        return "modifier-employe";
    }

    // Mettre à jour un employé
    @PostMapping("/modifier-employe")  // Corrigé ici
    public String modifierEmploye(@Valid @ModelAttribute("personne") Personne personne, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "modifier-employe";
        }
        personneService.mettreAJourPersonne(personne);
        return "redirect:/gererEmployes/consulter-employe";
    }




}
