package org.example.projetgestionstockp.Repository;

import org.example.projetgestionstockp.Model.Conge;
import org.example.projetgestionstockp.Model.Personne;
import org.example.projetgestionstockp.Model.StatutConge;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CongeRepository extends JpaRepository<Conge, Long> {
    @Cacheable("conge")
    List<Conge> findByStatut(StatutConge statut);
    @Cacheable("conge")
    List<Conge> findByEmploye(Personne employe);

}


