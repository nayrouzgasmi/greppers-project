import { Component, OnInit } from '@angular/core';
import { SellerService } from './seller.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-seller',
  templateUrl: './seller.component.html',
  styleUrls: ['./seller.component.css']
})
export class SellerComponent implements OnInit {
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
  constructor(private sellerService: SellerService) {
    this.getSellersDetails();
   }

  ngOnInit(): void {
  }
getSellersDetails(){
  this.sellerService.getSellers().subscribe(
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
updateSeller(){
  this.sellerService.updateSellers(this.sellerToUpdate).subscribe(
    (resp)=>{
      console.log(resp);
    },
    (err)=>{
      console.log(err);
    }
  );
}
deleteSeller(seller:any){
  this.sellerService.deleteSellers(seller.id).subscribe(
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
