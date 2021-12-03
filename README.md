<div align="center">
<img align="center" src="https://img.shields.io/static/v1?label=author&message=tsunfire&color=ffd74a&style=for-the-badge&logo=data%3Aimage/png%3Bbase64%2CiVBORw0KGgoAAAANSUhEUgAAABQAAAATCAYAAACQjC21AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAIGNIUk0AAHpFAACAgwAA/FcAAIDoAAB5FgAA8QEAADtfAAAcheDStWoAAAD0SURBVHjarJQ9SoNBEEDfp00q8QoxQgoPYG1pkQPYWpgbWAvaWRm7NLlGUEEQFETwD8RCsLCMYK%2BQvDQRJNlvv93EB9vM7jx2Z4ZFJbJ21Qt1oH6ql2o7llO2sayeW86VWssRnlnNdaqwZTo70/lLzNImnb3pQEi4niFcSxEOM4TDFOFzhvBlJhJoymZGU7ZSx%2BY0QdbLmUPUTkTWLcsr1FiN6sA2sAEUwCvQB97KEqqEAKtAYyJ8B76ipwPXbqgH6p36HXjuj3qvHqnNqhoeqyPzOAkJa%2Bqt8/OkrvwV3rg4j7/Cff%2BPw0J9mHwIshgF8DEeAPZgZ0kPPubLAAAAAElFTkSuQmCC">

<img align="center" src="https://img.shields.io/static/v1?label=author&message=brandon cheu&color=ffd74a&style=for-the-badge&logo=data%3Aimage/png%3Bbase64%2CiVBORw0KGgoAAAANSUhEUgAAABQAAAATCAYAAACQjC21AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAIGNIUk0AAHpFAACAgwAA/FcAAIDoAAB5FgAA8QEAADtfAAAcheDStWoAAAD0SURBVHjarJQ9SoNBEEDfp00q8QoxQgoPYG1pkQPYWpgbWAvaWRm7NLlGUEEQFETwD8RCsLCMYK%2BQvDQRJNlvv93EB9vM7jx2Z4ZFJbJ21Qt1oH6ql2o7llO2sayeW86VWssRnlnNdaqwZTo70/lLzNImnb3pQEi4niFcSxEOM4TDFOFzhvBlJhJoymZGU7ZSx%2BY0QdbLmUPUTkTWLcsr1FiN6sA2sAEUwCvQB97KEqqEAKtAYyJ8B76ipwPXbqgH6p36HXjuj3qvHqnNqhoeqyPzOAkJa%2Bqt8/OkrvwV3rg4j7/Cff%2BPw0J9mHwIshgF8DEeAPZgZ0kPPubLAAAAAElFTkSuQmCC">
</div>

---

<h3 align="center">Hidden Dragon</h3>

<p align="center">A fullstack drugstore application built on Spring with Cybersource payment integration</p>

---

## Technical Requirements

### Spring Framework

- We initalized the project using the spring initializer website (start.spring.io) in which we created the project with the dependancies of Lombok, Spring web, Thymeleaf, and Spring data JPA.
  ![spring](https://user-images.githubusercontent.com/54514627/143801689-0906eaaf-adeb-41d8-a4cd-0483feef9189.PNG)

### Front Office Portal
-We designed and created a simple user interface in which the login,register, and checkout navigation is located in the navigation bar for the user to see easily in order to either create an account or to login to an existing one. 
![home](https://user-images.githubusercontent.com/54514627/144555250-79cb0d90-9355-49aa-912f-2e1f084d2522.PNG)
![login](https://user-images.githubusercontent.com/54514627/144555240-1df608b6-01ac-4452-a0f0-907d914292b5.PNG)
![reg](https://user-images.githubusercontent.com/54514627/144555245-811d8e91-b6e9-47b4-a1dc-e7a31eb7ae63.PNG)


### Development Tools

- JDK version:
  ![jdk](https://user-images.githubusercontent.com/54514627/143801729-709c88a8-3b21-4ca5-9389-c0d77ab0b579.PNG)

- Gradle Version:
  ![gradle](https://user-images.githubusercontent.com/54514627/143801756-3f96c96e-e61d-469f-9227-0893fbbb2e76.PNG)

### Database and Middleware

- **MySQL**
  - MySQL 8.0 docker container deployed and connected with Spring project through SQL Java connector
  - Starter schema initialized with `bios-data.sql` and `bios-schema.sql`
  - Required SQL Query functions setup through CRUDRepository

### Credit Card Payment Support

- CyberSource Key Created
  ![cybersource](https://user-images.githubusercontent.com/54514627/143804123-466605bd-f3a6-4298-aafc-8022adc2507a.PNG)
