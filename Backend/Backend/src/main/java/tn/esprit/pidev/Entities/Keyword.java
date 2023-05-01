package tn.esprit.pidev.Entities;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "keywords")
public class Keyword {
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToMany(mappedBy = "keywords")
    private Set<Article> articles;

    private String name;

    // Constructors
    public Keyword() {
    }

    public Keyword(String name) {
        this.name = name;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Keyword { " +
	    "id = " + id +
	    ", articles = " + articles +
	    ", name = " + name +
	    " }";
    }
}
