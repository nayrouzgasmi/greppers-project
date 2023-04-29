import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { EventsComponent } from './events/events.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { CodeActivationComponent } from './code-activation/code-activation.component';
import { ProductsComponent } from './products/products.component';
import { MerchantDashboardComponent } from './merchant-dashboard/merchant-dashboard.component';
import { StoresComponent } from './stores/stores.component';
import { StoreComponent } from './store/store.component';
import { AboutComponent } from './about/about.component';
import { MerchantGuideComponent } from './merchant-guide/merchant-guide.component';
import { ProductComponent } from './product/product.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  { path: 'about', component: AboutComponent },
  { path: 'events', component: EventsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {path: 'reset', component:ResetPasswordComponent},
  {path: 'active', component:CodeActivationComponent},
  { path: 'products', component: ProductsComponent },
  { path: 'product/:id', component: ProductComponent },
  // { path: 'filter', component: ProductsFilterComponent },
  { path: 'merchant-dashboard', component: MerchantDashboardComponent },
  { path: 'merchant-guide', component: MerchantGuideComponent },
  { path: 'vendor-stores', component: StoresComponent },
  { path: 'stores', component: StoresComponent },
  { path: 'store/:id', component: StoreComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
