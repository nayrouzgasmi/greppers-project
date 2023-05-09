import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../services/ReviewServices';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-review',
  templateUrl: './create-review.component.html',
  styleUrls: ['./create-review.component.css'],
})
export class CreateReviewComponent implements OnInit {
  localUrl: any = 'assets/imgs/people/images.png';
  listProducts: any = [];
  loadButtonSubmit: boolean = false;
  uploadForm: any = null;
  focusFile: number = 0;
  isLoading: boolean = true;
  reviewToSave: any = {
    comment: '',
    userName: '',
    isActive: false,
    note: 0,
    product: 0,
  };

  constructor(private reviewService: ReviewService, private router: Router) { }

  ngOnInit(): void {
    this.getProducts();
  }

  // construction du blob
  onFileSelect(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.uploadForm = file;

      //charger blob image et changer src
      var reader = new FileReader();
      reader.onload = (event: any) => {
        this.localUrl = event.target.result;
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  }

  //charger la liste des produits

  getProducts() {
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
  //ajouter un review avec formdata
  addReview() {
    this.loadButtonSubmit = true;
    const formData = new FormData();
    formData.append('file', this.uploadForm);
    formData.append('comment', this.reviewToSave.comment);
    formData.append('note', this.reviewToSave.note);
    formData.append('userName', this.reviewToSave.userName);
    formData.append('active', this.reviewToSave.isActive);
    formData.append('product', this.reviewToSave.product);
    this.reviewService.addReview(formData).subscribe(
      (result) => {
        this.loadButtonSubmit = false;
        Swal.fire({
          icon: 'success',
          title: 'Review of ' + result.userName + ' has been saved',
          showConfirmButton: false,
          timer: 5000,
        });
        this.router.navigate(['/', 'review']);
      },
      (err) => {
        console.log(err);
      }
    );
  }

  changeFn() {
    this.reviewToSave.isActive == true ? false : true;
  }
  // controle de saisit sur input file
  focusFileFunction() {
    this.focusFile = 1;
  }
}
