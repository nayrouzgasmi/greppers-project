import { Component, OnInit } from '@angular/core';
import { ReponseService } from '../services/reponse.service';

import { Router } from '@angular/router';
import { Reponse } from '../entity/reponse';

@Component({
  selector: 'app-reponse-list',
  templateUrl: './reponse-list.component.html',
  styleUrls: ['./reponse-list.component.css']
})
export class ReponseListComponent implements OnInit {

  reponses : Reponse[]=[];

  constructor( private reponseservice : ReponseService ,private router: Router) { }


  ngOnInit(): void {
    this.getReponse();
    
  }
  private getReponse(){
      this.reponseservice.getAllReponse().subscribe (datas =>{
        this.reponses = datas as Reponse [];
      } )
  }

  updateReponse  (id : Number){
this.router.navigate(['update-reponse',id]);
  }


  deleteReponse  (id : Number){
    if(confirm('are you sure to delete this response ?'))

    this.reponseservice.deleteReponse(id).subscribe(data => {
      alert("Succefully delete")
      this.getReponse();
    })
      }

}
