import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { httpOptions } from '../utils/httpOptions';

export const httpOptionsFiles = {
  headers: new HttpHeaders({
    // 'Content-Type': 'multipart/form-data',
    Authorization: sessionStorage.getItem('token') || '',
  }),
};
@Injectable({
  providedIn: 'root'
})
export class EditStoreService {
  public baseUrl="http://localhost:8080/api/stores/";


  constructor(private http:HttpClient) { }
  public getStore(id:number) { 
    return this.http.get(this.baseUrl+id,httpOptionsFiles); 
   }
   public editStore(id:number,data:any) { 
    console.log("inside ")
    return this.http.put(this.baseUrl+id,data,httpOptionsFiles).subscribe(data=>console.log(data)); 
   }
}
