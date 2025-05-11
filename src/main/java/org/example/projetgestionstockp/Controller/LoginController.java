package org.example.projetgestionstockp.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Repository.PersonneRepository;
import org.example.projetgestionstockp.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private EmailService emailService;

    // Route pour afficher la page d'accueil (avec formulaire de connexion)
    @GetMapping("/")
    public String showLoginPage() {
        return "accueil";
    }

    // Route pour traiter la soumission du formulaire de connexion
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String motdepasse, HttpSession session, Model model) {
        Optional<Personne> personneOpt = personneRepository.findByEmail(email);

        if (personneOpt.isPresent()) {
            Personne personne = personneOpt.get();
            if (personne.getMotdepasse().equals(motdepasse)) {
                session.setAttribute("user", personne);
                session.setAttribute("username", personne.getNom());

                if (personne.getRole().toString().equals("Administrateur")) {
                    return "redirect:/admin";
                } else if (personne.getRole().toString().equals("Magasinier")) {
                    return "redirect:/magasinier";
                }
                else if (personne.getRole().toString().equals("Employe")) {
                    return "redirect:/employe";
                }

            } else {
                model.addAttribute("erreur", "Mot de passe incorrect");
            }
        } else {
            model.addAttribute("erreur", "Email introuvable");
        }
        return "accueil";
    }

    // Route pour se déconnecter
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "feedback"; // montre la page feedback.jsp après déconnexion
    }

    // Route pour afficher la page de réinitialisation du mot de passe
    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgot-password"; // Page d'entrée email
    }

    // Route pour envoyer un code de réinitialisation


    // Route pour vérifier le code de réinitialisation
    @PostMapping("/verify-reset-code")
    public String verifyResetCode(@RequestParam String email, @RequestParam String code, Model model) {
        Optional<Personne> personneOpt = personneRepository.findByEmail(email);
        if (personneOpt.isPresent()) {
            Personne personne = personneOpt.get();
            if (code.equals(personne.getResetCode())) {
                model.addAttribute("email", email);
                return "reset-password-final"; // Formulaire pour saisir le nouveau mot de passe
            } else {
                model.addAttribute("erreur", "Code incorrect.");
                return "verify-reset-code";
            }
        } else {
            model.addAttribute("erreur", "Email introuvable.");
            return "forgot-password";
        }
    }

    // Route pour finaliser la réinitialisation du mot de passe
    @PostMapping("/reset-password-final")
    public String finalPasswordReset(@RequestParam String email, @RequestParam String newPassword, @RequestParam String confirmPassword, Model model) {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("erreur", "Les mots de passe ne correspondent pas.");
            model.addAttribute("email", email);
            return "reset-password-final";
        }

        Optional<Personne> personneOpt = personneRepository.findByEmail(email);
        if (personneOpt.isPresent()) {
            Personne personne = personneOpt.get();
            personne.setMotdepasse(newPassword);
            personne.setResetCode(null); // Supprimer le code après usage
            personneRepository.save(personne);
            return "redirect:/accueil"; // Rediriger vers la page d'accueil
        } else {
            model.addAttribute("erreur", "Erreur. Réessayez.");
            return "reset-password-final";
        }
    }
    @PostMapping("/forgot-password")
    public String sendResetCode(@RequestParam String email, Model model) {
        Optional<Personne> personneOpt = personneRepository.findByEmail(email);
        if (personneOpt.isPresent()) {
            Personne personne = personneOpt.get();
            String code = String.valueOf((int)(Math.random() * 900000) + 100000); // Code aléatoire
            personne.setResetCode(code);
            personneRepository.save(personne);

            // Envoi de l'email avec le code de réinitialisation
            emailService.sendResetPasswordEmail(email, code);

            model.addAttribute("email", email);
            model.addAttribute("message", "Un code de vérification a été envoyé à votre email.");
            return "verify-reset-code"; // Page pour entrer le code
        } else {
            model.addAttribute("erreur", "Email introuvable.");
            return "forgot-password";
        }
    }
}
