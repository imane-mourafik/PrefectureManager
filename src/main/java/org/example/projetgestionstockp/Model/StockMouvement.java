package org.example.projetgestionstockp.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity


public class StockMouvement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private int quantite;
    private LocalDateTime date;
    private String  description;
    @ManyToOne
    private Article article;
    private String effectuePar;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getEffectuePar() {
        return effectuePar;
    }

    public void setEffectuePar(String effectuePar) {
        this.effectuePar = effectuePar;
    }

    public StockMouvement() {}

    public StockMouvement(String type, String description, int quantity, LocalDateTime date,Article article) {
        this.type = type;
        this.description = description;
        this.quantite = quantity;
        this.date = date;
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
