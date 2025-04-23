package org.example.projetgestionstockp.Service;

import org.example.projetgestionstockp.Repository.ArticleRepository;
import org.example.projetgestionstockp.Repository.CommandeRepository;
import org.example.projetgestionstockp.Repository.StockMouvementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RapportMensuelService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private StockMouvementRepository stockMouvementRepository;

    // Récupérer les statistiques générales pour le rapport
    public Map<String, Object> getStatistiques() {
        Map<String, Object> stats = new HashMap<>();

        long totalArticles = articleRepository.count();
        double valeurStock = articleRepository.findAll().stream()
                .mapToDouble(a -> a.getPrix() * a.getQuantite())
                .sum();
        long totalCommandes = commandeRepository.count();

        stats.put("totalArticles", totalArticles);
        stats.put("valeurStock", valeurStock);
        stats.put("totalCommandes", totalCommandes);

        return stats;
    }

    // Récupérer les données d'évolution mensuelle
    public List<Map<String, Object>> getEvolutionMensuelle() {
        List<Map<String, Object>> evolution = new ArrayList<>();

        // Simuler les 6 derniers mois
        String[] mois = {"Novembre", "Décembre", "Janvier", "Février", "Mars", "Avril"};
        for (String m : mois) {
            Map<String, Object> moisData = new HashMap<>();
            moisData.put("mois", m);
            moisData.put("ajoutes", (int) (Math.random() * 20));
            moisData.put("supprimes", (int) (Math.random() * 10));
            moisData.put("commandes", (int) (Math.random() * 30));

            evolution.add(moisData);
        }

        return evolution;
    }
}
