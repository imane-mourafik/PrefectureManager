package org.example.projetgestionstockp.Service;

import org.example.projetgestionstockp.Model.Fournisseur;
import org.example.projetgestionstockp.Repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FournisseurService {
    @Autowired
    private FournisseurRepository fournisseurRepository;

    public Fournisseur ajouterFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }
}