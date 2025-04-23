package org.example.projetgestionstockp.Model;

public class MouvementDTO {
    private String date;  // Date du mouvement
    private int entrees;  // Nombre d'articles ajoutés
    private int sorties;  // Nombre d'articles supprimés

    // Constructeur
    public MouvementDTO(String date, int entrees, int sorties) {
        this.date = date;
        this.entrees = entrees;
        this.sorties = sorties;
    }

    // Getter pour la date
    public String getDate() {
        return date;
    }

    // Setter pour la date
    public void setDate(String date) {
        this.date = date;
    }

    // Getter pour les entrées
    public int getEntrees() {
        return entrees;
    }

    // Setter pour les entrées
    public void setEntrees(int entrees) {
        this.entrees = entrees;
    }

    // Getter pour les sorties
    public int getSorties() {
        return sorties;
    }

    // Setter pour les sorties
    public void setSorties(int sorties) {
        this.sorties = sorties;
    }
}
