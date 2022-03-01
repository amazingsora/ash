import { getLocaleDateFormat } from '@angular/common';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { P } from 'src/until/P';
import { Until } from 'src/until/util';

@Injectable({
  providedIn: 'root',
})
export class TestService {
  tData: any;
  list: P[] = [];
  until: Until;
  public httpOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Credentials': 'true',
      'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE, OPTIONS, PATCH',
      'Access-Control-Max-Age': '86400',
      responseType: 'text',
    }),
  };

  constructor(private http: HttpClient) {}
  onUpload(fileToUpload: File){
    const fd = new FormData();
    fd.append('file',fileToUpload,fileToUpload.name);
    this.http.post('http://localhost:8839/ASH/angularUp',fd).subscribe(res=>{
console.log(res);

    });
  }
  getdata() {
    this.http
      .get('http://localhost:8839/ASH/angularTest', {
        observe: 'response',
        responseType: 'text',
      })
      .subscribe((res) => {
        let response: HttpResponse<any> = res;
        this.tData = res.body;
        console.log('angularTest ===>' + res);

        console.log('angularTest ===>' + res.body);
      });
  }
  getdata2() {
    return '222222';
  }
  get1() {
    this.http.get<any>('http://localhost:8839/ASH/rf/i').subscribe((data) => {
      console.log('get1 ===>');
      console.log(data);
    });
  }

  get2() {
    this.http.get<any>('http://localhost:8839/ASH/rf/i').subscribe((data) => {
      console.log('get2 ===>');

      console.log(data.length);
      if (data.length > 0) {
        this.list = data;
        console.log(this.list[1].id);
      } else {
        this.list = [];
      }
    });
  }
  get3(keyword: string) {
    if (keyword) {
      keyword ="noData";
    }
      this.http
        .get<any>('http://localhost:8838/ASH/rf/' + keyword)
        .subscribe((data) => {
          console.log('get2 ===>');

          console.log(data.length);
          if (data.length > 0) {
            this.list = data;
            console.log(this.list[1].id);

          } else {
            this.list = [];

          }
        });

  }
}
