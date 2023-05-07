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
import { AddStoreComponent } from './add-store/add-store.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { EditStoreComponent } from './edit-store/edit-store.component';
import { CarcirogenicsComponent } from './carcirogenics/carcirogenics.component';
import { CreateCarcirogenicsComponent } from './create-carcirogenics/create-carcirogenics.component';
import { ToxicProductComponent } from './toxic-product/toxic-product.component';

const routes: Routes = [
  {path:"", component: MenuComponent},

  {path:"seller", component: SellerComponent},
  {path:"client", component: ClientComponent},
  {path:"edit-product/:storeId/:id", component: EditProductComponent},
  {path:"add-product/:id", component: AddProductComponent},
  {path:"stores", component: StoresComponent},
  {path:"store/:id", component: StoreComponent},
  {path:"add-store/:id", component: AddStoreComponent},
  {path:"edit-store/:id", component: EditStoreComponent},
  {path:"add_seller", component: AjoutSellerComponent},
  {path:"add_client", component: AjoutClientComponent},
  {path:"user-details/:id", component: UserDetailsComponent},
  {path:"carcirogenics", component: CarcirogenicsComponent},
  {path:"create-carcirogenics", component: CreateCarcirogenicsComponent},
  {path:"toxic", component: ToxicProductComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
