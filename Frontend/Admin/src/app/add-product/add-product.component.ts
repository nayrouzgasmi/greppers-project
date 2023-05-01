import { Component, OnInit } from '@angular/core';
import { AddProductService } from './add-product.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css'],
})
export class AddProductComponent implements OnInit {
  product: {
    name: string,
    description:string,
    price:number,
    quantity:number,
    bioScore:number,
    tags:any[]
  } = {
    name: '',
    description:'',
    price:0,
    quantity:0,
    bioScore:0,
    tags:[],
  };
  selectedTags: any[] = [];

  constructor(private addProductService: AddProductService) {}

  ngOnInit(): void {}
  public popTag(tagToPop: string) {
    this.product.tags = this.selectedTags.filter((tag) => tag !== tagToPop);
  }
  public addTag(tagToPush: any) {
    this.product.tags.push({ name: tagToPush.target.value });
    tagToPush.target.value = '';
  }
}
