import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { httpOptions } from '../utils/httpOptions';
import { Request } from '../model/sign-up-request';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  public id = this.route.snapshot.paramMap.get('id');
  BASE = 'http://localhost:8080';
  baseUrl = 'http://localhost:8080/api/products/';
  constructor(private http: HttpClient, private route: ActivatedRoute) {}
  public getProduct(id: number): Observable<any> {
    return this.http.get(this.baseUrl + id, httpOptions);
  }
  public getReviewsByProduct(id: number ,  pageNo: number, pageSize: number) {
    return this.http.get(this.BASE + '/api/reviews/front/'+id +'?pageNo=' + pageNo + '&pageSize=' + pageSize, { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' }) })
}
addReview(formData: any,id:number): Observable<any> {
  return this.http.post<any>(this.BASE + '/api/reviews/'+id, formData, { headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*', Authorization: `Bearer ${localStorage.getItem('token')}`, }) })
}
}
