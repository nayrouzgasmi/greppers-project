import { WalletComponent } from './event/wallet/wallet.component';
import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { SellerComponent } from './seller/seller.component';
import { AjoutSellerComponent } from './ajout-seller/ajout-seller.component';
import { ClientComponent } from './client/client.component';
import { AjoutClientComponent } from './client/ajout-client/ajout-client.component';
import { ReviewComponent } from './review/review.component';
import { HeaderComponent } from './ui/header/header.component';
import { CreateReviewComponent } from './create-review/create-review.component';
import { LoadingComponent } from './ui/loading/loading.component';
import { UpdateReviewComponent } from './update-review/update-review.component';
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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EventComponent } from './event/event.component';
import { AddEventComponent } from './event/add-event/add-event.component';
import { EditEventComponent } from './event/edit-event/edit-event.component';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { HttpInterceptorService } from './login/http-interceptor.service';
import { ForbidenComponent } from './login/forbiden/forbiden.component';
import { TicketListComponent } from './ticket-list/ticket-list.component';
import { CreateTicketComponent } from './create-ticket/create-ticket.component';
import { UpdateTicketComponent } from './update-ticket/update-ticket.component';
import { ReponseListComponent } from './reponse-list/reponse-list.component';
import { CreateReponseComponent } from './create-reponse/create-reponse.component';
import { UpdateReponseComponent } from './update-reponse/update-reponse.component';
import { NgxEchartsModule } from 'ngx-echarts';
import { StatistiqueComponent } from './statistique/statistique.component';
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
    LoadingComponent,
    UpdateReviewComponent,

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
    CategoriesProductsComponent,
    EventComponent,
    AddEventComponent,
    EditEventComponent,
    WalletComponent,
    DashboardComponent,
    LoginComponent,
    ForbidenComponent,
    TicketListComponent,
    CreateTicketComponent,
    UpdateTicketComponent,
    ReponseListComponent,
    CreateReponseComponent,
    UpdateReponseComponent,
    StatistiqueComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    CommonModule,
    NgxEchartsModule.forRoot({
      echarts: () => import('echarts')
    }),
  ],
  providers: [  {provide: HTTP_INTERCEPTORS,
    useClass: HttpInterceptorService,
    multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
