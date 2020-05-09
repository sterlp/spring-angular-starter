import { Injectable } from '@angular/core';
import { SpringResource, Page, SpringDataSource } from '@sterlp/ng-spring-boot-api';
import { Person } from '../model/person-model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonService extends SpringResource<Page<Person>, Person> {

    get listUrl() { return '/api/persons'; }

    constructor(http: HttpClient) {
        super(http);
    }

    delete(id?: number | string): Observable<void> {
        return this.http.delete<void>(`${this.listUrl}/${id}`);
    }
}

export class PersonDataSource extends SpringDataSource<Page<Person>, Person, PersonService> {
    extractDataFromList(list: Page<Person>): Person[] {
        return list.content;
    }
}
