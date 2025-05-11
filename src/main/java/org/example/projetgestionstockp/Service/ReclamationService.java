package org.example.projetgestionstockp.Service;


import org.example.projetgestionstockp.Model.Reclamation;
import org.example.projetgestionstockp.Repository.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;


    public Reclamation saveReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    public  List<Reclamation> getAllReclamation() {
        return reclamationRepository.findAll();
    }
    public List<Reclamation> getReclamationsPourAdmin() {
        return reclamationRepository.findByVisibleParAdminTrue();
    }
}
