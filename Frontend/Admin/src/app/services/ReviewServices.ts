import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Request } from '../model/sign-up-request';


@Injectable({
    providedIn: 'root'
})
export class ReviewService {

    BASE = 'http://localhost:8080';

    constructor(private http: HttpClient) {
    }

    public getReviews(pageNo: number, pageSize: number) {
        return this.http.get(this.BASE + '/api/reviews/list?pageNo=' + pageNo + '&pageSize=' + pageSize, { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*', Authorization: `Bearer ${localStorage.getItem('token')}`, }) })
    }

    public getReviewById(id: number) {
        return this.http.get(this.BASE + '/api/reviews/' + id, { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*', Authorization: `Bearer ${localStorage.getItem('token')}`, }) })
    }

    public getProducts() {
        return this.http.get(this.BASE + '/api/products', { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*', Authorization: `Bearer ${localStorage.getItem('token')}`, }) })
    }

    public updateReview(id: number, review: any) {
        return this.http.put<any>(this.BASE + '/api/reviews/' + id, review, { headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*', Authorization: `Bearer ${localStorage.getItem('token')}`, }) }).pipe(map((resp) => {
            return resp;
        }));

    }

    public deleteReview(id: any) {
        return this.http.delete(this.BASE + '/api/reviews/' + id, { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*', Authorization: `Bearer ${localStorage.getItem('token')}`, }) })

    }

    addReview(formData: any): Observable<any> {
        return this.http.post<any>(this.BASE + '/api/reviews', formData, { headers: new HttpHeaders({ 'Access-Control-Allow-Origin': '*', Authorization: `Bearer ${localStorage.getItem('token')}`, }) }).pipe(map((resp) => {
            return resp;
        }));
    }


    signup(request: Request): Observable<any> {
        return this.http.post<any>(this.BASE + '/signup', request, { headers: new HttpHeaders({ 'Content-Type': 'application/json' }), responseType: 'text' as 'json' }).pipe(map((resp) => {
            return resp;
        }));
    }
}
