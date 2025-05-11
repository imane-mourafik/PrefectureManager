package org.example.projetgestionstockp.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeId;
    private String nom;
    private String Prenom;
    private String numcarte;
    private String adresse;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumcarte() {
        return numcarte;
    }

    public void setNumcarte(String numcarte) {
        this.numcarte = numcarte;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    private String role;
    private String justification;
    private LocalDateTime dateArchivage;

    public Archive() {}

    public Archive(Long employeId, String nom, String prenom, String role, String adresse, String email, String justification, String numcarte) {
        this.employeId = employeId;
        this.nom = nom;
        Prenom = prenom;
        this.role = role;
        this.adresse = adresse;
        this.email = email;
        this.justification = justification;
        this.numcarte = numcarte;
        this.dateArchivage =LocalDateTime.now();
    }



    public LocalDateTime getDateArchivage() {
        return dateArchivage;
    }

    public void setDateArchivage(LocalDateTime dateArchivage) {
        this.dateArchivage = dateArchivage;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
