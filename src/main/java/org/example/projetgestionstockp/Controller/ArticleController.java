package org.example.projetgestionstockp.Controller;

import org.example.projetgestionstockp.Model.Article;
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

    @PostMapping("/ajouter-article")
    public String ajouterArticle(@ModelAttribute("article") Article article) {
        articleRepository.save(article);

        // Enregistrer le mouvement de stock - ENTREE
        StockMouvement mvt = new StockMouvement();
        mvt.setType("ENTREE");
        mvt.setDate(LocalDateTime.now());
        mvt.setQuantite(article.getQuantite());
        mvt.setArticle(article);
        mvt.setDescription("Ajout d’un nouvel article");
        stockMouvementService.enregistrerMouvement(mvt);
        notificationService.ajouterNotification("🆕 Nouvel article ajouté : " + article.getNom(), null);


        return "redirect:/consulter-article";
    }



    @GetMapping("/supprimer/{id}")
    public String supprimerArticle(@PathVariable("id") Long id) {
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
            notificationService.ajouterNotification(" Un article est supprimer  : " + article.getNom(), null);

        }

        return "redirect:/consulter-article";
    }

    @GetMapping("/consulter-article")
    public String consulterArticles(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "consulter-article";
    }

}
