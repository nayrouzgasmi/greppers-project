import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization:
      'Bearer ' +
      'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJraGFsaWwuY2hldHRhb3VpMDZAZ21haWwuY29tIiwiZXhwIjoxNjgzNTcyNTA0fQ.Ji-N0L_8aTjyuuiU_TaqKP5M1k6c9r1qXHbNJgd6--NpWLEgeadlG3Yms2aSYGooh7iGA0f9JvVjht0XC_3Bog',
  }),
};
@Injectable({
  providedIn: 'root',
})
export class ProductService {
  public id = this.route.snapshot.paramMap.get('id');
  baseUrl = 'http://localhost:8080/api/products/';
  constructor(private http: HttpClient, private route: ActivatedRoute) {}
  public getProduct(id: number): Observable<any> {
    console.log(this.id);
    return this.http.get(this.baseUrl + id, httpOptions);
  }
}
