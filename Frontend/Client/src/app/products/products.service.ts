import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { httpOptions } from '../utils/httpOptions';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  public baseUrl = "http://localhost:8080/api/products";

  constructor(private http: HttpClient) {}

  public getProducts(): Observable<any> { 
    return this.http.get(this.baseUrl,httpOptions); 
   }
}
