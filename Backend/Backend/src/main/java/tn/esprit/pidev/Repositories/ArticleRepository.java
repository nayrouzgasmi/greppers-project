package tn.esprit.pidev.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.Entities.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {}
