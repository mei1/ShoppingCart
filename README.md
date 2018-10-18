# ShoppingCart

#### Goals:
To implement a checkout system that fulfills the requirements described in above scenario and to design the CheckoutRegister implementation on how it would return the final price that the customer would have to pay.

#### Solution:
- All the products and deals are maintained in individual properties file, this is the database for now.
- As soon as the CheckoutRegister is opened, it reads the products that are available in the store and the deals that are configured for products, and store them in memory.
- As the items are scanned one by one, they will be added into a list, and then group them by SKU.
- Since the deals are SKU specific, deal is to be applied to each of the group created in previous step, rules will calculate the final price of the product.
- The final due amount is the sum of final price of all products from previous step.

#### Assumptions:
- Only one deal per sku.
- Each deal needs to be implemented first. Currently 3 deals are implemented.
