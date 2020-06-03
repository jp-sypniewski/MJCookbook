import { Recipe } from 'src/app/models/recipe';
import { Instruction } from './instruction';
export class Ingredient {
  id: Number;
  name: String;
  amount: String;
  substitute: String;
  inclusion: String;
  instruction: Instruction;
  recipe: Recipe;

  constructor(id?: Number, name?: String,
    amount?: String, substitute?: String,
    inclusion?: String, instruction?: Instruction,
    recipe?: Recipe){
      this.id = id;
      this.name = name;
      this.amount = amount;
      this.substitute = substitute;
      this.inclusion = inclusion;
      this.instruction = instruction;
      this.recipe = recipe;
    }
}
