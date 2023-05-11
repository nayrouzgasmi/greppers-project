import { Component } from '@angular/core';
import { ArticleService } from './article.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.sass']
})

export class ArticleComponent {
  article: any;

  constructor(private articleService: ArticleService, private route: ActivatedRoute) { }

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.articleService.getArticle(id).subscribe((data) => (this.article = data));
  }
}
