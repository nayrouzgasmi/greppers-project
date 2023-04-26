import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Request } from '../model/sign-up-request';

import { ActivatedRoute, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
	providedIn: 'root'
})
export class AuthService {
	
	private baseUrl = 'http://localhost:8080/';
	isLoggedIn = false;


	constructor(private cook: CookieService,private route: ActivatedRoute, private router: Router, private http: HttpClient) { }

	signin(email: any,password: any): Observable<any> {
		return this.http.post<any>(this.baseUrl + 'signin', {email,password}, {headers: new HttpHeaders({ 'Content-Type': 'application/json' })}).pipe(map((resp) => {
			sessionStorage.setItem("email",resp.email)
          sessionStorage.setItem("token",`Bearer ${resp.token}`)
			this.cook.set("email",resp.email)
             this.cook.set("token",`Bearer ${resp.token}`)
			return resp;
		}));
		this.isLoggedIn = true;
	}

	signup(request: Request): Observable<any> {
		return this.http.post<any>(this.baseUrl + 'signup', request, {headers: new HttpHeaders({ 'Content-Type': 'application/json' }), responseType: 'text' as 'json'}).pipe(map((resp) => {                                                         
			return resp;
		}));
	}


	
	userActive(email: any,password: any): Observable<any>{
		return this.http.post<any>(`${this.baseUrl}active`,{email,password}).pipe(
		  map(
			response => {
			  return response;
			}
		  )
		)
	  }


	activeAccount(mail: any,code: any):Observable<any>{
		return this.http.post<any>(`${this.baseUrl}activated`,{mail,code}).pipe(
		  map(
			response => {
			  return response;
			}
		  )
		)
	  }
	
	  checkEmail(email: any):Observable<any>{
		return this.http.post<any>(`${this.baseUrl}checkEmail`,{email}).pipe(
		  map(
			response => {
			  return response;
			}
		  )
		)
	  }
	
	  resetPassword(email: any,code: any,password: any):Observable<any>{
		return this.http.post<any>(`${this.baseUrl}resetPassword`,{email,code,password}).pipe(
		  map(
			response => {
			  return response;
			}
		  )
		)
	  }

	signout() {
		sessionStorage.removeItem('email');
		sessionStorage.removeItem('token');

		this.router.navigateByUrl('login');
	}

	isUserSignedin() {
		return sessionStorage.getItem('token') !== null;
		
	}

	getSignedinUser() {
		return sessionStorage.getItem('user') as string;
		
	}
	getSignInPhoneNumber() {
		return sessionStorage.getItem('phone_number') as string;
		
	}
	isLogin(){
		return !(sessionStorage.getItem('email') == null ||
			   sessionStorage.getItem('token') == null);
	  }
	getToken() {
		let token = sessionStorage.getItem('token') as string;
		return token;
	}

}