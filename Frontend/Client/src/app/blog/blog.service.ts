import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { httpOptions } from 'src/app/utils/httpOptions';

@Injectable({
  providedIn: 'root'
})

export class BlogService {
  public baseUrl = "http://localhost:8080/articles"

  constructor(private http:HttpClient) { }

  public getArticles(): Observable<any> {
    return this.http.get(this.baseUrl, httpOptions);
  }
}
