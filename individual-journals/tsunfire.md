# Tsunfire Journal

## Week 3

**Snapshot**
![img](https://i.gyazo.com/42d114b095a60bf7711e1228b9d54649.png)


**Accomplishments**

- **[1.0.8]** Went through the step by step process of creating a docker-compose file and building the image and pushing the image to docker. I then used kompose convert to create service and deployment yamls. I had to manually modify a few of them (convert Docker to LoadBalancer) to get everything working.
- **[1.0.7]** Added new image field to Items and relayed data to front end to be able to query for images.
- **[1.0.6]** I isolated Rest controllers from the MainController and removed unnecessary code like Exceptions and ModelAssemblers. I also setup the cart API and relayed data to the main controller and to Thymeleaf frontend. 

**Challenges**

- One of the biggest challenges I had was during the K8 deployment of our application. Prior to the deployment, I had seeded the data manually using docker exec, but when it came time for deployment the data wasn't being seeded or the tables weren't being setup. I had initially thought JPA auto updates the repositories, but I had to do additional configurations to seed the table and data from a file. 
- Another challenge was debugging the frontend and relaying inputs to the MainController. The debugging messages weren't very insightful as they had just mentioned BindingErrors, so we had to go through manually to really find the root of the problem and fix it. 

## Week 2

**Snapshot**
![img](https://i.gyazo.com/fb52a4ffb673f80d4fa4411d4fe35ec1.png)

**Accomplishments**

- **[1.0.5]** I converted the entire API to a RESTful API (Cart, Items, Users) with exception handling, model assembly, link creation, responseentity returns. Also modified code styling inconsistencies and syntax to match Java standards. I am also constantly adding and updating Postman collection, so that team members can test API.
- **[1.0.4]** Figured out how to pass data in from frontend to backend through thymeleaf actions and ModelAttributes. 
- **[1.0.3]** Implement INNER JOIN for multiple tables through the use of IdClass and Composite keys to find user cart, items, item prices, and quantities given a userID.
- **[1.0.4]** Added cart functionality with API routes and mapped carts to items and users using separate schemas.
- **[1.0.2]** I added user schema to the database and seeded initial data. I setup API routes in the UserController for login and register functionality, which required querying. I also setup a Postman collection, so that group members could the test the API endpoints during their implementation. https://github.com/nguyensjsu/fa21-172-hidden-dragon/commit/d7d4b12873d2119a3f8cc8770f73f2ca2e549ac4
- **[1.0.1]** I added MySQL connection to Spring application through CRUDRepository and Spring JPA. Add item schema and setup purchase API endpoint. https://github.com/nguyensjsu/fa21-172-hidden-dragon/commit/4b03738ef855526311cdcaafe2ae9e977199afb7

**Challenges**

- One of the biggest challenges I faced as that I couldn't for the longest time figure out how to use my local instance of MySQL on computer. I tried all available ways of resetting the password (uninstalling and modifying user profiles). Whenever I would use SQL in this class in the past, I hadn't gotten it to work because the Spring application would connect to the local instance on port 3306 rather than the docker image. For some reason, I never noticed that there was a conflict and tried another port or killed the local instance.
- One challenge I faced was figuring out the most up to date method to pull data and passing data into backend through Thymeleaf, Brandon and I had it initially setup so that the data was separate variables but combining them based on models made it a lot simpler to implement for the both of us. Using th:name and th:field and then passing in a th:object made data extraction very simple for the MainController and then sending requests to the rest api. 
- Another challenge was porting the entire API over and refactoring custom queries that were unnecessary because of built in findBy methods. I had initially followed previous labs to setup the repositories but querying the data was a lot more simple than I had initially thought. Most of the querying required by our application was solveable with the built in methods.

## Week 1

**Snapshot**

**Accomplishments**

- **[1.0.0]** Spring Boot setup https://github.com/nguyensjsu/fa21-172-hidden-dragon/commit/d45e9429642ddc20650d6dce9f289a1afac3c9d3

**Challenges**

- None
