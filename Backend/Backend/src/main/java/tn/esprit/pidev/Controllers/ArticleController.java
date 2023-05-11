package tn.esprit.pidev.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.Services.ArticleService;
import tn.esprit.pidev.Entities.Article;

@RestController
@RequestMapping("/articles")
@CrossOrigin("*")
public class ArticleController {

    ArticleService service;

    public ArticleController() {}

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.service = articleService;
    }
    
    @GetMapping
    public List<Article> all() {
        return service.all();
    }

    @GetMapping("{id}")
    public ResponseEntity<Article> get(@PathVariable("id") Long id) {
        return service.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @PutMapping
    public Article update(@RequestBody Article article) {
        return service.update(article);
    }

    @PostMapping
    public Article create(@RequestBody Article article, @RequestParam("username") String username) {
        return service.create(article, username);
    }
}
