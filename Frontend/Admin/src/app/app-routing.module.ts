import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SellerComponent } from './seller/seller.component';
import { MenuComponent } from './menu/menu.component';
import { AjoutSellerComponent } from './ajout-seller/ajout-seller.component';
import { ClientComponent } from './client/client.component';
import { AjoutClientComponent } from './client/ajout-client/ajout-client.component';

const routes: Routes = [
  {path:"", component: MenuComponent},

  {path:"seller", component: SellerComponent},
  {path:"client", component: ClientComponent},

  {path:"add_seller", component: AjoutSellerComponent},
  {path:"add_client", component: AjoutClientComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
