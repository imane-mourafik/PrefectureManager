package org.example.projetgestionstockp.Repository;

import org.example.projetgestionstockp.Model.StockMouvement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMouvementRepository extends JpaRepository<StockMouvement, Long> {
    @Query("SELECT m.date, " +
            "SUM(CASE WHEN m.type = 'ENTREE' THEN m.quantite ELSE 0 END), " +
            "SUM(CASE WHEN m.type = 'SORTIE' THEN m.quantite ELSE 0 END) " +
            "FROM StockMouvement m GROUP BY m.date")
    List<Object[]> countMouvementsGroupByDate();

}
