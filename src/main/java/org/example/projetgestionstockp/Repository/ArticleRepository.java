package org.example.projetgestionstockp.Repository;

import org.example.projetgestionstockp.Model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    long countByQuantiteLessThan(int seuil);
    @Query("SELECT a.typeArticle, COUNT(a) FROM Article a GROUP BY a.typeArticle")
    List<Object[]> countArticlesByType();
    List<Article> findByNomContainingIgnoreCase(String motCle);




}
