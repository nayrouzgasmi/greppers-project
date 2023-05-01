import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { httpOptions } from '../utils/httpOptions';

@Injectable({
  providedIn: 'root'
})
export class StoresService {
  public baseUrl="http://localhost:8080/api/stores";

  constructor(private http:HttpClient) { }
  
  public getStores(): Observable<any> { 
    return this.http.get(this.baseUrl,httpOptions); 
   }
}
