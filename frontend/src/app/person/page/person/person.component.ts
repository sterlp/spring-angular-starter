import { Component, OnInit, OnDestroy } from '@angular/core';
import { PersonService } from '../../service/person.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SubscriptionsHolder } from '@sterlp/ng-spring-boot-api';
import { Person, PersonModel } from '../../model/person-model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.scss']
})
export class PersonComponent implements OnInit, OnDestroy {
    readonly BACK_URL = ['/persons'];
    private subs = new SubscriptionsHolder();

    constructor(private router: Router,
                private route: ActivatedRoute,
                private personService: PersonService) {

        this.subs.add(this.route.params.subscribe(params => {
            const id = params.id * 1;
            if (id) {
                this.personService.get(id).subscribe(d => this.person = d);
            } else {
                this.person = {};
            }
        }));
    }

    person?: Person;
    error: any;
    getLabel = PersonModel.getLabel;

    get isNew() {
        return this.person && typeof this.person.version !== 'number';
    }

    ngOnInit(): void {

    }

    ngOnDestroy(): void {
        this.subs.close();
    }

    doSave() {
        this.error = null;
        let saveObs: Observable<Person>;
        if (this.isNew) {
            saveObs = this.personService.save(this.person!);
        } else {
            saveObs = this.personService.update(this.person!, this.person!.id!);
        }

        saveObs.subscribe(
            r => this.router.navigate(this.BACK_URL),
            e => this.error = e.error || e
        );
    }
}
