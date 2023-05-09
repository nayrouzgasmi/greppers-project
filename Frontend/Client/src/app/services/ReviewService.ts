import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';


@Injectable({
    providedIn: 'root'
})
export class ReviewService {

    BASE = 'http://localhost:8080';

    constructor(private http: HttpClient) {
    }

    public getReviewsByProduct(id: number ,  pageNo: number, pageSize: number) {
        return this.http.get(this.BASE + '/api/reviews/front/'+id +'?pageNo=' + pageNo + '&pageSize=' + pageSize, { headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' }) })
    }


}
