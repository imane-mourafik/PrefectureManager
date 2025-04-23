package org.example.projetgestionstockp.Repository;

import org.example.projetgestionstockp.Model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    Optional<Fournisseur> findByEmail(String email);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);
}
