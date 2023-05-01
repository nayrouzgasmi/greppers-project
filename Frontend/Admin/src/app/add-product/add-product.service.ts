import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { httpOptions } from '../utils/httpOptions';

@Injectable({
  providedIn: 'root'
})
export class AddProductService {
  public baseUrl="http://localhost:8080/api/tags";
  constructor(private http:HttpClient) { }
  public getTags() { 
    return this.http.get(this.baseUrl,httpOptions); 
   }
}
