package org.example.projetgestionstockp.Service;

import org.example.projetgestionstockp.Model.Article;
import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository stockRepository;


    @Autowired
    private PersonneService administrateurService;

    @Autowired
    private TwilioService twilioService;


    //  Créer ou mettre à jour un stock
    public Article ajouterArticle(Article article) {
        return stockRepository.save(article);
    }

    //  Lire un stock par ID
    public Optional<Article> getArticleById(Long id) {
        return stockRepository.findById(id);
    }

    //  Lire tous les stocks
    public List<Article> getAllArticles() {
        return stockRepository.findAll();
    }

    //  Supprimer un Article
    public void supprimerArticle(Long id) {
        stockRepository.deleteById(id);
    }
    // returner les articles dont la quantité dans le stock est inférieur au sueilALert
    public List<Article> getArticlesStockFaible() {
        return stockRepository.findAll()
                .stream()
                .filter(article -> article.getQuantite() < article.getSeuilAlerte())
                .collect(Collectors.toList());
    }
    public void verifierStockFaibleEtEnvoyerSMS() {
        List<Article> articlesFaibles = getArticlesStockFaible();

        if (!articlesFaibles.isEmpty()) {
            StringBuilder messageContent = new StringBuilder();
            messageContent.append("Alerte Stock Bas :\n");

            for (Article article : articlesFaibles) {
                messageContent.append("• ")
                        .append(article.getNom())
                        .append(" (")
                        .append(article.getQuantite())
                        .append(" / seuil: ")
                        .append(article.getSeuilAlerte())
                        .append(")\n");
            }

            // Envoyer le SMS à l'administrateur (à sécuriser plus tard si plusieurs)
            Personne admin = administrateurService.afficherTouslesAdmins().get(0);
            twilioService.sendSms(admin.getTelephone(), messageContent.toString());
        }
    }


}
