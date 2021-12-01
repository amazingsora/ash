import { Observable } from 'rxjs';
import { TestService } from './test.service';
import { Component, OnInit } from '@angular/core';
import { P } from 'src/until/P';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'web';
  data :string = '字串寫死';
  data2 :any;
  ps:any[] ;
  constructor(private test:TestService){

  }
   get aData(){
     return this.test.tData;
  }
  get p():any{
    return this.test.p;
 }
  ngOnInit(): void {
    console.log("ngOnInit");
    this.test.getdata();
    this.test.getp().subscribe(pp=>this.ps = pp
      console.log(pp));
    this.data2 = this.test.getdata2();

  }

}
