import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SellerComponent } from './seller/seller.component';
import { MenuComponent } from './menu/menu.component';
import { AjoutSellerComponent } from './ajout-seller/ajout-seller.component';
import { ClientComponent } from './client/client.component';
import { AjoutClientComponent } from './client/ajout-client/ajout-client.component';
import { AddProductComponent } from './add-product/add-product.component';
import { StoresComponent } from './stores/stores.component';
import { StoreComponent } from './store/store.component';
import { EditProductComponent } from './edit-product/edit-product.component';

const routes: Routes = [
  {path:"", component: MenuComponent},

  {path:"seller", component: SellerComponent},
  {path:"client", component: ClientComponent},
  {path:"edit-product/:id", component: EditProductComponent},
  {path:"add-product/:id", component: AddProductComponent},
  {path:"stores", component: StoresComponent},
  {path:"store/:id", component: StoreComponent},
  {path:"add_seller", component: AjoutSellerComponent},
  {path:"add_client", component: AjoutClientComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
