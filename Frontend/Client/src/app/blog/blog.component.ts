import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { BlogService } from './blog.service';

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.sass']
})
export class BlogComponent {
  articles: any;

  constructor(private blogService: BlogService) {
  }

  ngOnInit() {
    this.articles = this.blogService.getArticles();
  }
}
