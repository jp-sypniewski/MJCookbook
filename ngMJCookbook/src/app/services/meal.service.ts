import { Meal } from './../models/meal';
import { AuthService } from 'src/app/services/auth.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MealService {

  private baseUrl = 'http://localhost:8085/';

  constructor(private http: HttpClient,
    private authSvc: AuthService) { }

  getMealsByUser(){
    const credentials = this.authSvc.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.get<Meal[]>(this.baseUrl + 'api/meals', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('MealService.getMealsByUser(): error getting user meals.');
      })
    );
  }

  getMealById(id: Number){
    const credentials = this.authSvc.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.get<Meal>(this.baseUrl + 'api/meals/' + id, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('MealService.getMealById(): error getting meal by id.');
      })
    );
  }

  postMeal(meal: Meal){
    const credentials = this.authSvc.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.post<Meal>(this.baseUrl + 'api/meals', meal, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('MealService.postMeal(): error creating meal.');
      })
    );
  }

  putMeal(meal: Meal){
    const credentials = this.authSvc.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.put<Meal>(this.baseUrl + 'api/meals/' + meal.id, meal, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('MealService.putMeal(): error updating meal.');
      })
    );
  }
}
