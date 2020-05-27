import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8085/';

  constructor(private http: HttpClient) { }

  login(username, password) {
    const credentials = this.generateBasicAuthCredentials(username, password);
    const httpOptions = {
      headers: new HttpHeaders({
         'Authorization': `Basic ${credentials}`,
         'X-Requested-With': 'XMLHttpRequest'
       })
    };
   return this.http.get(this.baseUrl + 'authenticate', httpOptions)
   .pipe(
     tap((res) => {
       localStorage.setItem('credentials' , credentials);
       return res;
     }),
     catchError((err: any) => {
       console.log(err);
       return throwError('UserService.login(): Error logging in.');
     })
   );
  }

  // removes local creds
  logout() {
    localStorage.removeItem('credentials');
  }

  // checks for local creds
  checkLogin() {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

  // generates creds (Basic)
  generateBasicAuthCredentials(username, password) {
    return btoa(`${username}:${password}`);
  }

  // returns creds to be used in other api calls
  getCredentials() {
    return localStorage.getItem('credentials');
  }
}
