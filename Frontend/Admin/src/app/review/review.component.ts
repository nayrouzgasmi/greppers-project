import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../services/ReviewServices';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import 'sweetalert2/src/sweetalert2.scss';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  reviews = [] as any;
  isLoading = true as boolean;
  pageNo = 0;
  pageSize = 1
  totalPages = 1
  totalItems = 0
  currentPage = 0
  displayModal = 'none'
  reviewToDelete = { id: 0 }

  constructor(private reviewService: ReviewService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getReviews();
  }

  getReviews() {
    this.reviewService.getReviews(this.pageNo, 10).subscribe(
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
  changePageNo(numP: number) {
    this.pageNo = numP;
    this.getReviews();
  }

  changeDisplayModal(value: string) {
    this.displayModal = value;
  }
  setReviewToDelete(i: any) {
    this.reviewToDelete = i;
  }
  deleteReview() {
    this.isLoading = true;
    this.reviewService.deleteReview(this.reviewToDelete.id).subscribe(
      (resp) => {
        this.getReviews();
        this.displayModal = 'none'
        Swal.fire({
          icon: 'info',
          title: 'deleted item',
          showConfirmButton: false,
          timer: 3000,
        });

      },
      (err) => {
        console.log(err);

      }
    );

  }

}
