import { RecipeService } from './../../services/recipe.service';
import { User } from 'src/app/models/user';
import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/models/recipe';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  title: string = "Recipe List";
  recipes: Recipe[] = [];
  singleView: boolean = false;
  selectedRecipe: Recipe = new Recipe();
  showForm: boolean = false;





  constructor(private authSvc: AuthService,
    private recipeSvc: RecipeService) { }

  ngOnInit(): void {
  }

  reload(){
    if (this.authSvc.checkLogin()){
      this.recipeSvc.getAllRecipes().subscribe(
        data => {
          this.recipes = data;
        },
        err => {
          console.error('RecipeListComponent.reload(): issue collecting recipes list')
        }
      );
    }
  }

  selectARecipe(recipe: Recipe){
    this.selectedRecipe = recipe;
    this.singleView = true;
  }

  editRecipe(){
    this.showForm = true;
  }

  createNewRecipe(){
    this.selectedRecipe = new Recipe();
    this.showForm = true;
  }

  submit(){
    if (this.selectedRecipe.id != null){
      // if it has an id, then submit the put
    } else {
      // otherwise submit the post
    }
    // reassign it to the selected one to show the new one
    this.showForm = false;
  }

}
