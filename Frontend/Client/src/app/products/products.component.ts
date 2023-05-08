import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ProductsService } from './products.service';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization:
      'Bearer ' +
      'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJraGFsaWwuY2hldHRhb3VpMDZAZ21haWwuY29tIiwiZXhwIjoxNjgzNTcyNTA0fQ.Ji-N0L_8aTjyuuiU_TaqKP5M1k6c9r1qXHbNJgd6--NpWLEgeadlG3Yms2aSYGooh7iGA0f9JvVjht0XC_3Bog',
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
    this.products = this.getProducts();
  }

  public getProducts(): Observable<any[]> | any{
    return this.http.get(this.baseUrl, httpOptions);
  }
}
