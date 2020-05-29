import { User } from 'src/app/models/user';
import { Recipe } from 'src/app/models/recipe';
export class Meal {
  id: Number;
  rating: Number;
  completed: Boolean;
  enabled: Boolean;
  plannedFor: Date;
  recipe: Recipe;
  user: User;

  constructor(id?: Number, rating?: Number,
    completed?: Boolean, enabled?: Boolean,
    plannedFor?: Date, recipe?: Recipe,
    user?: User){
      this.id = id;
      this.rating = rating;
      this.completed = completed;
      this.enabled = enabled;
      this.plannedFor = plannedFor;
      this.recipe = recipe;
      this.user = user;
    }



}
