import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SellerComponent } from './seller/seller.component';
import { MenuComponent } from './menu/menu.component';
import { AjoutSellerComponent } from './ajout-seller/ajout-seller.component';
import { ClientComponent } from './client/client.component';
import { AjoutClientComponent } from './client/ajout-client/ajout-client.component';
import { ReviewComponent } from './review/review.component';
import { HeaderComponent } from './ui/header/header.component';
import { CreateReviewComponent } from './create-review/create-review.component';
import { UpdateReviewComponent } from './update-review/update-review.component';

const routes: Routes = [
  { path: "", component: MenuComponent },

  { path: "seller", component: SellerComponent },

  {
    path: "review", component: HeaderComponent, children: [
      { path: '', redirectTo: 'listReviews', pathMatch: 'full' },
      { path: 'listReviews', component: ReviewComponent },
      { path: 'create', component: CreateReviewComponent },
      { path: 'update/:id', component: UpdateReviewComponent }

    ]
  },

  { path: "client", component: ClientComponent },

  { path: "add_seller", component: AjoutSellerComponent },
  { path: "add_client", component: AjoutClientComponent }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
