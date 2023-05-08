import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { httpOptions } from '../utils/httpOptions';

@Injectable({
  providedIn: 'root',
})
export class EditProductService {
  baseUrl = 'http://localhost:8080/api/products/';
  constructor(private http: HttpClient) {}
  public getProduct(id: number): Observable<any> {
    return this.http.get(this.baseUrl + id, httpOptions);
  }
  public updateProduct(id: number, product: any): any {
    return this.http.put(this.baseUrl + id, product, { ...httpOptions }).subscribe(data=>console.log(data))
  }
}
