<div align="center">
<img align="center" src="https://img.shields.io/static/v1?label=author&message=tsunfire&color=ffd74a&style=for-the-badge&logo=data%3Aimage/png%3Bbase64%2CiVBORw0KGgoAAAANSUhEUgAAABQAAAATCAYAAACQjC21AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAIGNIUk0AAHpFAACAgwAA/FcAAIDoAAB5FgAA8QEAADtfAAAcheDStWoAAAD0SURBVHjarJQ9SoNBEEDfp00q8QoxQgoPYG1pkQPYWpgbWAvaWRm7NLlGUEEQFETwD8RCsLCMYK%2BQvDQRJNlvv93EB9vM7jx2Z4ZFJbJ21Qt1oH6ql2o7llO2sayeW86VWssRnlnNdaqwZTo70/lLzNImnb3pQEi4niFcSxEOM4TDFOFzhvBlJhJoymZGU7ZSx%2BY0QdbLmUPUTkTWLcsr1FiN6sA2sAEUwCvQB97KEqqEAKtAYyJ8B76ipwPXbqgH6p36HXjuj3qvHqnNqhoeqyPzOAkJa%2Bqt8/OkrvwV3rg4j7/Cff%2BPw0J9mHwIshgF8DEeAPZgZ0kPPubLAAAAAElFTkSuQmCC">

<img align="center" src="https://img.shields.io/static/v1?label=author&message=brandon cheu&color=ffd74a&style=for-the-badge&logo=data%3Aimage/png%3Bbase64%2CiVBORw0KGgoAAAANSUhEUgAAABQAAAATCAYAAACQjC21AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAIGNIUk0AAHpFAACAgwAA/FcAAIDoAAB5FgAA8QEAADtfAAAcheDStWoAAAD0SURBVHjarJQ9SoNBEEDfp00q8QoxQgoPYG1pkQPYWpgbWAvaWRm7NLlGUEEQFETwD8RCsLCMYK%2BQvDQRJNlvv93EB9vM7jx2Z4ZFJbJ21Qt1oH6ql2o7llO2sayeW86VWssRnlnNdaqwZTo70/lLzNImnb3pQEi4niFcSxEOM4TDFOFzhvBlJhJoymZGU7ZSx%2BY0QdbLmUPUTkTWLcsr1FiN6sA2sAEUwCvQB97KEqqEAKtAYyJ8B76ipwPXbqgH6p36HXjuj3qvHqnNqhoeqyPzOAkJa%2Bqt8/OkrvwV3rg4j7/Cff%2BPw0J9mHwIshgF8DEeAPZgZ0kPPubLAAAAAElFTkSuQmCC">
</div>

---

<h3 align="center">Hidden Dragon</h3>

<p align="center">A fullstack drugstore application built on Spring with Cybersource payment integration</p>

---
## Architecture Diagram

## Technical Requirements

### Spring Framework

- We initalized the project using the spring initializer website (start.spring.io) in which we created the project with the dependancies of Lombok, Spring web, Thymeleaf, and Spring data JPA.
  ![spring](https://user-images.githubusercontent.com/54514627/143801689-0906eaaf-adeb-41d8-a4cd-0483feef9189.PNG)

### Front Office Portal
-We designed and created a simple user interface in which the user will be walked though a process of either creating or logging in to an exisiting account in order to view the products this allows the user to avoid losing items in their cart without an account upon checking out.   
-Upon loading the site the user will see this screen in which they will either login into their exisiting account or they will register for a new one
![home](https://user-images.githubusercontent.com/54514627/144564583-1305c93c-adb8-4bb4-b687-958ccee7229d.PNG)

-If the user needs to register a new account they will see the following screens:
Registering form
![reg](https://user-images.githubusercontent.com/54514627/144564823-aca3683f-1bdd-4753-8e8a-b6e209b3a0f3.PNG)
After submitting to create the account
![post_reg](https://user-images.githubusercontent.com/54514627/144564860-9b5c0180-3972-4ddf-9bb3-0f50c38487a1.PNG)

-After registering the account the user will be prompted by the message to login to see the products:
![login](https://user-images.githubusercontent.com/54514627/144564964-7df0e298-fb31-4c4e-bce7-a5c10fceb5cd.PNG)

-The user also has the option to reset their password using the form in the navigation bar.
![reset](https://user-images.githubusercontent.com/54514627/144739457-d4ff74d9-533b-405c-b15f-8554905476f7.PNG)
![resetPass](https://user-images.githubusercontent.com/54514627/144739458-93ff753a-b107-4a91-89e5-50e2ceadc774.PNG)

-After the user logs into their account they will then see the products
![products](https://user-images.githubusercontent.com/54514627/144565035-4c32b747-895b-46e2-9767-87b63e93f60e.PNG)

-After adding an item to the cart the user will be navigated to the checkout page
![checkout](https://user-images.githubusercontent.com/54514627/144942966-231a06ad-ef6c-4ccd-8719-ebfbae9d23f6.PNG)

-After successful payment the user will be navigated to the thank you for your order page
![sucpurchase](https://user-images.githubusercontent.com/54514627/144942879-4b8868db-d087-4851-b8ea-839fb8621463.PNG)


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
