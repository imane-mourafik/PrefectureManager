package org.example.projetgestionstockp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DocumentTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom; // ex : "Attestation de travail"
    private String description;

    private String cheminFichierModele; // fichier .docx ou .html

    // Le document est actif ou non (archivé par exemple)
    private boolean actif = true;

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getCheminFichierModele() {
        return cheminFichierModele;
    }

    public void setCheminFichierModele(String cheminFichierModele) {
        this.cheminFichierModele = cheminFichierModele;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
