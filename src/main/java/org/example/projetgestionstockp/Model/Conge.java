package org.example.projetgestionstockp.Model;

import jakarta.persistence.*;
import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Model.StatutConge;

import java.time.LocalDate;

@Entity
public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private StatutConge statut = StatutConge.EN_ATTENTE; // Statut à "EN_ATTENTE" au départ

    private String motif;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Personne employe;

    // Champs ajoutés pour l'IA
    private int anciennete;
    private int solde;
    private int duree;
    private int typeConge;

    public int getAnciennete() {
        return anciennete;
    }

    public void setAnciennete(int anciennete) {
        this.anciennete = anciennete;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Personne getEmploye() {
        return employe;
    }

    public void setEmploye(Personne employe) {
        this.employe = employe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public StatutConge getStatut() {
        return statut;
    }

    public void setStatut(StatutConge statut) {
        this.statut = statut;
    }

    public int getTypeConge() {
        return typeConge;
    }

    public void setTypeConge(int typeConge) {
        this.typeConge = typeConge;
    }
}
