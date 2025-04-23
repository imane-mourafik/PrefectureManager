package org.example.projetgestionstockp.Repository;

import org.example.projetgestionstockp.Model.RapportMensuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RapportMensuelRepository extends JpaRepository<RapportMensuel, Long> {
}
