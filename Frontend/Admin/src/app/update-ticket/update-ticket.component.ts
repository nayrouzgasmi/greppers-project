import { Component, OnInit } from '@angular/core';
import { TicketService } from '../services/ticket.service';
import { Ticket } from '../entity/ticket';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup ,Validators } from '@angular/forms';

@Component({
  selector: 'app-update-ticket',
  templateUrl: './update-ticket.component.html',
  styleUrls: ['./update-ticket.component.css']
})
export class UpdateTicketComponent implements OnInit {
  id!: Number;
  ticket: Ticket = new Ticket();
  registerForm!: FormGroup;
  badword=false;

  submitted = false;
  constructor(private ticketService:TicketService,
    private route:ActivatedRoute,
    private router:Router,private formBuilder: FormBuilder) { }

  ngOnInit(): void {
this.id = this.route.snapshot.params['id'];
    this.ticketService.getticketbyid(this.id).subscribe(data => {
      this.ticket = data;

    }
    , error => console.log(error));
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

  

  gototicketList(){
    this.router.navigate(['/tickets']);
    }
    
    onSubmit(){
      this.submitted = true;

      // stop here if form is invalid
      if (this.registerForm.invalid) {
          return;
      }
     
  
     
        this.ticketService.editTicket(this.id,this.ticket).subscribe(data =>{
          console.log(data);
          this.ticket = new Ticket();
          this.gototicketList();
    
    }, error => console.log(error));      }

}
