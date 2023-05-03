import { Component, OnInit } from '@angular/core';
import { EChartsOption } from 'echarts';
import { Stat } from '../entity/stat';
import { ReponseService } from '../services/reponse.service';

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
  constructor(private reponseservice:ReponseService) { }

  ngOnInit(): void {
  this.getstat();
  }

  private getstat(){
    this.reponseservice.getstatbyid().subscribe (datas =>{
      this.stat = datas as Stat;
      this.val= this.stat.traite;
      this.val2=this.stat.nontraite;
      this.tosubmit();

    } )
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
          data: [this.val, 200],
          type: 'bar',
          showBackground: true,
          backgroundStyle: {
            color: 'rgba(180, 180, 180, 0.2)'
          }
        }
      ]
    };
    
  }

}
