import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SellerComponent } from './seller/seller.component';
import { MenuComponent } from './menu/menu.component';
import { AjoutSellerComponent } from './ajout-seller/ajout-seller.component';
import { ClientComponent } from './client/client.component';
import { AjoutClientComponent } from './client/ajout-client/ajout-client.component';
import { TicketListComponent } from './ticket-list/ticket-list.component';
import { ReponseListComponent } from './reponse-list/reponse-list.component';
import { CreateTicketComponent } from './create-ticket/create-ticket.component';
import { UpdateTicketComponent } from './update-ticket/update-ticket.component';
import { CreateReponseComponent } from './create-reponse/create-reponse.component';
import { UpdateReponseComponent } from './update-reponse/update-reponse.component';
import { StatistiqueComponent } from './statistique/statistique.component';

const routes: Routes = [
  {path:"", component: MenuComponent},
  {path:"seller", component: SellerComponent},
  {path:"client", component: ClientComponent},
  {path:"add_seller", component: AjoutSellerComponent},
  {path:"add_client", component: AjoutClientComponent},
  {path:'tickets', component: TicketListComponent},
  {path:'reponses', component: ReponseListComponent},
  {path:'create-ticket',component: CreateTicketComponent},
  {path:'update-ticket/:id',component: UpdateTicketComponent},
  {path:'create-reponse/:id',component: CreateReponseComponent},
  {path:'update-reponse/:id',component: UpdateReponseComponent},
  {path:'stat',component: StatistiqueComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
