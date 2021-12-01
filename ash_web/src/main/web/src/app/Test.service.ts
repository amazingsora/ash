import { getLocaleDateFormat } from '@angular/common';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Until } from 'src/until/util';
import { P } from 'src/until/P';

@Injectable({
  providedIn: 'root'
})
export class TestService {
  tData:any ;
  p:any ;
  until:Until;
  public httpOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Credentials': 'true',
      'Access-Control-Allow-Methods': 'GET, PUT, POST, DELETE, OPTIONS, PATCH',
      'Access-Control-Max-Age': '86400',
       'responseType': 'text'
    })
  };

  constructor(private http : HttpClient) {



   }

  getdata(){
     this.http.get('http://localhost:8838/ASH/angularTest',{
      observe: 'response' ,
      responseType: 'text'
    }).subscribe(res => {
      let response: HttpResponse<any> = res;
    this.tData = res.body;
    console.log("angularTest ===>"+res)

      console.log("angularTest ===>"+res.body)
    });
  }
  getdata2(){
    return  '222222'

  }
  getp():Observable<any[]>{
    return this.http.get('http://localhost:8838/ASH/rf/i').pipe(map((res:any)=>res
    ));

  }


}



