import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

export const httpOptionsFiles = {
  headers: new HttpHeaders({
    // 'Content-Type': 'multipart/form-data',
    Authorization:
      'Bearer ' +
      'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJraGFsaWwuY2hldHRhb3VpMDZAZ21haWwuY29tIiwiZXhwIjoxNjgzODEzNzIzfQ.U_UOE8z3IK_krIruiqT2W25jUb2xoThh4YwNEYSUranxfo2lpQrGuBLTUhifJCL1Gc7soJkUVEmmPfOX7b1NgQ',
  }),
};
@Injectable({
  providedIn: 'root'
})
export class AddStoreService {
  public storeUrl = 'http://localhost:8080/api/stores/';
  constructor(private http: HttpClient) { }
  addStoreToVendor(id:number,data:any){
    return this.http.post(this.storeUrl+id, data, httpOptionsFiles).subscribe(data=>console.log(data));
  }
}
