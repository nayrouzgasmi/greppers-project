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
import { ReviewComponent } from './review/review.component';
import { HeaderComponent } from './ui/header/header.component';
import { CreateReviewComponent } from './create-review/create-review.component';
import { LoadingComponent } from './ui/loading/loading.component';
@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    SellerComponent,
    AjoutSellerComponent,
    ClientComponent,
    AjoutClientComponent,
    ReviewComponent,
    HeaderComponent,
    CreateReviewComponent,
    LoadingComponent

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
