import { Component } from '@angular/core';
import { ProductService } from './product.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { ReviewService } from '../services/ReviewService';
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.sass'],
})
export class ProductComponent {
  productData: any;
  product: any;
  reviews = [] as any;
  isLoading = true as boolean;
  pageNo = 0;
  pageSize = 1
  totalPages = 1
  totalItems = 0
  currentPage = 0
  constructor(private route: ActivatedRoute,private productService: ProductService, private router: Router,private reviewService: ReviewService) {}
  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.productService.getProduct(id).subscribe((data) => (this.product = data));
   

    this.route.params.subscribe(s => {
      this.getReviewsById(s["id"]); 
    });
  }
  getReviewsById(id:number){
    this.reviewService.getReviewsByProduct(id,this.pageNo, 3).subscribe(
      (resp: any) => {

        this.reviews = resp.reviews;
        this.totalPages = resp.totalPages
        this.totalItems = resp.totalItems
        this.currentPage = resp.currentPage
        this.isLoading = false;

      },
      (err) => {
        console.log(err);
      }
    );

  }
  changePageNo() {
    if(this.pageNo < this.totalPages-1){
      this.pageNo = this.pageNo+1;
    }else{
      this.pageNo = 0 ;
    }
    this.route.params.subscribe(s => {
      this.getReviewsById(s["id"]); 
    });

  }

}
