package org.example.projetgestionstockp.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.projetgestionstockp.Model.Article;
import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Model.StockMouvement;
import org.example.projetgestionstockp.Repository.ArticleRepository;
import org.example.projetgestionstockp.Service.ArticleService;
import org.example.projetgestionstockp.Service.NotificationService;
import org.example.projetgestionstockp.Service.StockMouvementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private StockMouvementService stockMouvementService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/gerer-articles")
    public String afficherPageGestionArticles() {
        return "gererArticles";
    }

    @GetMapping("/ajouter-article")
    public String afficherFormulaireAjouterArticle(Model model) {
        model.addAttribute("article", new Article());
        return "ajouter-article";
    }

    // Ajouter un article
    @PostMapping("/ajouter-article")
    public String ajouterArticle(@ModelAttribute("article") Article article, HttpSession session) {
        Personne user = (Personne) session.getAttribute("user");  // Récupération de l'utilisateur

        articleRepository.save(article);

        // Enregistrer le mouvement de stock - ENTREE
        StockMouvement mvt = new StockMouvement();
        mvt.setType("ENTREE");
        mvt.setDate(LocalDateTime.now());
        mvt.setQuantite(article.getQuantite());
        mvt.setArticle(article);
        mvt.setDescription("Ajout d’un nouvel article");
        stockMouvementService.enregistrerMouvement(mvt);

        String utilisateurNom = user != null ? user.getNom() : "Inconnu"; // Nom de l'utilisateur
        notificationService.ajouterNotification(" Nouvel article ajouté : " + article.getNom() + " par " + utilisateurNom, null);

        return "redirect:/consulter-article";
    }

    // Suppression d'un article
    @GetMapping("/supprimer/{id}")
    public String supprimerArticle(@PathVariable("id") Long id, HttpSession session) {
        Personne user = (Personne) session.getAttribute("user");  // Récupération de l'utilisateur

        Article article = articleRepository.findById(id).orElse(null);

        if (article != null) {
            // Enregistrer le mouvement de stock - SORTIE
            StockMouvement mvt = new StockMouvement();
            mvt.setType("SORTIE");
            mvt.setDate(LocalDateTime.now());
            mvt.setQuantite(article.getQuantite());
            mvt.setArticle(article);
            mvt.setDescription("Suppression de l’article");
            stockMouvementService.enregistrerMouvement(mvt);

            articleService.supprimerArticle(id);

            String utilisateurNom = user != null ? user.getNom() : "Inconnu"; // Nom de l'utilisateur
            notificationService.ajouterNotification("Un article a été supprimé : " + article.getNom() + " par " + utilisateurNom, null);
        }

        return "redirect:/consulter-article";
    }


    @GetMapping("/consulter-article")
    public String consulterArticles(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "consulter-article";
    }

    @GetMapping("/rechercher-articles")
    public String rechercherArticles(@RequestParam("motCle") String motCle, Model model) {
        List<Article> articles = articleRepository.findByNomContainingIgnoreCase(motCle);
        model.addAttribute("articles", articles);
        model.addAttribute("motCle", motCle); // pour réafficher dans le champ
        return "consulter-article";
    }

    @GetMapping("/consulterstockAdmine")
    public String consulterArticles2(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);

        return "consulterstockAdmine";
    }
}