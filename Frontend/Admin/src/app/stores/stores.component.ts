import { Component } from '@angular/core';
import { StoresService } from './stores.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-stores',
  templateUrl: './stores.component.html',
  styleUrls: ['./stores.component.css'],
})
export class StoresComponent {
  stores: Observable<any> | null = null;
  constructor(private storesServices: StoresService) {}
  ngOnInit(): void {
    // this.stores = this.storesServices.getStores().subscribe();
    this.stores = this.storesServices.getStores();
  }
  deleteStore(id: number) {
    this.storesServices.deleteStore(id);
    this.stores = this.storesServices.getStores();
    window.location.reload();

  }
}
