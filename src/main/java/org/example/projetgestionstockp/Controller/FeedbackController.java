package org.example.projetgestionstockp.Controller;

import org.example.projetgestionstockp.Model.FeedbackSimple;
import org.example.projetgestionstockp.Repository.FeedbackSimpleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class FeedbackController {

    @GetMapping("/feedback")
    public String afficherFeedback() {
        return "feedback"; // correspond à feedback.html
    }

    @PostMapping("/api/avis")
    @ResponseBody
    public String recevoirAvis(@RequestBody Map<String, Integer> body) {
        int note = body.get("note");

        return "Merci pour votre retour !";
    }
}



