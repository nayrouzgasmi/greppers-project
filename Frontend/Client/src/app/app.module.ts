import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HeroComponent } from './home/hero/hero.component';
import { CategoriesComponent } from './home/categories/categories.component';
import { EventsComponent } from './events/events.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { HttpInterceptorService } from './security/http-interceptor.service';
import { FacebookLoginProvider, GoogleLoginProvider, SocialAuthServiceConfig, SocialLoginModule } from 'angularx-social-login'
import { CookieService } from 'ngx-cookie-service';
import { CodeActivationComponent } from './code-activation/code-activation.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ModalComponent } from './shared/modal/modal.component';
import { CarouselModule } from 'primeng/carousel';
import { ProductsComponent } from './products/products.component';
import { StoresComponent } from './stores/stores.component';
import { StoreComponent } from './store/store.component';
import { MerchantDashboardComponent } from './merchant-dashboard/merchant-dashboard.component';
import { MerchantGuideComponent } from './merchant-guide/merchant-guide.component';
import { AboutComponent } from './about/about.component';
import { ProductComponent } from './product/product.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { EventDetailsComponent } from './events/event-details/event-details.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    FooterComponent,
    HeroComponent,
    CategoriesComponent,
    EventsComponent,
    LoginComponent,
    RegisterComponent,
    CodeActivationComponent,
    ResetPasswordComponent,
    ModalComponent,
    ProductsComponent,
    StoresComponent,
    StoreComponent,
    MerchantDashboardComponent,
    MerchantGuideComponent,
    AboutComponent,
    ProductComponent,
    UserDetailsComponent,
    EventDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    CarouselModule,
    SocialLoginModule  
  ],
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              '998943596311-agii5b72rppsj1h1tdp5f75mhnfj22s7.apps.googleusercontent.com'
            )
          },
          {
            id: FacebookLoginProvider.PROVIDER_ID,
            provider: new FacebookLoginProvider('557258556600595')
          }
        ]
      } as SocialAuthServiceConfig,
    },
    {provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true},
    CookieService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
