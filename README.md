## A quick Spring/Angular project for cooking recipes

### Overview

Mary Blake and I have been cooking a lot more lately, and she thought I should make us a place to store recipes.

I've seen JIRA referenced in a lot of job postings, so I'll be implementing an agile workflow for this project with JIRA.

v0.0 Literally just recipes as written text.  The user logs in, can view the list of all recipes, and creates/updates recipes.  Viewing a single recipe shows the recipe text.

### Application Technologies

* Spring RESTful API, Spring Data JPA, MySQL
* Angular9
* Languages: Java, Typescript, Javascript, HTML, CSS, SQL

### Endpoints
| Path | Object | Details |
|--|--|--|
| GET `/authenticate` | Principal | Logs (pre-existing) user in |
| POST `/register` | User | Creates new user |
| PUT `/update` | User | Updates existing user|
| GET `api/recipes` | List<Recipe> | Gets list of all recipes |
| PUT `api/recipes/{id}` | Recipe | Updates recipe record |
| POST `api/recipes` | Recipe | Creates recipe record |
