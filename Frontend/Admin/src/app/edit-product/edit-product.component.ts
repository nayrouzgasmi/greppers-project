import { Component, OnInit } from '@angular/core';
import { EditProductService } from './edit-product.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css'],
})
export class EditProductComponent implements OnInit {
  // productData: any = { name: '' };
  name: string = 'hello';
  product: any = { name: 'hello' };
  formData: any = { name: 'hello' };
  myData: string = '';
  id: number = 0;
  constructor(
    private route: ActivatedRoute,
    private editProductService: EditProductService,
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.editProductService.getProduct(this.id).subscribe((data) => {
      this.product = data;
    });
  }

  submitForm() {
    this.editProductService.updateProduct(this.id,this.product);
    // You can send the form data to your server or do other operations here
  }
}
