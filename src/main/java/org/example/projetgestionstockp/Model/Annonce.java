package org.example.projetgestionstockp.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @Column(columnDefinition = "TEXT")
    private String contenu;

    private LocalDateTime datePublication;
}
