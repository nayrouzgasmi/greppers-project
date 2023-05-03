import { Component, OnInit } from '@angular/core';
import {Ticket } from '../entity/ticket';
import { TicketService } from '../services/ticket.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {
 

  tickets: Ticket[] = [];
  

  constructor(private ticketservice : TicketService,
    private router: Router) {
   }

  ngOnInit(): void {
    this.getTicket();
    
  }
  private getTicket(){
      this.ticketservice.getAllTicket().subscribe (datas =>{
        this.tickets = datas as Ticket[];
      } )
  }

  updateTicket  (id : Number){
this.router.navigate(['update-ticket',id]);
  }


  deleteTicket  (id : Number){

    this.ticketservice.deleteTicket(id).subscribe(data => {
      console.log(data);
      this.getTicket();
    })
      }

      reponseticket  (id : Number){
        this.router.navigate(['create-reponse',id]);
          }
}
