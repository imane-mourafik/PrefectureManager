package org.example.projetgestionstockp.Model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class RapportMensuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int mois;
    private int année;
    private int TotalEntrees;
    private int TotalSortie;
    private String Observation ;
    @OneToMany
    private ArrayList<Article> articles;
    public RapportMensuel() {}

    public RapportMensuel(int année, int mois, String observation, int totalEntrees, int totalSortie, ArrayList<Article> articles) {
        this.année = année;
        this.mois = mois;
        Observation = observation;
        TotalEntrees = totalEntrees;
        TotalSortie = totalSortie;
        this.articles = new ArrayList<>();
    }

    public int getTotalSortie() {
        return TotalSortie;
    }

    public void setTotalSortie(int totalSortie) {
        TotalSortie = totalSortie;
    }

    public int getTotalEntrees() {
        return TotalEntrees;
    }

    public void setTotalEntrees(int totalEntrees) {
        TotalEntrees = totalEntrees;
    }

    public String getObservation() {
        return Observation;
    }

    public void setObservation(String observation) {
        Observation = observation;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    @Override
    public String toString() {
        return "RapportMensuel{" +
                "id=" + id +
                ", année=" + année +
                ", mois=" + mois +
                ", Observation='" + Observation + '\'' +
                ", TotalEntrees=" + TotalEntrees +
                ", TotalSortie=" + TotalSortie +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAnnée() {
        return année;
    }

    public void setAnnée(int année) {
        this.année = année;
    }


}
