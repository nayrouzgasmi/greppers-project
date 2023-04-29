import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import { Event } from "./event.model";

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private readonly apiUrl = 'http://localhost:8080/events';

  constructor(private http: HttpClient) {}

  getEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.apiUrl}/getEvents`);
  }

  getEvent(id:number): Observable<Event>{
    return this.http.get<Event>(`${this.apiUrl}/searchEvent/`+id);
  }

  createEvent(event: Event): Observable<Event> {
    console.log(event);
    return this.http.post<Event>(`${this.apiUrl}/addEvent`, event);
  }

  updateEvent(event: Event): Observable<Event> {

    return this.http.put<Event>(`${this.apiUrl}/updateEvent`, event);
  }

  deleteEvent(event: Event): Observable<Event> {

    return this.http.request<Event>('delete', `${this.apiUrl}/deleteEvent`, { body: {
      "id":event.id
      } });
      }
}
