package tn.esprit.pidev.Controllers;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import tn.esprit.pidev.Repositories.ArticleRepository;
import tn.esprit.pidev.Services.ArticleService;
import tn.esprit.pidev.Entities.Article;

@RestController
@AllArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    ArticleService service;
    
    @GetMapping
    public List<Article> all() {
        return service.all();
    }

    @GetMapping("/get/{id}")
    public Article get(@PathVariable("id") UUID id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") UUID id) {
        service.delete(id);
    }

    @PutMapping("/update")
    public Article update(@RequestBody Article article) {
        return service.update(article);
    }

    @PostMapping("/post")
    public Article create(@RequestBody Article article) {
        return service.create(article);
    }
}
