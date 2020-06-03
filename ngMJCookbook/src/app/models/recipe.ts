import { Ingredient } from './ingredient';
import { Instruction } from './instruction';
import { User } from './user';
export class Recipe {
  id: number;
  title: string;
  recipeText: string;
  user: User;
  enabled: Boolean;
  status: String;
  instructions: Instruction[];
  ingredients: Ingredient[];

  constructor(id?: number, title?: string, recipeText?: string, user?: User,
    enabled?: Boolean, status?: String,
    instructions?: Instruction[], ingredients?: Ingredient[]){
    this.id = id;
    this.title = title;
    this.recipeText = recipeText;
    this.user = user;
    this.enabled = enabled;
    this.status = status;
    this.instructions = instructions;
    this.ingredients = ingredients;
  }
}
