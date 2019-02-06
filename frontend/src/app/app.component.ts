import {Component, OnInit} from '@angular/core';
import {ExampleService} from './example.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'frontend :-)';
  items = ['Item 1 before server call', 'Item 2 before server call'];

  constructor(private example: ExampleService) {}

  ngOnInit() {
    this.example.get().subscribe((v: string[]) => {
      console.info(v);
      this.items = v; 
    });
  }
}
