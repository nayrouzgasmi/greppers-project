import { Component, OnInit } from '@angular/core';
import { AddStoreService } from './add-store.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-store',
  templateUrl: './add-store.component.html',
  styleUrls: ['./add-store.component.css'],
})
export class AddStoreComponent implements OnInit {
  store: {
    name: string;
    description: string;
    // imageUrls: any;
  } = {
    name: '',
    description: '',
    // imageUrls: [],
  };
  logo:any;
  banner:any;
  data: FormData = new FormData();
  constructor(
    private addStoreService: AddStoreService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {}
  public saveLogo(files: any) {
    this.logo=files.target.files[0]
    // this.data.append('logo', files.target.files[0]);

  }
  public saveBanner(files: any) {
    this.banner=files.target.files[0]
    // this.data.append('banner', files.target.files[0]);
  }
  public submitForm() {
    this.data.append('store', JSON.stringify(this.store));
    // console.log(this.data.getAll("file"))
    console.log("banner",this.banner)
    console.log("logo",this.logo)
    this.data.append('logo',this.logo)
    this.data.append('banner',this.banner)
    const id = this.route.snapshot.params['id'];
    this.addStoreService.addStoreToVendor(id, this.data);
  }
}
