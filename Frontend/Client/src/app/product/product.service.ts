import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { httpOptions } from '../utils/httpOptions';


@Injectable({
  providedIn: 'root',
})
export class ProductService {
  public id = this.route.snapshot.paramMap.get('id');
  baseUrl = 'http://localhost:8080/api/products/';
  constructor(private http: HttpClient, private route: ActivatedRoute) {}
  public getProduct(id: number): Observable<any> {
    return this.http.get(this.baseUrl + id, httpOptions);
  }
}
