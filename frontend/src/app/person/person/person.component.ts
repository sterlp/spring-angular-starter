import { Component, OnInit, OnDestroy, Input, AfterViewInit, ViewChild, ElementRef, Renderer2 } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from "@angular/router";
import { Person } from '../model/person';
import { PersonService } from '../services/person.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit, OnDestroy, AfterViewInit {
  @Input() private id: string;
  @Input() person: Person;
  // as we have *ngIf used, the view are maybe not in the DOM if ngAfterViewInit is executed
  @ViewChild('personName') set elName(element: ElementRef) {
    if (element) {
      element.nativeElement.focus();
    }
  };
  private paramSub;
  constructor(private $person: PersonService, private $route: ActivatedRoute, private $router: Router, private $renderer: Renderer2) { }

  ngOnInit(): void {
    // we could use this.$route.snapshot.paramMap.get('id');
    // but then we will not get an update if we use the TOP nav if we are on this page right now
    this.paramSub = this.$route.paramMap.subscribe(params => {
      this.id = params.get('id');
      if ("newPerson" == this.id) {
        this.person = new Person();
      } else {
        // get the data from the state object, check id first
        if (window.history.state.id == this.id) {
          this.person = window.history.state;
        } else {
          // lazy loading just in case we don't have the data
          this.$person.get(this.id).subscribe(p => this.person = p);
        }
      }
    });
    
  }
  cmdSave() {
    this.$person.save(this.person).subscribe(result => this.$router.navigate(["/persons"]));
  }
  ngOnDestroy(): void {
    if (this.paramSub) this.paramSub.unsubscribe();
  }
  ngAfterViewInit(): void {
    // moved as we use ngIf
    // this.$renderer.invokeElementMethod(this.elName.nativeElement, 'focus');
  }
}
