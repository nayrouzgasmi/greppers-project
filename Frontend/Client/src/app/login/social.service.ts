import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SocialService {

  private baseUrl = 'http://localhost:8080/social';

  constructor(private http: HttpClient,
              private cook: CookieService) { }

  loginWithGoogle(token: any): Observable<any>{
    return this.http.post<any>(`${this.baseUrl}/google`,{token}).pipe(
      map(
        response => {
          sessionStorage.setItem("email",response.email)
          sessionStorage.setItem("token",`Bearer ${response.token}`)
          this.cook.set("email",response.email)
          this.cook.set("token",`Bearer ${response.token}`)
          return response;
        }
      )
    )
  }
  
  loginWithFacebook(token: any): Observable<any>{
    return this.http.post<any>(`${this.baseUrl}/facebook`,{token}).pipe(
      map(
        response => {
          sessionStorage.setItem("email",response.email)
          sessionStorage.setItem("token",`Bearer ${response.token}`)
          this.cook.set("email",response.email)
          this.cook.set("token",`Bearer ${response.token}`)
          return response;
        }
      )
    )
  }
}

