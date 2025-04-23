package org.example.projetgestionstockp.Model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String message ;
    private Boolean lue;
    private Date date ;
    @ManyToOne
    private Personne administrateur ;

    public Notification() {}

    public Personne getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Personne administrateur) {
        this.administrateur = administrateur;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getLue() {
        return lue;
    }

    public void setLue(Boolean lue) {
        this.lue = lue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Notification(Boolean lue, String message, Date date , Personne administrateur) {
        this.lue = lue;
        this.message = message;
        this.date = date;
        this.administrateur = administrateur;

    }
}
