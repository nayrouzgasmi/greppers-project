import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StoreService } from './store.service';

@Component({
  selector: 'app-store',
  templateUrl: './store.component.html',
  styleUrls: ['./store.component.sass'],
})
export class StoreComponent {
  storeData: any;
  store: any;
  constructor(
    private route: ActivatedRoute,
    private storeService: StoreService,
  ) {}
  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.storeService.getStore(id).subscribe((data) => (this.store=data));
  }
}
