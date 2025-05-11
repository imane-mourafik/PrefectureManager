package org.example.projetgestionstockp.Service;

import org.example.projetgestionstockp.Model.Conge;
import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Model.StatutConge;
import org.example.projetgestionstockp.Repository.CongeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CongeService {

    @Autowired
    private CongeRepository congeRepository; // Votre repository pour gérer la persistance
    public List<Conge> getDemandesParEmploye(Personne employe) {
        return congeRepository.findByEmploye(employe);
    }

    public String envoyerDemande(Conge conge) {
        // Sauvegarder la demande de congé avec statut EN_ATTENTE
        conge.setStatut(StatutConge.EN_ATTENTE);
        congeRepository.save(conge);
        return "Votre demande de congé a été envoyée avec succès.";
    }

    public List<Conge> getAllDemandes() {
        // Récupérer toutes les demandes de congé avec statut EN_ATTENTE
        return congeRepository.findByStatut(StatutConge.EN_ATTENTE);
    }

    public String traiterDemande(Long congeId, boolean accepter) {
        try {
            Conge conge = congeRepository.findById(congeId).orElseThrow(() -> new RuntimeException("Demande non trouvée"));

            if (accepter) {
                conge.setStatut(StatutConge.ACCEPTE);
            } else {
                conge.setStatut(StatutConge.REFUSE);
            }

            congeRepository.save(conge);
            return accepter ? "Demande acceptée." : "Demande refusée.";
        } catch (Exception e) {
            return "Une erreur s'est produite : " + e.getMessage();
        }
    }

}
