package tn.esprit.pidev.Services;

import lombok.AllArgsConstructor;
import tn.esprit.pidev.Entities.Article;
import tn.esprit.pidev.Repositories.ArticleRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleService {
    ArticleRepository repo;

    public List<Article> all() {
	return (List<Article>) repo.findAll();
    }
    
    public Article get(UUID id) {
	return repo.findById(id).get();
    }
    
    public Article update(Article article) {
	return repo.save(article);
    }

    public void delete(UUID id) {
	repo.deleteById(id);
    }
    
    public Article create(Article article) {
	return repo.save(article);
    }
}
