# Bcheu Journal

## Week 1

**Snapshot**
![home](https://user-images.githubusercontent.com/54514627/144554329-84dadf4c-2b93-4a7d-a067-fd13ad3bdf56.PNG)
![login](https://user-images.githubusercontent.com/54514627/144554324-1dd60611-a019-437d-a460-572f601e1f7e.PNG)
![reg](https://user-images.githubusercontent.com/54514627/144554328-581d4602-8e1a-4088-a8cc-21ecfdf00e65.PNG)


**Accomplishments**

- Started working on the front end portal implementing the overall framework in which the user will interact with. I went with a simple user interface involving login, register, and checkout in the navigation bar in which when clicked will open the form that the controller will read the values from. This insures simplicity and allows the user to not be confused upon using the user interface. 

**Challenges**

- None

## Week 2

**Snapshot**
![home](https://user-images.githubusercontent.com/54514627/144565478-2eae3b98-d189-40c3-9c39-1d055ec3d250.PNG)
![login](https://user-images.githubusercontent.com/54514627/144565481-70e12346-b93f-4c17-9fe9-5018e36b5f5d.PNG)
![reg](https://user-images.githubusercontent.com/54514627/144565488-47a7806d-5bf8-4c4f-a85a-f4de82df67f5.PNG)
![post_reg](https://user-images.githubusercontent.com/54514627/144565484-c842d847-599b-403f-81ca-a6abcc2ffea1.PNG)
![products](https://user-images.githubusercontent.com/54514627/144565486-fe029bb6-689c-493d-bfbd-5a83821953e9.PNG)
![reset](https://user-images.githubusercontent.com/54514627/144942073-85ecd35e-a4ea-466a-bce5-526bf8e2006e.PNG)
![resetPass](https://user-images.githubusercontent.com/54514627/144942075-0a7ef804-a0be-4041-9d53-eea3093ad29f.PNG)
![sucpurchase](https://user-images.githubusercontent.com/54514627/144942094-11d62e35-f10d-447f-a37a-271927ee83dc.PNG)
![cyber](https://user-images.githubusercontent.com/54514627/144942098-3fb69b82-fa15-4459-8b58-9f2a53179e57.PNG)




**Accomplishments**

- I started working on the front end integration with the controllers in which the data will be sent to either the database or the cybersource API in order to register the user or allow the user to login and process their payments. 
- Finished the registration for a new user with a successful send to the mySQL database
- Redesigned the front end to guide the user through registering and logging into their account to see the products to aviod confusion
- Added in the reset password into the template
- Implemented redirecting links to each class in order to have correct url after navigating through links
- Implemented the controller to support cybersource tested and succeeded in recieving test payment
- Grabbed items from database and formatted them to display the image, item, and price onto the webpage
- Allowed for successful add to cart functions which will update the cart table in mySQL and navigate the user to the checkout page and if the user wants to add an additional item they can navigate back to the store page
- Successful cybersource payment processing when user enters a valid card (Card number where the type of card is found through the first digit of the card number, the expiration month, and expiration year)
- Implemented a page where after successful payment the application will navigate the user to the thank you page. 

**Challenges**

- Some challenges I faced was the overall integration of the front end forms to their parts in the controllers in which we had only been exposed to one form to one controller method as seen in the spring-payments series of labs. This faced a challenge in which led to many bugs and the code crashing upon running gradle bootRun and running the applicaiton locally. This was solved with the help of my teammate Alan. I then was able to create a series of templates to helpguide the user through the registration and login process in order to see the products. Another challenge I faced was the cybersource support in which I had found out that it needed the month of expiration to be in 01-12 format and also needed the card type. I solved this by implementing the template to run the format of 01-12 rather than the user inputting the month in thought of online shops implementing this. I solved the card type by implementing card if statements that read the first digit of the card and determine the card type.
