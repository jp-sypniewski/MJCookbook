import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  [x: string]: any;

  private baseUrl = 'http://localhost:8085/';

  constructor(private http: HttpClient) { }


  // log user in, creates btoa creds and checks against authenticate path
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

  // gets user info from principal
  getUserInfo(){
    const credentials = this.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.get<User>(this.baseUrl + 'user', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AuthService.getUserInfo(): error getting user info.');
      })
    );
  }

  // creates user, references register path
  createUser(user){
    return this.http.post<User>(this.baseUrl+'register', user)
   .pipe(
     catchError((err: any) => {
       console.log(err);
       return throwError('AuthService.createUser(): error registering user.');
     })
   );
  }

  updateUser(user){
    const credentials = this.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.put<User>(this.baseUrl+'update', user, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AuthService.updateUser(): error updating user.');
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
