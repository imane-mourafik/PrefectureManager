package org.example.projetgestionstockp.Service;

import jakarta.servlet.http.HttpSession;
import org.example.projetgestionstockp.Model.Archive;
import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Repository.ArchiveRepository;
import org.example.projetgestionstockp.Repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.Optional;

@Service
public class PersonneService {
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private ArchiveRepository archiveRepository;


    @Autowired
    private HttpSession session;

    public Personne ajouterPersonne(Personne personne) {
        return personneRepository.save(personne);
    }
    public Optional<Personne> findByEmail(String email) {
        return personneRepository.findByEmail(email);
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



    public void mettreAJourPersonne(Personne personne) {
        personneRepository.save(personne);
    }
    public void supprimerPersonne(Long id, String justification) {
        Personne personne = personneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personne non trouvée"));

        // Archiver les infos avant suppression
        Archive archive = new Archive(
                personne.getId(),
                personne.getNom(),
                personne.getPrenom(),
                personne.getRole().name(),
                personne.getAdresse(),
                personne.getEmail(),
                justification,
                personne.getNumCarte()
        );


        archiveRepository.save(archive);

        personneRepository.delete(personne);
    }
    public Personne findById(Long id) {
        return personneRepository.findById(id).orElse(null); // Retourne null si la personne n'est pas trouvée
    }
    public Personne findByUsername(String username) {
        // Supposons que vous ayez un repository PersonneRepository pour gérer l'accès à la base de données
        return personneRepository.findByNom(username);
    }

    public Personne getLoggedInPersonne() {
        // Supposons que tu utilises une session pour stocker l'utilisateur connecté
        Long personneId = (Long) session.getAttribute("personneId"); // Personne ID dans la session
        return personneId != null ? findById(personneId) : null;
    }



}
