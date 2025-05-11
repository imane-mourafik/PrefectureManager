package org.example.projetgestionstockp.Service;

import org.example.projetgestionstockp.Model.Annonce;
import org.example.projetgestionstockp.Repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnonceService {
    @Autowired
    private AnnonceRepository repo;
    @Autowired
    private AnnonceRepository annonceRepository;

    public void publierAnnonce(Annonce annonce) {
        annonce.setDatePublication(LocalDateTime.now());
        repo.save(annonce);
    }
    // Méthode pour supprimer une annonce par son ID


    public List<Annonce> getAllAnnonces() {
        return repo.findAllByOrderByDatePublicationDesc();
    }
}
