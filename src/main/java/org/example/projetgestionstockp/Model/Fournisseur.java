package org.example.projetgestionstockp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    @Email(message="la forme d'email n'est pas réspecter")
    private String email;
    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL)
    private List<Commande> commandes = new ArrayList<>();

    public Fournisseur() {}

    public Fournisseur(String nom) {
        this.nom = nom;
    }

    public Fournisseur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public @Email(message = "la forme d'email n'est pas réspecter") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "la forme d'email n'est pas réspecter") String email) {
        this.email = email;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Fournisseur{" +
                "adresse='" + adresse + '\'' +
                ", id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Fournisseur(String adresse, String email, String nom, String prenom, String telephone,ArrayList<Commande> commandes) {
        this.adresse = adresse;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.commandes = new ArrayList<>();

    }
}
