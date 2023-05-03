import { Component, OnInit } from '@angular/core';
import { ClientService } from './client.service';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {
  sellerDetails = null as any ;

  username: string = '';
 password: string = '';
 email: string = '';
 phone_number: string = '';

 user_roles: any = [
   {name:'Client', value:'Client', selected: false},
   {name:'Marchant', value:'Marchant', selected: false},
 ]

 selectedRoles: string[] = [];

 error: string = '';
 success: string = '';
   sellerToUpdate={
   id:"",
   username:"",
   email:"",
   phone_number:""
  }
 constructor(private sellerService: ClientService) {
   this.getSellersDetails();
  }

 ngOnInit(): void {
 }
getSellersDetails(){
 this.sellerService.getClients().subscribe(
   (resp) => {
     console.log(resp);
     this.sellerDetails = resp;
   },
   (err)=>{
     console.log(err);
   }
 );
}
edit(seller: any){
 this.sellerToUpdate=seller;
}

deleteSeller(client:any){
 this.sellerService.deleteClient(client.id).subscribe(
   (resp)=>{
     console.log(resp);
     this.getSellersDetails();
   },
   (err)=>{
     console.log(err);
   }
 );
}


}
