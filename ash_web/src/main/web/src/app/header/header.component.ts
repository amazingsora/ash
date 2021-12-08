import { Component, OnInit } from '@angular/core';
import { TestService } from '../test.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  Keyword :string;
  constructor(private test: TestService) {

   }

  ngOnInit(): void {
  }
  search($event){
    console.log("Keyword ===>"+this.Keyword);
    this.test.get3(this.Keyword);




  }
}
