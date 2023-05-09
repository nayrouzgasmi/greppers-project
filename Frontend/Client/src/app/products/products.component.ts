import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ProductsService } from './products.service';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: sessionStorage.getItem('token') || '',
  }),
};
@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.sass'],
})
export class ProductsComponent {
  productData: any;
  // products!: any[];
  products: any | undefined;
  product: any;
  public baseUrl = 'http://localhost:8080/api/products';
  constructor(
    private productService: ProductsService,
    private http: HttpClient
  ) {}
  ngOnInit() {
    // this.productService.getProducts().subscribe(data => {
    //   console.log(data);
    //   this.products = data;
    // })
    // console.log(this.getProducts().subscribe(data=>this.products=data))
    console.log('hello  ', sessionStorage.getItem('token'));
    this.products = this.getProducts();
  }
  public displayScore(product: any) {
    if (product.bioScore >= 80) {
      return 'Healthy & Approved';
    }
    if (product.bioScore < 80 && product.bioScore >= 60) {
      return 'Safe';
    }
    if (product.bioScore < 60 && product.bioScore >= 50) {
      return 'Standard';
    }
    return 'Low';
  }

  public getProducts(): Observable<any[]> | any {
    return this.http.get(this.baseUrl, httpOptions);
  }
}
