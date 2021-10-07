import { Component, OnInit, ViewChild, AfterViewInit, Query } from '@angular/core';
import { PersonModel, Person } from '../../model/person-model';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { PersonService, PersonDataSource } from '../../service/person.service';
import { timeout } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-persons-list',
  templateUrl: './persons-list.component.html',
  styleUrls: ['./persons-list.component.scss']
})
// tslint:disable: curly
export class PersonsListComponent implements OnInit, AfterViewInit {

    @ViewChild(MatPaginator, { static: true })
    paginator!: MatPaginator;
    @ViewChild(MatSort, { static: true })
    sort!: MatSort;

    readonly columns = PersonModel.COLUMNS;
    readonly displayedColumns = this.columns.map(c => c.id);

    dataSource!: PersonDataSource;

    constructor(private personService: PersonService, private snackBar: MatSnackBar) {}

    ngOnInit(): void {
        this.displayedColumns.push('actions');
        this.dataSource = new PersonDataSource(this.personService);
        this.paginator.page.subscribe(this.dataSource.doPage.bind(this.dataSource));
        this.sort.sortChange.subscribe(this.dataSource.doSortBy.bind(this.dataSource));
        this.dataSource.hateosSubject$.subscribe(v => {
            if (v && v.totalElements) this.paginator.length = v.totalElements;
            else this.paginator.length = 0;
        });
    }
    ngAfterViewInit(): void {
        // avoid Expression has changed after it was checked. Previous value: 'undefined'.
        setTimeout(this.doLoad.bind(this), 0);
    }
    doLoad() {
        this.dataSource.doLoad(this.paginator.pageIndex, this.paginator.pageSize);
    }
    doDelete(p: Person) {
        this.personService.delete(p.id).subscribe(r => {
            this.snackBar.open(`Person '${p.name}' successfully deleted.`, undefined, {
                duration: 3000,
            });
            this.doLoad();
        });
    }
}
