import { Component } from '@angular/core';
import { ProductService } from './product.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
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
  displayModal = 'none'
  reviewToDelete = { id: 0 }
  loadButtonSubmit: boolean = false;
  reviewToSave: any = {
    comment: '',
    userName: '',
    isActive: false,
    note: 0,
    product: 0,
  };
  uploadForm: any = null;

  constructor(private route: ActivatedRoute,private productService: ProductService, private router: Router) {}
  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.productService.getProduct(id).subscribe((data) => (this.product = data));
   

    this.route.params.subscribe(s => {
      this.getReviewsById(s["id"]); 
    });
  }
  getReviewsById(id:number){
    this.productService.getReviewsByProduct(id,this.pageNo, 3).subscribe(
      (resp: any) => {

        this.reviews = resp.reviews;
        this.totalPages = resp.totalPages
        this.totalItems = resp.totalItems
        this.currentPage = resp.currentPage
        this.isLoading = false;

      },
      (err:any) => {
        console.log(err);
      }
    );

  }
   addReview() {
    const id = this.route.snapshot.params['id'];
    this.loadButtonSubmit = true;
    const formData = new FormData();
    formData.append('file', this.uploadForm);
    formData.append('comment', this.reviewToSave.comment);
    formData.append('note', this.reviewToSave.note);
    formData.append('userName', this.reviewToSave.userName);
    formData.append('active', this.reviewToSave.isActive);
    formData.append('product', this.reviewToSave.product);
    this.productService.addReview(formData,id).subscribe(
      (result) => {
        this.loadButtonSubmit = false;
        Swal.fire({
          icon: 'success',
          title: 'Review of ' + result.userName + ' has been saved',
          showConfirmButton: false,
          timer: 5000,
        });
        this.router.navigate(['/', 'products']);
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
