import { Recipe } from 'src/app/models/recipe';
import { Ingredient } from './ingredient';
export class Instruction {
  id: Number;
  sequence: Number;
  text: String;
  ingredients: Ingredient[];
  recipe: Recipe;

  constructor(id?: Number, sequence?: Number, text?: String,
    ingredients?: Ingredient[], recipe?: Recipe){
    this.id = id;
    this.sequence = sequence;
    this.text = text;
    this.ingredients = ingredients;
    this.recipe = recipe;
  }


}
