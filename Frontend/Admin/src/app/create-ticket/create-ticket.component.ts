import { Component, OnInit } from '@angular/core';
import { Ticket } from '../entity/ticket';
import { TicketService } from '../services/ticket.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup ,Validators } from '@angular/forms';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.css']
})
export class CreateTicketComponent implements OnInit {
  ticket: Ticket = new Ticket();
  badword=false;

  registerForm!: FormGroup;
  submitted = false;
  idGet!:number;

 


  constructor(private ticketService:TicketService,
    private router:Router,private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    ) { }
  
  ngOnInit(): void {
    this.idGet= this.route.snapshot.params['id'];

    console.clear()
    console.log("id product",this.idGet);
    this.registerForm = this.formBuilder.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      creationDate: ['', Validators.required],
      satisfaction: ['', Validators.required],
      type: ['', Validators.required],
      priority: ['', Validators.required],
      
      
  });

    


  }

  get f() { return this.registerForm.controls; }

saveTicket(){
  this.ticket.id_user=1;
  this.ticket.status="PENDING";
  this.ticketService.addTicket(this.ticket,this.idGet).subscribe( data => {
    if (data===null)
    {
      // alert bad words
      this.badword=true;
      
    }
    else
    {
      console.log(data);
      this.gototicketList();
    }

  },
  error => console.log(error));
}

gototicketList(){
this.router.navigate(['/tickets']);
}

  onSubmit(){
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
        return;
    }
   
      this.saveTicket();

   
   

    
  }

}
