import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { HttpLoadingService } from './http-loading.service';
/**
 * https://angular.io/guide/http#using-interceptors-for-logging
 *
 * Requires change to the app.module
 * ```
 *   providers: [
 *      { provide: HTTP_INTERCEPTORS, useClass: HttpLoadingService, multi: true },
 *   ],
 * ```
 * and
 * ```
 * "experimentalDecorators": true
 * ```
 * in the tsconfig.*.json compilerOptions
 */
@Injectable()
export class HttpLoggingInterceptor implements HttpInterceptor {
    constructor(private loadingService: HttpLoadingService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler) {
        this.loadingService.queue();
        return next.handle(req).pipe(finalize(() => this.loadingService.finished()));
    }
}
