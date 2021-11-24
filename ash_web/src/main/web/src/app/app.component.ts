import { TestService } from './test.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'web';
  data :string = '字串寫死';
  data2 :any;

  constructor(private test:TestService){

  }
   get aData(){
     return this.test.tData;
  }
  ngOnInit(): void {
    this.test.getdata();
    this.data2 = this.test.getdata2();
    this.data2 = this.aData();
  }

}
