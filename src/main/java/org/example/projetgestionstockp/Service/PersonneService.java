package org.example.projetgestionstockp.Service;

import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PersonneService {
    @Autowired
    private PersonneRepository personneRepository;

    public Personne ajouterPersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    Optional<Personne> chercherAdministrateur(String Email) {
        return personneRepository.findByEmail(Email);
    }

    void supprimerAdministrateur(String Email) {
       personneRepository.deleteByEmail(Email);

    }

    ArrayList<Personne> afficherTouslesAdmins() {
        return (ArrayList<Personne>) personneRepository.findAll();

    }
}
