import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {Chart, ChartConfiguration, ChartItem, registerables} from 'node_modules/chart.js'
import { AuthService } from '../login/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  chart!: Chart<"doughnut", number[], string>;

  constructor(private http: HttpClient,private auth:AuthService) { }

  totalClients!: number;
  totalMarchants!: number;
  totalUsers!: number;
  totalAdmins!: number;
  
  signout() {
    this.auth.signout();
  }
  ngOnInit() {
   

    this.http.get(' http://localhost:8080/users').subscribe((data: any) => {
       this.totalClients = data['totalClients'];
       this.totalMarchants = data['totalMarchants'];
       this.totalUsers = data['totalUsers'];
       this.totalAdmins = data['totalAdmins'];
       this.createChart();


    });
    }

    createChart(){
      Chart.register(...registerables);
      const data = {
        labels: ['Admins','Clients','Marchants'],
        datasets: [{
          backgroundColor: ['#0694a2', '#1c64f2', '#7e3af2'],
          borderColor: 'rgb(255, 99, 132)',
          data: [this.totalAdmins,this.totalClients,this.totalMarchants],
        }]
  };
  const options = {
   
    responsive : true,
    cutoutPercentage: 80,

  }
  const config: ChartConfiguration = {
    type: 'doughnut',
    data: data,
    options: options
  }

  const chartItem: ChartItem = document.getElementById('my-chart') as ChartItem
  new Chart(chartItem, config)

    }
   
}

