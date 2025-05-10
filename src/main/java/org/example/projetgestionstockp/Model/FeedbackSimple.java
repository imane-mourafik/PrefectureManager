package org.example.projetgestionstockp.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FeedbackSimple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int note; // 1 = 😡, 2 = 😐, 3 = 😊

    private String commentaire;

    private String dateSoumission; // optionnel si tu veux la date
}
