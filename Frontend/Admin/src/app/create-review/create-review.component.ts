import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../services/ReviewServices';

@Component({
  selector: 'app-create-review',
  templateUrl: './create-review.component.html',
  styleUrls: ['./create-review.component.css']
})
export class CreateReviewComponent implements OnInit {
  listProducts: any = []
  uploadForm: any
  isLoading: boolean = true
  reviewToSave: any = {
    comment: '',
    userName: '',
    isActive: false,
    note: 0,
    product: 0
  }

  constructor(private reviewService: ReviewService) { }




  onFileSelect(event: any) {
    if (event.target.files.length > 0) {
      console.log(event)
      const file = event.target.files[0];
      this.uploadForm = file;
    }
  }
  ngOnInit(): void {
    this.getReviews()
  }
  //charger la liste des produits

  getReviews() {
    this.reviewService.getProducts().subscribe(
      (resp: any) => {
        this.isLoading = false;
        this.listProducts = resp;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  addReview() {
    const formData = new FormData();
    formData.append('file', this.uploadForm);
    formData.append('comment', this.reviewToSave.comment);
    formData.append('note', this.reviewToSave.note);
    formData.append('userName', this.reviewToSave.userName);
    formData.append('active', this.reviewToSave.isActive);
    formData.append('product', this.reviewToSave.product)
    this.reviewService.addReview(formData).subscribe((result) => {

      console.log(result)

    }, (err) => {
      console.log(err)
    });

  }

  changeFn() {
    this.reviewToSave.isActive == true ? false : true;
  }


}
