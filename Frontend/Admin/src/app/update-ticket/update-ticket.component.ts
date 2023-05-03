import { Component, OnInit } from '@angular/core';
import { TicketService } from '../services/ticket.service';
import { Ticket } from '../entity/ticket';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-ticket',
  templateUrl: './update-ticket.component.html',
  styleUrls: ['./update-ticket.component.css']
})
export class UpdateTicketComponent implements OnInit {
  id!: Number;
  ticket: Ticket = new Ticket();
  constructor(private ticketService:TicketService,
    private route:ActivatedRoute,
    private router:Router) { }

  ngOnInit(): void {
this.id = this.route.snapshot.params['id'];
    this.ticketService.getticketbyid(this.id).subscribe(data => {
      this.ticket = data;

    }
    , error => console.log(error));
  }

  

  gototicketList(){
    this.router.navigate(['/tickets']);
    }
    
    onSubmit(){
        this.ticketService.editTicket(this.id,this.ticket).subscribe(data =>{
          console.log(data);
          this.ticket = new Ticket();
          this.gototicketList();
    
    }, error => console.log(error));      }

}
