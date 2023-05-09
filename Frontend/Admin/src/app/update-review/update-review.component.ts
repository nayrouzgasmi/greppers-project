import { Component, OnInit } from '@angular/core';
import { ReviewService } from '../services/ReviewServices';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-review',
  templateUrl: './update-review.component.html',
  styleUrls: ['./update-review.component.css']
})
export class UpdateReviewComponent implements OnInit {

  localUrl: any = 'assets/imgs/people/images.png';
  listProducts: any = [];
  loadButtonSubmit: boolean = false;
  uploadForm: any = null;
  focusFile: number = 0;
  isLoading: boolean = true;
  isLoadingSingleReview: boolean = true;
  reviewToSave: any = {
    comment: '',
    id: 0,
    userName: '',
    active: false,
    note: 0,
    product: {}

  };

  constructor(private reviewService: ReviewService, private router: Router, private activatedRoute: ActivatedRoute) { }
  ngOnInit(): void {
    this.activatedRoute.params.subscribe(s => {
      this.getReviewById(s["id"]);
    });
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

  getReviewById(id: number) {
    this.reviewService.getReviewById(id).subscribe(
      (resp: any) => {
        console.log(resp)
        this.isLoadingSingleReview = false;
        this.localUrl = 'assets/imgs/people/' + resp.userPhoto
        this.reviewToSave = resp;
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
    formData.append('userPhoto', this.reviewToSave.userPhoto)
    formData.append('file', this.uploadForm);
    formData.append('comment', this.reviewToSave.comment);
    formData.append('note', this.reviewToSave.note);
    formData.append('userName', this.reviewToSave.userName);
    formData.append('active', this.reviewToSave.active);
    formData.append('product', this.reviewToSave.product.id);
    this.reviewService.updateReview(this.reviewToSave.id, formData).subscribe(
      (result) => {
        this.loadButtonSubmit = false;
        Swal.fire({
          icon: 'success',
          title: 'Review of  has been saved',
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
    this.reviewToSave.active == true ? false : true;
  }
  // controle de saisit sur input file
  focusFileFunction() {
    this.focusFile = 1;
  }

}
