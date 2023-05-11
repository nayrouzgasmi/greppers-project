import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { httpOptions } from 'src/app/utils/httpOptions';

@Injectable({
  providedIn: 'root'
})

export class ArticleService {
  public baseUrl = "http://localhost:8080/articles"
  
  constructor(private http: HttpClient, private route: ActivatedRoute) {}

  public getArticle(id: number): Observable<any> {
     return this.http.get(this.baseUrl + id, httpOptions);
  }
}
