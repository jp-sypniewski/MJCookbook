import { Recipe } from 'src/app/models/recipe';
import { Ingredient } from './ingredient';
export class Instruction {
  id: Number;
  order: Number;
  text: String;
  ingredients: Ingredient[];
  recipe: Recipe;

  constructor(id?: Number, order?: Number, text?: String,
    ingredients?: Ingredient[], recipe?: Recipe){
    this.id = id;
    this.order = order;
    this.text = text;
    this.ingredients = ingredients;
    this.recipe = recipe;
  }


}
