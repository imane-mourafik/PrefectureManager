package org.example.projetgestionstockp.Model;

import jakarta.persistence.*;

@Entity
public class ArticleCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantite;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    public ArticleCommande() {}

    public ArticleCommande(Article article, Commande commande, int quantite) {
        this.article = article;
        this.commande = commande;
        this.quantite = quantite;
    }

    // Getters et setters
}
