import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './dashboard/sidebar/sidebar.component';
import { ToggleDirective } from './dashboard/sidebar/toggle.directive';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule, MatRippleModule } from '@angular/material/core';
import { MatRadioModule } from '@angular/material/radio';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { MatTooltipModule } from '@angular/material/tooltip';
import { PersonsListComponent } from './person/page/persons-list/persons-list.component';
import { PersonComponent } from './person/page/person/person.component';
import { FormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { HttpLoadingService } from './dashboard/loading/http-loading.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpLoggingInterceptor } from './dashboard/loading/http-loading-interceptor';



@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    ToggleDirective,
    PersonsListComponent,
    PersonComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRadioModule,
    MatTooltipModule,
    MatRippleModule,
    MatSnackBarModule,
    MatProgressBarModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpLoggingInterceptor, multi: true },
    { provide: LocationStrategy, useClass: HashLocationStrategy }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
