import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class ArticleService {
  constructor(private http:HttpClient) { }
  
  baseUrl = 'http://localhost:8080/articles';
  
  public getArticles(){
    return this.http.get(this.baseUrl)
  }

  public updateArticle(article: any){
    return this.http.put(this.baseUrl, article)

  }

  public deleteArticle(id: any){
    return this.http.delete(this.baseUrl + id)
  }
}
