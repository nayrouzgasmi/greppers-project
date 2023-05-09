import { Injectable } from '@angular/core';
import { TicketDTO } from './entity/ticketdto';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Ticket } from './entity/ticket';
import { Stat } from './entity/stat';

@Injectable({
  providedIn: 'root'
})
export class TicketdtoService {
  constructor(private httpClient: HttpClient) { }

  readonly API_URL ="http://localhost:8080/reponseTicket";

  getstatistique( ticketdto : TicketDTO):Observable<Stat>{

    return this.httpClient.post<Stat>(`${this.API_URL}/statistiqueDate`,ticketdto);
  }


}
