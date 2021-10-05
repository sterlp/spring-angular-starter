import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PersonComponent } from './person/page/person/person.component';
import { PersonsListComponent } from './person/page/persons-list/persons-list.component';


const routes: Routes = [
  {path: 'persons', component: PersonsListComponent},
  {path: 'persons/:id', component: PersonComponent},
  {path: '', redirectTo: '/persons', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
