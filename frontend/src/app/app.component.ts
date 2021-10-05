import { Component } from '@angular/core';
import { HttpLoadingService } from './dashboard/loading/http-loading.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  loading$: Observable<boolean>;
  constructor(loadingService: HttpLoadingService) {
      this.loading$ = loadingService.loading$;
  }
}
