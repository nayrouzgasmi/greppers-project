import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { httpOptions } from '../utils/httpOptions';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class StoresService {
  public baseUrl="http://localhost:8080/api/stores/";

  constructor(private http:HttpClient,private router:Router) { }
  
  public getStores() { 
    return this.http.get(this.baseUrl,httpOptions); 
   }
   public deleteStore(id:number) { 
    return this.http.delete(this.baseUrl+id,httpOptions).subscribe(data=>data && this.router.navigateByUrl("/stores")); 
   }
}
