export class Until {
    observe= 'response'
    responseType= 'json'
    Until(type:string){
      this.responseType = type;
    }
}
