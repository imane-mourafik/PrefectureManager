package org.example.projetgestionstockp.Repository;

import org.example.projetgestionstockp.Model.Reclamation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
 @Cacheable("reclamations")
    List<Reclamation> findByVisibleParAdminTrue();
}
