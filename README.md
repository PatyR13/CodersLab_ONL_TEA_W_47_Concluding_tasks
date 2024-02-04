# CodersLab - Concluding tasks.
## Final project for the Automation Tester course.

> *Content of tasks*:

**Workshop TASK 1 - Selenium WebDriver + Cucumber**

Create a user manually at https://mystore-testlab.coderslab.pl.

Write a script that:
- will log in to this created user,
- will enter by clicking on the Addresses tile after logging in, click on + Create new address,
- fills in the New address form - the data should be taken from the Examples table in Gherkin (alias, address, city, zip/postal code, country, phone),
- checks whether the data in the added address are correct.

*Additional steps for those wishing to*:

- *will delete the above address by clicking on "delete"*,
- *check if the address has been deleted.*

**Workshop TASK 2 (any way)**

Write a script that:

- logs in the same user from task 1,
- selects a Hummingbird Printed Sweater to purchase *(extra option: check if there is a 20% discount on it)*,
- selects a size M *(extra option: make it so that you can parameterise the size and select S,M,L,XL)*,
- selects 5 pieces according to the parameter given in the test *(extra option: make it possible to parameterise the number of pieces)*,
- add the product to the basket,
- proceed to the option - checkout,
- confirm the address (you can add it manually beforehand),
- select the pickup method - PrestaShop "pick up in store",
- select the payment option - Pay by Check,
- click on "order with an obligation to pay",
- takes a screenshot of the order confirmation and the amount.

*Additional steps for those willing to do so*:

- *go to order history and details (first click on logged-in user, then tile)*,
- *check if the order is listed with the status "Awaiting check payment" and the same amount as on the order two steps earlier*.
