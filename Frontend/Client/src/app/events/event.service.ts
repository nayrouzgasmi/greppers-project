import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import { Event } from "./event.model";
import { AuthService } from '../security/auth.service';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private readonly apiUrl = 'http://localhost:8080/events';

  constructor(private http: HttpClient,private authService: AuthService) {}

  getEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.apiUrl}/getEvents`, {
      headers: new HttpHeaders({
          'Authorization': this.authService.getToken()
      })} );
  }

  isUserParticipant(eventid:number,userid:number):Observable<boolean>{
    return this.http.get<boolean>(`${this.apiUrl}/isUserParticipant/`+eventid+"/"+userid,{
      headers: new HttpHeaders({
          'Authorization': this.authService.getToken()
      })} );
  }

  getEvent(id:number): Observable<Event>{
    return this.http.get<Event>(`${this.apiUrl}/searchEvent/`+id);
  }

  getParticipantsNumber(eventid:number): Observable<number>
  {
    ///numberOfParticipantsEvent/{event_id}

    return this.http.get<number>(`${this.apiUrl}/numberOfParticipantsEvent/`+eventid)
  }

  getPlacesLeft(eventid:number): Observable<number>
  {
    ///numberOfParticipantsEvent/{event_id}

    return this.http.get<number>(`${this.apiUrl}/nombreDePlacesDisponible/`+eventid)
  }

  //isUserParticipant

  createEvent(event: Event,file:File): Observable<Event> {

    const formData: FormData = new FormData();

    formData.append('file', file);
    formData.append('event', JSON.stringify(event));



    return this.http.post<Event>(`${this.apiUrl}/addEvent`, formData,{
      reportProgress: true,
      responseType: 'json'
    });
  }

  updateEvent(event: Event,file?:File): Observable<Event> {


    const formData: FormData = new FormData();

    formData.append('file', file!);
    formData.append('event', JSON.stringify(event));

    return this.http.put<Event>(`${this.apiUrl}/updateEvent`, formData,{
      reportProgress: true,
      responseType: 'json'
    });
  }

  deleteEvent(event: Event): Observable<Event> {

    return this.http.request<Event>('delete', `${this.apiUrl}/deleteEvent`, { body: {
      "id":event.id
      } });
      }

  participate(eventid:number,userid:number) : Observable<boolean>
  {
    return this.http.post<boolean>(`${this.apiUrl}/participateEvent/`+eventid+"/"+userid,{
      headers: new HttpHeaders({
          'Authorization': this.authService.getToken()
      })} );
  }
  skipEvent(eventid:number,userid:number): Observable<boolean>
  {
    return this.http.post<boolean>(`${this.apiUrl}/revokeParticipateEvent/`+eventid+"/"+userid,{
      headers: new HttpHeaders({
          'Authorization': this.authService.getToken()
      })} );
  }
}
