import { Component } from '@angular/core';
import { StoresService } from './stores.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-stores',
  templateUrl: './stores.component.html',
  styleUrls: ['./stores.component.sass'],
})
export class StoresComponent {
  stores: Observable<any> ;
  constructor(private storesServices: StoresService) {
    this.stores = this.storesServices.getStores();
  }
  ngOnInit(): void {
    // this.stores = this.storesServices.getStores().subscribe();
  }
}
