// For more information, see section 2.6.11 of https://docs.jboss.org/hibernate/orm/5.0/userguide/html_single/Hibernate_User_Guide.html
package tn.esprit.pidev.Entities;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "articles")
    private Set<User> authors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "article_keywords", 
	       joinColumns = @JoinColumn(name =  "article_id"),
	       inverseJoinColumns = @JoinColumn(name = "keyword_id"))
    private Set<Keyword> keywords;
    
    @NotBlank
    @Lob
    private String content;

    private LocalDate published;
    
    // Getters
    public UUID getId() {
        return id;
    }

    public Set<User> getAuthors() {
        return authors;
    }

    public Set<Keyword> getKeywords() {
        return keywords;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getPublished() {
        return published;
    }

    // Setters
    public void setId(UUID id) {
        this.id = id;
    }

    public void setAuthors(Set<User> authors) {
        this.authors = authors;
    }

    public void setKeywords(Set<Keyword> keywords) {
        this.keywords = keywords;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "Article { " +
	    "id = " + id +
	    ", authors = " + authors +
	    ", keywords = " + keywords +
	    ", published = " + published +
	    " }";
    }
}
