package tn.esprit.pidev.Services;

import tn.esprit.pidev.Entities.Article;
import tn.esprit.pidev.Entities.User;
import tn.esprit.pidev.Repositories.ArticleRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.Repositories.UserRepository;

@Service
public class ArticleService {
    ArticleRepository articleRepository;
    UserRepository userRepository;

    @Autowired
    public ArticleService(ArticleRepository repository) {
        this.articleRepository = repository;
    }

    public List<Article> all() {
	return (List<Article>) articleRepository.findAll();
    }
    
    public Optional<Article> get(Long id) {
        return articleRepository.findById(id);
    }
    
    public Article update(Article article) {
	return articleRepository.save(article);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
    
    public Article create(Article article, String username) {
        User user = userRepository.findByUsername(username);
        article.getAuthors().add(user);
        articleRepository.save(article);
        return article;
    }
    
//     public Article assignKeyword(UUID articleId, Long keywordId) {
// 	Article article = repository.findById(articleId).get();
// 	Optional<Keyword> keyword = keywordRepository.findById(keywordId);
// 	if (!keyword.isPresent()) {
// 	    article.getKeywords().add(keyword.get());
// 	}
// 	repository.save(article);
// 	return article;
//     }
}
