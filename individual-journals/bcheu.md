# Bcheu Journal

## Week 1

**Snapshot**


**Accomplishments**

- Started working on the front end portal implementing the overall framework in which the user will interact with. I went with a simple user interface involving login, register, and checkout in the navigation bar in which when clicked will open the form that the controller will read the values from. This insures simplicity and allows the user to not be confused upon using the user interface. 

**Challenges**

- None

## Week 2

**Snapshot**
<img width="1436" alt="cluster" src="https://user-images.githubusercontent.com/54514627/144974826-8e3e71f4-7b47-4489-ba04-f68d0c36e4df.jpg">

**Accomplishments**

- I started working on the front end integration with the controllers in which the data will be sent to either the database or the cybersource API in order to register the user or allow the user to login and process their payments. 
- Finished the registration for a new user with a successful send to the mySQL database
- Redesigned the front end to guide the user through registering and logging into their account to see the products to aviod confusion
- Added in the reset password into the template
- Implemented redirecting links to each class in order to have correct url after navigating through links
- Implemented the controller to support cybersource tested and succeeded in recieving test payment
- Grabbed items from database and display them on the website as well as successful form submission


**Challenges**

- Some challenges I faced was the overall integration of the front end forms to their parts in the controllers in which we had only been exposed to one form to one controller method as seen in the spring-payments series of labs. This faced a challenge in which led to many bugs and the code crashing upon running gradle bootRun and running the application locally. This was solved with the help of my teammate Alan. I then was able to create a series of templates to helpguide the user through the registration and login process in order to see the products. 
- Another challenge I faced was the cybersource support in which I had found out that it needed the month of expiration to be in 01-12 format and also needed the card type. I solved this by implementing the template to run the format of 01-12 rather than the user inputting the month in thought of online shops implementing this. I solved the card type by implementing card if statements that read the first digit of the card and determine the card type.

## Week 3

**Snapshot**
<img width="1436" alt="cluster" src="https://user-images.githubusercontent.com/54514627/144974880-3d1fe2e5-0a2e-4d14-a65e-fc1bf74d837a.jpg">

**Accomplishments**

-- Allowed for successful add to cart functions which will update the cart table in mySQL and navigate the user to the checkout page and if the user wants to add an additional item they can navigate back to the store page
- Successful cybersource payment processing when user enters a valid card (Card number where the type of card is found through the first digit of the card number, the expiration month, and expiration year)
- Implemented a page where after successful payment the application will navigate the user to the thank you page. 
- Successful formatting and displaying of data and images for the store and products page
- Docker in which both me and Alan had done in order to practice and continue learning and practicing the build process to docker and later to GKE. 

**Challenges**

- A challenge I faced was the overall formatting of the products in which when displaying utilizing the tr and td tags in a table would show up in reverse for example the image and input for quantity would display first and since we utilized a th:each statement the loop would show all images and quantity input first then the item and price on the bottom. In order to fix this I had everything the same regarding the tr tags in a table but i had formatted the page to display the price and item using p tags inside the table. 
- Another challenge I faced was the error of redirecting after adding an item to the cart this happened due to the main controller method redirecting to a link that did not exist. In order to fix this I implemented the add to cart function to redirect to the checkout page in order to avoid getting the error. 
