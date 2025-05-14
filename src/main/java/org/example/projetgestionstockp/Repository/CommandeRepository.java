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



    Optional<Commande> findByStatut(Status statut);


    List<Commande> findAllByStatut(Status statut);


    List<Commande> findByDate(Date date);


    List<Commande> findByDateBetween(Date startDate, Date endDate);


    Optional<Commande> findTopByOrderByDateDesc();

    long countByStatut(Status statut);


    List<Commande> findByStatutContaining(String keyword);


    void deleteByStatut(Status statut);
}
