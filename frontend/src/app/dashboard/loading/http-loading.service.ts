import { Injectable, OnDestroy } from '@angular/core';
import { HttpInterceptor } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpLoadingService implements OnDestroy {
    private loadingSubject = new BehaviorSubject<boolean>(false);
    public loading$: Observable<boolean> = this.loadingSubject.asObservable();

    private requestCount = 0;
    constructor() {}

    queue() {
        ++this.requestCount;
        if (this.requestCount > 0 && !this.loadingSubject.value) {
            this.loadingSubject.next(true);
        }
    }
    finished() {
        --this.requestCount;
        if (this.requestCount <= 0) {
            this.requestCount = 0;
            if (this.loadingSubject.value) {
                this.loadingSubject.next(false);
            }
        }
    }
    ngOnDestroy(): void {
        this.loadingSubject.complete();
    }
}
