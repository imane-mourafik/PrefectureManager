package org.example.projetgestionstockp.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private PersonneRepository personneRepository; // Assure-toi que tu as bien ce repository pour gérer les utilisateurs

    // Route pour afficher la page d'accueil (avec formulaire de connexion)
    @GetMapping("/")
    public String showLoginPage() {
        return "accueil"; // Renvoie à la page d'accueil
    }

    // Route pour traiter la soumission du formulaire de connexion
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String motdepasse,
                        HttpSession session,
                        Model model) {

        Optional<Personne> personneOpt = personneRepository.findByEmail(email);

        if (personneOpt.isPresent()) {
            Personne personne = personneOpt.get();

            if (personne.getMotdepasse().equals(motdepasse)) {
                // L'utilisateur est authentifié, on l'ajoute à la session
                session.setAttribute("user", personne);

                // Rediriger en fonction du rôle
                if (personne.getRole().toString().equals("Administrateur")) {
                    return "redirect:/admin";
                } else if (personne.getRole().toString().equals("Magasinier")) {
                    return "redirect:/magasinier";
                } else {
                    return "redirect:/accueil";
                }
            } else {
                model.addAttribute("erreur", "Mot de passe incorrect");
                return "accueil"; // Rediriger vers la page d'accueil si le mot de passe est incorrect
            }
        } else {
            model.addAttribute("erreur", "Email introuvable");
            return "accueil"; // Rediriger vers la page d'accueil si l'email est introuvable
        }
    }

    // Route pour se déconnecter
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/accueil"; // Rediriger vers la page d'accueil après la déconnexion
    }

    // Route pour afficher la page de réinitialisation du mot de passe
    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "reset-password"; // Affiche la page où l'utilisateur peut entrer son email et son nouveau mot de passe
    }

    // Route pour gérer la soumission du formulaire de réinitialisation du mot de passe
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String newPassword,
                                @RequestParam String confirmPassword,
                                Model model) {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("erreur", "Les mots de passe ne correspondent pas.");
            return "reset-password"; // Si les mots de passe ne correspondent pas, on retourne au formulaire
        }

        // Recherche de l'utilisateur par email
        Optional<Personne> personneOpt = personneRepository.findByEmail(email);
        if (personneOpt.isPresent()) {
            Personne personne = personneOpt.get();
            // Mise à jour du mot de passe de l'utilisateur
            personne.setMotdepasse(newPassword); // Pense à le hacher ici avec BCrypt si nécessaire
            personneRepository.save(personne); // Sauvegarder l'utilisateur avec le nouveau mot de passe
            return "redirect:/accueil"; // Rediriger vers la page d'accueil après la réinitialisation
        } else {
            model.addAttribute("erreur", "Email introuvable.");
            return "reset-password"; // Si l'email n'est pas trouvé, retourner à la page de réinitialisation
        }
    }

}
