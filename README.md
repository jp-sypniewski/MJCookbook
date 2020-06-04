## A quick Spring/Angular project for cooking recipes

### Overview

Mary Blake and I have been cooking a lot more lately, and she thought I should make us a place to store recipes.

I've seen JIRA referenced in a lot of job postings, so I'll be implementing an agile workflow for this project with JIRA.

v0.1 Users can:  "cook" a recipe [have a *meal*], recipes now include *instructions* which include *ingredients*.

v0.0 Literally just recipes as written text.  The user logs in, can view the list of all recipes, and creates/updates recipes.  Viewing a single recipe shows the recipe text.

### Application Technologies

* Spring RESTful API, Spring Data JPA, MySQL
* Angular9
* Languages: Java, Typescript, Javascript, HTML, CSS, SQL

### Sprints

##### 1) Create project with recipes and users

* Going through setup of Spring (w/ JPA) and Angular projects was a great exercise, should walk through setup and document for test project
* Spring Security setup will need to be adjusted if non-users should be able to view data

##### 2) Create meal (user cooks recipe)

* Meal is setup as mostly a join table, will need to function later for how user interacts with meal
* Good experience in making a more complex html form, with Recipes as options under a select

##### 3) Make recipes more complicated (instructions, ingredients)

* Initial plan was to have instruction have PK of (recipe_id, sequence), nixed to go with normal PK (id)
* Ingredients and instructions are both deleted if user updates whole list, might want to look for a way to "save" old recipe instructions
* Allowing a simple UI to update the instructions/ingredients combo required the DragDrop module, which is an awesome option to have for later features

### Endpoints
| Path | Object | Details |
|--|--|--|
| GET `/authenticate` | Principal | Logs (pre-existing) user in |
| POST `/register` | User | Creates new user |
| PUT `/update` | User | Updates existing user|
| GET `api/recipes` | List\<Recipe> | Gets list of all recipes |
| POST `api/recipes` | Recipe | Creates recipe record |
| PUT `api/recipes/{id}` | Recipe | Updates recipe record |
| GET `api/meals` | List\<Meal> | Gets list of meals for current user |
| GET `api/meals/{id}` | Meal | Get meal by id |
| POST `api/meals` | Meal | Create meal record for current user |
| PUT `api/meals/{id}` | Meal | Updates meal record, verifying current user |
| POST `api/recipes/{id}/instructions` | Recipe | Creates new instructions and ingredients within recipe |
| PUT `api/recipes/{rid}/instructions/{iid}` | Instruction | Updates single instruction record |
| POST `api/recipes/{rid}/instructions/{iid}/ingredients`| Ingredient | Creates an ingredient and adds to an instruction |
| PUT `api/recipes/{rid}/ingredients/{iid}` | Ingredient | Updates single ingredient record |

### Ideas

* Add in a social aspect (favorite recipes, leaving notes/messages for others)
* Add in timers and alerts for instructions
* Add 'helpful advice' within recipes (see: stackoverflow)
* Meal planning (nutritionist user-type, pre-planned diets)
* Food purchasing export for meal/meal[]
