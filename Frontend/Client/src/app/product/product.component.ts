import { Component } from '@angular/core';
import { ProductService } from './product.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'Basic ' + btoa('user:5e7131e2-2193-4ca9-9bc7-3206debf0d57'),
  }),
};
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.sass'],
})
export class ProductComponent {
  productData: any;
  product: any;
  constructor(private route: ActivatedRoute,private productService: ProductService, private router: Router) {}
  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.productService.getProduct(id).subscribe((data) => (this.product = data));
  }
}
