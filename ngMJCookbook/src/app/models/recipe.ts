import { User } from './user';
export class Recipe {
  id: number;
  title: string;
  recipeText: string;
  user: User;

  constructor(id?: number, title?: string, recipeText?: string, user?: User){
    this.id = id;
    this.title = title;
    this.recipeText = recipeText;
    this.user = user;
  }
}
