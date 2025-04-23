package org.example.projetgestionstockp.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Status statut;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleCommande> articleCommandes = new ArrayList<>();



    public Commande() {
    }

    public Commande(LocalDate date, Status statut, Fournisseur fournisseur) {
        this.date = date;
        this.statut = statut;
        this.fournisseur = fournisseur;
    }



    public void ajouterArticleCommande(Article article, int quantite) {
        ArticleCommande ac = new ArticleCommande(article, this, quantite);
        articleCommandes.add(ac);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatut() {
        return statut;
    }

    public void setStatut(Status statut) {
        this.statut = statut;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public List<ArticleCommande> getArticleCommandes() {
        return articleCommandes;
    }

    public void setArticleCommandes(List<ArticleCommande> articleCommandes) {
        this.articleCommandes = articleCommandes;
    }



    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", date=" + date +
                ", statut=" + statut +
                ", fournisseur=" + (fournisseur != null ? fournisseur.getNom() : "null") +
                '}';
    }
}
