package org.example.projetgestionstockp.Repository;

import org.example.projetgestionstockp.Model.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    List<Annonce> findAllByOrderByDatePublicationDesc();
}
