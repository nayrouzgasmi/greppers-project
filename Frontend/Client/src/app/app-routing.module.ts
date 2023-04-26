import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { EventsComponent } from './events/events.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { CodeActivationComponent } from './code-activation/code-activation.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  { path: 'events', component: EventsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {path: 'reset', component:ResetPasswordComponent},
  {path: 'active', component:CodeActivationComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
