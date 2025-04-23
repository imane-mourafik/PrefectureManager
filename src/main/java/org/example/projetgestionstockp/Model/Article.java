package org.example.projetgestionstockp.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeArticle typeArticle;

    @Enumerated(EnumType.STRING)
    private ServiceAffecte serviceAffecte;

    private String description;
    private int quantite;
    private String nom;
    private double prix;
    private  static final  int seuilAlerte=5;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleCommande> articleCommandes = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockMouvement> stockMouvements = new ArrayList<>();

    @ManyToOne
    private RapportMensuel rapportMensuel;

    // === Constructeurs ===

    public Article() {
    }

    public Article(String description, String nom, int quantite) {
        this.description = description;
        this.nom = nom;
        this.quantite = quantite;

    }

    public Article(String description, double prix, int quantite, ServiceAffecte serviceAffecte,
                   TypeArticle typeArticle, ArrayList<StockMouvement> stockMouvements, RapportMensuel rapportMensuel) {
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.serviceAffecte = serviceAffecte;

        this.typeArticle = typeArticle;
        this.stockMouvements = stockMouvements;
        this.rapportMensuel = rapportMensuel;
    }

    // === Getters & Setters ===

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeArticle getTypeArticle() {
        return typeArticle;
    }

    public void setTypeArticle(TypeArticle typeArticle) {
        this.typeArticle = typeArticle;
    }

    public ServiceAffecte getServiceAffecte() {
        return serviceAffecte;
    }

    public void setServiceAffecte(ServiceAffecte serviceAffecte) {
        this.serviceAffecte = serviceAffecte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getSeuilAlerte() {
        return seuilAlerte;
    }



    public List<ArticleCommande> getArticleCommandes() {
        return articleCommandes;
    }

    public void setArticleCommandes(List<ArticleCommande> articleCommandes) {
        this.articleCommandes = articleCommandes;
    }

    public List<StockMouvement> getStockMouvements() {
        return stockMouvements;
    }

    public void setStockMouvements(List<StockMouvement> stockMouvements) {
        this.stockMouvements = stockMouvements;
    }

    public RapportMensuel getRapportMensuel() {
        return rapportMensuel;
    }

    public void setRapportMensuel(RapportMensuel rapportMensuel) {
        this.rapportMensuel = rapportMensuel;
    }

    // === toString ===

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", typeArticle=" + typeArticle +
                ", serviceAffecte=" + serviceAffecte +
                ", quantite=" + quantite +
                ", prix=" + prix +
                ", seuilAlerte=" + seuilAlerte +
                '}';
    }
}
