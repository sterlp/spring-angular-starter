import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, from } from 'rxjs';
import { Person } from '../model/person';

// optional just added as example
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Accept': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  constructor(private http: HttpClient) { }

  list() {
    return this.http.get('api/persons');
  }
  get(id: string): Observable<Person> {
    return this.http.get(`api/persons/${id}`);
  }
  save(person: Person): Observable<Person> {
    if (person.id) {
      const id = person.id;
      return this.http.put(`api/persons/${id}`, person);
    } else {
      return this.http.post(`api/persons`, person);
    }
  }
  delete(person: any) {
    let id: any;
    if (person.id) id = person.id;
    else id = person;

    return this.http.delete("api/persons/" + id, httpOptions);
  }
}