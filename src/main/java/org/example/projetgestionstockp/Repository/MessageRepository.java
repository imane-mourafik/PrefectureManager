package org.example.projetgestionstockp.Repository;

import org.example.projetgestionstockp.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByDeletedFalseOrderByTimestampAsc();
}
