import { Component, OnInit } from '@angular/core';
import { Reponse } from '../entity/reponse';
import { ReponseService } from '../services/reponse.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-reponse',
  templateUrl: './update-reponse.component.html',
  styleUrls: ['./update-reponse.component.css']
})
export class UpdateReponseComponent implements OnInit {
  id! :  Number;
  reponse: Reponse = new Reponse();
  constructor(private reponseService:ReponseService,
    private route:ActivatedRoute,
    private router:Router) { }

  ngOnInit(): void {
this.id = this.route.snapshot.params['id'];
    this.reponseService.getreponsebyid(this.id).subscribe(data => {
      this.reponse = data;

    }
    , error => console.log(error));
  }

  

  gotoreponseList(){
    
    this.router.navigate(['/reponses']);
    }
    
    onSubmit(){
        this.reponseService.editReponse(this.id,this.reponse).subscribe(data =>{
          console.log(data);
          this.reponse = new Reponse();
          
          this.gotoreponseList();
    
    }, error => console.log(error));      }

}
