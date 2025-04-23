package org.example.projetgestionstockp.Service;

import org.example.projetgestionstockp.Model.StockMouvement;
import org.example.projetgestionstockp.Repository.StockMouvementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMouvementService {
    @Autowired
    private StockMouvementRepository stockMouvementRepository;

    public void enregistrerMouvement(StockMouvement mvt) {
        stockMouvementRepository.save(mvt);
    }

    public List<StockMouvement> listerMouvements() {
        return stockMouvementRepository.findAll();
    }
    public List<StockMouvement> getAllMouvements() {
        return stockMouvementRepository.findAll();
    }

}
