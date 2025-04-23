package org.example.projetgestionstockp.Repository;

import org.example.projetgestionstockp.Model.Commande;
import org.example.projetgestionstockp.Model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    // Trouver une commande par son statut

    Optional<Commande> findByStatut(Status statut);

    // Trouver toutes les commandes par statut

    List<Commande> findAllByStatut(Status statut);

    // Trouver toutes les commandes à une date spécifique
    List<Commande> findByDate(Date date);

    // Trouver toutes les commandes entre deux dates
    List<Commande> findByDateBetween(Date startDate, Date endDate);

    // Trouver la dernière commande (par date)
    Optional<Commande> findTopByOrderByDateDesc();

    // Compter le nombre de commandes par statut
    long countByStatut(Status statut);

    // Trouver des commandes dont le statut contient un mot clé
    List<Commande> findByStatutContaining(String keyword);

    // Supprimer toutes les commandes avec un statut spécifique
    void deleteByStatut(Status statut);
}
