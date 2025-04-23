package org.example.projetgestionstockp.Controller;

import org.example.projetgestionstockp.Service.RapportMensuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rapport")
public class RapportController {

    @Autowired
    private RapportMensuelService rapportService;

    // Méthode pour obtenir les statistiques générales
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStatistiques() {
        return ResponseEntity.ok(rapportService.getStatistiques());
    }

    // Méthode pour obtenir l'évolution mensuelle des mouvements
    @GetMapping("/evolution")
    public ResponseEntity<List<Map<String, Object>>> getEvolutionMensuelle() {
        return ResponseEntity.ok(rapportService.getEvolutionMensuelle());
    }

}
