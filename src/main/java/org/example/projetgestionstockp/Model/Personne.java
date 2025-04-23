package org.example.projetgestionstockp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "le nom est obligatoire")
    private String nom;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String telephone;
    @NotBlank(message = "l'email est obligatoire")
    @Email(message = "la forme de l'email est invalide")
    private String email;


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Personne(String nom, String email, String motdepasse, Role role, String telephone) {
        this.nom = nom;
        this.email = email;
        this.motdepasse = motdepasse;
        this.role = role;
        this.telephone = telephone;
    }



    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Personne(String nom, String email, String motdepasse, String telephone) {
        this.nom = nom;
        this.email = email;
        this.motdepasse = motdepasse;
        this.telephone = telephone;
    }


    @NotBlank(message = "le mot de passe est obligatoire")
    private String motdepasse;

    @OneToMany(mappedBy = "administrateur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

    public Personne() {}

    public Personne(String nom, String email, String motdepasse) {
        this.nom = nom;
        this.email = email;
        setMotdepasse(motdepasse);
    }

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

    public void setMotdepasse(String motdepasse) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.motdepasse = encoder.encode(motdepasse);
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

}
