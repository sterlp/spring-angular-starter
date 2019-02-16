import { Component, OnInit } from '@angular/core';
import { Person } from '../model/person';
import { PersonService } from '../services/person.service'

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit {

  persons: Person[] = [];
  constructor(private $person: PersonService) { }

  ngOnInit() {
    this.$person.list().subscribe((persons: Person[]) => {
      this.persons = persons; 
    });
  }
  cmdDelete(id: number) {
    // NOTE: for some reason no call is done by HTTP without subscription ...
    this.$person.delete(id).subscribe(result => console.info("person deleted ...", result));
    this.persons = this.persons.filter(p => p.id != id);
  }
}
