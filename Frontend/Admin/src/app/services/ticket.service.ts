import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { Observable } from 'rxjs';
import { Ticket } from '../entity/ticket';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  readonly API_URL ="http://localhost:8080/ticket"


  constructor(private httpClient: HttpClient) { }


  getAllTicket(): Observable<Ticket[]> {
    return this.httpClient.get<Ticket[]>(`${this.API_URL}/retrieve-all-tickets`);
  }
  getTicketbystatus(): Observable<Ticket[]> {
    return this.httpClient.get<Ticket[]>(`${this.API_URL}/retrieve-all-ticketss`);
  }
  
  addTicket(ticket : Ticket,id:number):Observable<Object> {
    console.log(id)
    return this.httpClient.post(`${this.API_URL}/retrieve-all-tickets/${1}/${id}`, ticket);
  }
  
  editTicket(id:Number,ticket : any):Observable<Object>{
    return this.httpClient.put(`${this.API_URL}/retrieve-all-tickets/${id}`, ticket);
  }

  getticketbyid(id : Number):Observable<Ticket>{
    return this.httpClient.get<Ticket>(`${this.API_URL}/retrieve-Ticket/${id}`);
  }

  deleteTicket(id : Number):Observable<Object>{
    return  this.httpClient.delete(`${this.API_URL}/retrieve-all-tickets/${id}`);
  }





}