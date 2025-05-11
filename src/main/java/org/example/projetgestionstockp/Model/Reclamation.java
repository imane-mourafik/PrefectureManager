package org.example.projetgestionstockp.Model;

import jakarta.persistence.*;


import java.time.LocalDateTime;

@Entity
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String objet;
    private String contenu;


    private LocalDateTime dateReclamation;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Personne employe;

    @Column(name = "visible_par_admin")
    private boolean visibleParAdmin = false;




    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }



    public void setDateReclamation(LocalDateTime dateReclamation) {
        this.dateReclamation = dateReclamation;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isVisibleParAdmin() {
        return visibleParAdmin;
    }

    public void setVisibleParAdmin(boolean visibleParAdmin) {
        this.visibleParAdmin = visibleParAdmin;
    }
}
