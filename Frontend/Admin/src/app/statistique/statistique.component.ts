import { Component, OnInit } from '@angular/core';
import { EChartsOption } from 'echarts';
import { Stat } from '../entity/stat';
import { ReponseService } from '../services/reponse.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup ,Validators } from '@angular/forms'; 
import { TicketdtoService } from '../ticketdto.service';
import { TicketDTO } from '../entity/ticketdto';

@Component({
  selector: 'app-statistique',
  templateUrl: './statistique.component.html',
  styleUrls: ['./statistique.component.css']
})
export class StatistiqueComponent implements OnInit {
chartOption!:EChartsOption;
stat:Stat = new Stat();
 val !:any;
 val2 !:any;
 creationDate1!:Date;
ticketdtoe:TicketDTO = new TicketDTO();
 creationDate2!:Date;

  constructor(private ticketdto:TicketdtoService) { }

  ngOnInit(): void {
  }

  private getstat(creationDate11:Date,creationDate22:Date){
    this.ticketdtoe.dateDebut=creationDate11;
    this.ticketdtoe.dateFin=creationDate22;
    this.ticketdto.getstatistique(this.ticketdtoe).subscribe (datas =>{
      this.stat = datas as Stat;
      this.val= this.stat.traite;
      this.val2=this.stat.nontraite;
      this.tosubmit();

    } )
}
printDate(){
  console.log(this.creationDate1);
  console.log(this.creationDate2);
}
  tosubmit()
  {
    console.log(this.val);
    this.chartOption = {
      xAxis: {
        type: 'category',
        data: ['traite', 'nontraite']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [this.val, this.val2],
          type: 'bar',
          showBackground: true,
          backgroundStyle: {
            color: 'rgba(180, 180, 180, 0.2)'
          }
        }
      ]
    };
    
  }
  clearFilter()
  {
    
    this.getstat(this.creationDate1,this.creationDate2);
 
  }
  clearFilter2()
  {
    this.getstat(this.creationDate1,this.creationDate2);

  }
}
