import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { SellerComponent } from './seller/seller.component';
import { FormsModule } from '@angular/forms';
import { AjoutSellerComponent } from './ajout-seller/ajout-seller.component';
import { ClientComponent } from './client/client.component';
import { AjoutClientComponent } from './client/ajout-client/ajout-client.component';
import { AddProductComponent } from './add-product/add-product.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { StoresComponent } from './stores/stores.component';
import { StoreComponent } from './store/store.component';
import { EditProductComponent } from './edit-product/edit-product.component';
import { AddStoreComponent } from './add-store/add-store.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { EditStoreComponent } from './edit-store/edit-store.component';
import { FooterComponent } from './footer/footer.component';
import { CarcirogenicsComponent } from './carcirogenics/carcirogenics.component';
import { CreateCarcirogenicsComponent } from './create-carcirogenics/create-carcirogenics.component';
import { ToxicProductComponent } from './toxic-product/toxic-product.component';
import { CategoriesComponent } from './categories/categories.component';
import { CategoriesProductsComponent } from './categories-products/categories-products.component';

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    SellerComponent,
    AjoutSellerComponent,
    ClientComponent,
    AjoutClientComponent,
    AddProductComponent,
    SidebarComponent,
    StoresComponent,
    StoreComponent,
    EditProductComponent,
    AddStoreComponent,
    UserDetailsComponent,
    EditStoreComponent,
    FooterComponent,
    CarcirogenicsComponent,
    CreateCarcirogenicsComponent,
    ToxicProductComponent,
    CategoriesComponent,
    CategoriesProductsComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
