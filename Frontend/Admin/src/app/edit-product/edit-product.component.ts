import { Component, OnInit } from '@angular/core';
import { EditProductService } from './edit-product.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css'],
})
export class EditProductComponent implements OnInit {
  // productData: any = { name: '' };
  name: string = 'hello';
  product: {
    name: string;
    description: string;
    price: number;
    quantity: number;
    bioScore: number;
    tags: any[];
    compositions: { name: string; description: string; quantity: number }[];
    imageUrls:string[]
    // imageUrls: any;
  } = {
    name: '',
    description: '',
    price: 0,
    quantity: 0,
    bioScore: 0,
    tags: [],
    compositions: [],
    imageUrls:[]
    // imageUrls: [],
  };
  composition = { name: 'string', description: 'string', quantity: 0 };
  multipartFile: any;
  data: FormData = new FormData();
  filesToUpload: any[] = [];
  formData: any = { name: 'hello' };
  myData: string = '';
  id: number = 0;
  public addTag(tagToPush: any) {
    this.product.tags.push({ name: tagToPush.target.value });
    tagToPush.target.value = '';
  }
  public popTag(tagToPop: any) {
    const index = this.product.tags.findIndex(
      (tag) => tag.name === tagToPop.name
    );
    if (index !== -1) {
      this.product.tags.splice(index, 1);
    }
  }
  public addComposition(event:any) {
    if (!this.composition.name||!this.composition.description||!this.composition.quantity) {
      return
    }
    this.product.compositions.push(this.composition);
    this.composition = { name: '', description: '', quantity: 0 };
  }
  public popComposition(compositionToPop:any) {
    const index=this.product.compositions.findIndex(comp=>comp.name===compositionToPop.name);
    if (index !== -1) {
      this.product.compositions.splice(index, 1);
    }
  }
  public saveFiles(files: any) {
    const fileList: FileList = files.target.files;
    // this.filesToUpload = files.target.files;
    for (let i = 0; i < fileList.length; i++) {
      const file: File = fileList[i];
      this.data.append('file', file);
    }
    if (files.target.files && files.target.files[0]) {
      var filesAmount = files.target.files.length;
      for (let i = 0; i < filesAmount; i++) {
        let reader = new FileReader();

        reader.onload = (event: any) => {
          this.filesToUpload.push(event.target.result);
        };

        reader.readAsDataURL(files.target.files[i]);
      }
    }
    // data.append('files', files.target.files);
    // console.log('files', files.target.files)
    // this.addProductService.saveFiles(data);
  }
  public deleteFiles() {
    console.log(this.data.getAll('file'));
    this.data.delete('file');
    // this.data.delete('files');
    this.filesToUpload = [];
  }

  constructor(
    private route: ActivatedRoute,
    private editProductService: EditProductService,
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.editProductService.getProduct(this.id).subscribe((data) => {
      this.product = data;
    });
  }

  submitForm() {
    // this.editProductService.updateProduct(this.id,this.product);
    this.data.append('product', JSON.stringify(this.product));
    // You can send the form data to your server or do other operations here
  }
}
