import { Ingredient } from './../models/ingredient';
import { Instruction } from './../models/instruction';
import { AuthService } from './auth.service';
import { Recipe } from 'src/app/models/recipe';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private baseUrl = 'http://localhost:8085/';

  constructor(private http: HttpClient,
    private authSvc: AuthService) { }

  getAllRecipes(){
    const credentials = this.authSvc.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.get<Recipe[]>(this.baseUrl + 'api/recipes', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('RecipeService.getAllRecipes(): error getting recipes.');
      })
    );
  }

  createRecipe(recipe: Recipe){
    const credentials = this.authSvc.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.post<Recipe>(this.baseUrl + 'api/recipes', recipe, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('RecipeService.createRecipe(): error creating recipe.');
      })
    );
  }

  updateRecipe(recipe: Recipe){
    const credentials = this.authSvc.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.put<Recipe>(this.baseUrl + 'api/recipes/' + recipe.id, recipe, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('RecipeService.updateRecipe(): error updating recipe.');
      })
    );
  }

  disableRecipe(recipe: Recipe){
    recipe.enabled = false;
    const credentials = this.authSvc.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.put<Recipe>(this.baseUrl + 'api/recipes/' + recipe.id, recipe, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('RecipeService.disableRecipe(): error updating recipe.');
      })
    );
  }

  enableRecipe(recipe: Recipe){
    recipe.enabled = true;
    const credentials = this.authSvc.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.put<Recipe>(this.baseUrl + 'api/recipes/' + recipe.id, recipe, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('RecipeService.disableRecipe(): error updating recipe.');
      })
    );
  }

  updateInstructionsAndIngredients(recipe: Recipe){
    const credentials = this.authSvc.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.post<Recipe>(this.baseUrl + 'api/recipes/' +
      recipe.id + '/instructions', recipe.instructions, httpOptions)
      .pipe(
        catchError((err:any) => {
          console.log(err);
          return throwError('RecipeService.updateInstructionsAndIngredients(): error with inst update');
        }
      )
    );
  }

  updateSingleInstruction(recipeId: Number, instruction: Instruction){
    const credentials = this.authSvc.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.put<Instruction>(this.baseUrl + 'api/recipes/' +
      recipeId + '/instructions/' + instruction.id, instruction, httpOptions)
      .pipe(
        catchError((err:any) => {
          console.log(err);
          return throwError('RecipeService.updateSingleInstruction(): error with single inst update');
        }
      )
    );
  }

  addIngredient(recipeId: Number, instructionId: Number, ingredient: Ingredient){
    const credentials = this.authSvc.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.post<Ingredient>(this.baseUrl + 'api/recipes/' +
      recipeId + '/instructions/' + instructionId + '/ingredients',
      ingredient, httpOptions)
      .pipe(
        catchError((err:any) => {
          console.log(err);
          return throwError('RecipeService.addIngredient(): error adding new ingredient');
        }
      )
    );
  }

  updateSingleIngredient(recipeId: Number, ingredient: Ingredient){
    const credentials = this.authSvc.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${credentials}`
      })
    };
    return this.http.put<Ingredient>(this.baseUrl + 'api/recipes/' +
      recipeId + '/ingredients/' + ingredient.id,
      ingredient, httpOptions)
      .pipe(
        catchError((err:any) => {
          console.log(err);
          return throwError('RecipeService.updateSingleIngredient(): error adding new ingredient');
        }
      )
    );
  }


}
