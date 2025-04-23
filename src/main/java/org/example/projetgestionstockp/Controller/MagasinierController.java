package org.example.projetgestionstockp.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.projetgestionstockp.Model.Personne;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MagasinierController {

    @GetMapping("/magasinier")
    public String magasinierPage(HttpSession session, Model model) {
        Personne user = (Personne) session.getAttribute("user");

        if (user == null || !user.getRole().toString().equals("Magasinier")) {
            return "redirect:/accueil";
        }

        model.addAttribute("magasinier", user);
        return "magasinier";
    }

    @GetMapping("/Magasinier/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/accueil";
    }
}
