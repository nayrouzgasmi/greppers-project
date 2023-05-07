import { WalletComponent } from './event/wallet/wallet.component';
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
import { AddEventComponent } from './event/add-event/add-event.component';
import { EditEventComponent } from './event/edit-event/edit-event.component';
import { EventComponent } from './event/event.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { AdminGuardService } from './login/admin-guard.service';
import { ForbidenComponent } from './login/forbiden/forbiden.component';

const routes: Routes = [
  {path:"menu", component: MenuComponent , canActivate: [AdminGuardService]},
  {path:"", component: LoginComponent },
  {path:"seller", component: SellerComponent, canActivate: [AdminGuardService]},
  {path:"client", component: ClientComponent, canActivate: [AdminGuardService]},
  {path:"edit-product/:id", component: EditProductComponent, canActivate: [AdminGuardService]},
  {path:"add-product/:id", component: AddProductComponent, canActivate: [AdminGuardService]},
  {path:"stores", component: StoresComponent, canActivate: [AdminGuardService]},
  {path:"store/:id", component: StoreComponent, canActivate: [AdminGuardService]},
  {path:"add-store/:id", component: AddStoreComponent, canActivate: [AdminGuardService]},
  {path:"add_seller", component: AjoutSellerComponent, canActivate: [AdminGuardService]},
  {path:"add_client", component: AjoutClientComponent, canActivate: [AdminGuardService]},
  {path:"user-details/:id", component: UserDetailsComponent, canActivate: [AdminGuardService]},
  {path:"forbiden", component: ForbidenComponent},
  {path:"events", component: EventComponent, canActivate: [AdminGuardService]},
  {path:"addEvent", component: AddEventComponent, canActivate: [AdminGuardService]},
  {path:"editEvent/:id", component: EditEventComponent, canActivate: [AdminGuardService]},
  {path:"wallet", component: WalletComponent, canActivate: [AdminGuardService]}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
