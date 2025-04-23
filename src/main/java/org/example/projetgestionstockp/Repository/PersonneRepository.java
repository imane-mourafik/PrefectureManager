package org.example.projetgestionstockp.Repository;

import org.example.projetgestionstockp.Model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Ajouter l'annotation @Repository ici
public interface PersonneRepository extends JpaRepository<Personne, Long> {

    Optional<Personne> findByEmail(String email);


    boolean existsByEmail(String email);

    void deleteByEmail(String email);
}