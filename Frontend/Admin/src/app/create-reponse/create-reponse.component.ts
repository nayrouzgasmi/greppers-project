import { Component, OnInit } from '@angular/core';
import { Reponse } from '../entity/reponse';
import { ReponseService } from '../services/reponse.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-create-reponse',
  templateUrl: './create-reponse.component.html',
  styleUrls: ['./create-reponse.component.css']
})
export class CreateReponseComponent implements OnInit {
  reponse: Reponse = new Reponse();
  id! : Number;
  constructor(private reponseService:ReponseService,
    private router:Router,private route:ActivatedRoute
    ) { }

  ngOnInit(): void {
  }

  saveTicket(){

    this.id = this.route.snapshot.params['id'];

    this.reponse.ticket_id=this.id;
    
    this.reponseService.addReponse(this.reponse).subscribe( data => {
  console.log(data);
  this.gotoreponseList();
    },
    error => console.log(error));
  }
  
  gotoreponseList(){
  this.router.navigate(['/reponses']);
  }
  
    onSubmit(){
      console.log(this.reponse);
      this.saveTicket();
    }
  
}
