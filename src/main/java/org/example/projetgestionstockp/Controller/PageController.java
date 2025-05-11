package org.example.projetgestionstockp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/rapport")
    public String afficherRapport() {
        return "rapport";
    }
}
