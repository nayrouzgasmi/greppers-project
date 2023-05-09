import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { httpOptions } from '../utils/httpOptions';

@Injectable({
  providedIn: 'root'
})
export class StoreService {
  baseUrl = 'http://localhost:8080/api/stores/';
  constructor(private http: HttpClient, private route: ActivatedRoute) { }
  public getStore(id: number): Observable<any> {
    return this.http.get(this.baseUrl + id, httpOptions);
  }
}
