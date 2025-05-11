package org.example.projetgestionstockp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

@Entity
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "le nom est obligatoire")
    private String nom;
    private String prenom;
    private String adresse;
    private String numCarte;
    private Date dateRecrutement;
    private  String service;
    private String photoProfil;

    private int experience;
    private double salaire;

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    public String getPhotoProfil() {
        return photoProfil;
    }

    public void setPhotoProfil(String photoProfil) {
        this.photoProfil = photoProfil;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public List<Conge> getConges() {
        return conges;
    }

    public void setConges(List<Conge> conges) {
        this.conges = conges;
    }

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<Conge> conges = new ArrayList<>();


    public Personne(String nom, String prenom, String email, String motdepasse, Role role, String adresse, String telephone, String numCarte, Date dateRecrutement,String Service,int experience,double salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motdepasse = motdepasse;
        this.role = role;
        this.adresse = adresse;
        this.telephone = telephone;
        this.numCarte = numCarte;
        this.dateRecrutement = dateRecrutement;
        this.service=Service;
        this.experience=experience;
        this.salaire=salaire;
    }
    public Personne(String nom, String prenom, String email, String motdepasse, Role role, String adresse, String telephone, String numCarte, Date dateRecrutement,String Service,int experience) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motdepasse = motdepasse;
        this.role = role;
        this.adresse = adresse;
        this.telephone = telephone;
        this.numCarte = numCarte;
        this.dateRecrutement = dateRecrutement;
        this.service=Service;
        this.experience=experience;}

    public Date getDateRecrutement() {
        return dateRecrutement;
    }

    public void setDateRecrutement(Date dateRecrutement) {
        this.dateRecrutement = dateRecrutement;
    }

    public String getNumCarte() {
        return numCarte;
    }

    public void setNumCarte(String numCarte) {
        this.numCarte = numCarte;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }








    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Enumerated(EnumType.STRING)
    private Role role;
    private String telephone;
    @NotBlank(message = "l'email est obligatoire")
    @Email(message = "la forme de l'email est invalide")
    private String email;
    private String resetCode;

    public void setId(Long id) {
        this.id = id;
    }

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }





    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }




    @NotBlank(message = "le mot de passe est obligatoire")
    private String motdepasse;

    @OneToMany(mappedBy = "administrateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

    public Personne() {}



    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(@NotBlank(message = "le mot de passe est obligatoire") String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

}
