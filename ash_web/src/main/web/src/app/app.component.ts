import { Observable } from 'rxjs';
import { TestService } from './test.service';
import { Component, OnInit } from '@angular/core';
import { P } from 'src/until/P';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'web';
  data: string = '字串寫死';
  data2: any;
  ps: P;
  constructor(private test: TestService) {}
  get aData() {
    return this.test.tData;
  }

  get list():P[]{
    let list :P[]=[];
    list = this.test.list
    return list;
  }
  ngOnInit(): void {
    console.log('ngOnInit');
    this.test.getdata();
    this.test.get2();
  }
  search($event){
    console.log($event);
  }
}
