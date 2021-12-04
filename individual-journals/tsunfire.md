# Tsunfire Journal

## Week 2

**Snapshot**

**Accomplishments**

- **[1.0.5]** I converted the entire API to a RESTful API (Cart, Items, Users) with exception handling, model assembly, link creation, responseentity returns. Also modified code styling inconsistencies and syntax to match Java standards.
- **[1.0.4]** Figured out how to pass data in from frontend to backend through thymeleaf actions and ModelAttributes. 
- **[1.0.3]** Implement INNER JOIN for multiple tables through the use of IdClass and Composite keys to find user cart, items, item prices, and quantities given a userID.
- **[1.0.4]** Added cart functionality with API routes and mapped carts to items and users using separate schemas.
- **[1.0.2]** I added user schema to the database and seeded initial data. I setup API routes in the UserController for login and register functionality, which required querying. I also setup a Postman collection, so that group members could the test the API endpoints during their implementation. https://github.com/nguyensjsu/fa21-172-hidden-dragon/commit/d7d4b12873d2119a3f8cc8770f73f2ca2e549ac4
- **[1.0.1]** I added MySQL connection to Spring application through CRUDRepository and Spring JPA. Add item schema and setup purchase API endpoint. https://github.com/nguyensjsu/fa21-172-hidden-dragon/commit/4b03738ef855526311cdcaafe2ae9e977199afb7

**Challenges**

- One of the biggest challenges I faced as that I couldn't for the longest time figure out how to use my local instance of MySQL on computer. I tried all available ways of resetting the password (uninstalling and modifying user profiles). Whenever I would use SQL in this class in the past, I hadn't gotten it to work because the Spring application would connect to the local instance on port 3306 rather than the docker image. For some reason, I never noticed that there was a conflict and tried another port or killed the local instance.
- One challenge I faced was figuring out the most up to date method to pull data and passing data into backend through Thymeleaf, Brandon and I had it initially setup so that the data was separate variables but combining them based on models made it a lot simpler to implement for the both of us.
- Another challenge was porting the entire API over and refactoring custom queries that were unnecessary because of built in findBy methods. 

## Week 1

**Snapshot**

**Accomplishments**

- **[1.0.0]** Spring Boot setup https://github.com/nguyensjsu/fa21-172-hidden-dragon/commit/d45e9429642ddc20650d6dce9f289a1afac3c9d3

**Challenges**

- None
