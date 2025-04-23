package org.example.projetgestionstockp.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.projetgestionstockp.Model.Personne;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        return "redirect:/accueil";
    }


}
