# Final Project
### It is a project where customer, bill and payment information can be created/queried/updated/deleted.

Testing of APIs was done using Postman.

The path, request and response operations that show how to make the request from Postman are categorized as GET/PUT/POST/DELETE and are given below.

The project is started by running FinalProjectApplication.java.

### POST
1) http://localhost:8080/customers/createCustomer	-> Creates a new customer in the table.

   Request Body: 
   ```json
   {
       "name": "Zeynep",
       "surname": "Yalçınkaya"
   }
   ```
   Response:
   ```json
   "Customer created."
   ```

2) http://localhost:8080/bills/createBill/{subsNumber}	-> Creates a new bill in the table. Gets the customer's subscriber number and it saves the bill by stating that it belongs to a this customer.

   Request Body:
   ```json
   {
       "dept": 100
   }
   ```
   Response: 
   ```json
   "Bill created."
   ```

3) http://localhost:8080/payments/createPayment/{subsNumber}	-> Creates a new payment in the table. Gets the customer's subscriber number and it saves the payment by stating that it belongs to a this customer.

   Request Body: 
   ```json
   {
       "totalAmount": "200"
   }
   ```
   Response:
   ```json
   "Payment created."
   ```

### GET
1) http://localhost:8080/customers/getAll	-> Queries and returns all customers in the table.

   Response: 
   ```json
   [
       {
           "subsNumber": 100,
	   "name": "Zeynep",
           "surname": "Yalçınkaya"
       },
       {
           "subsNumber": 101,
           "name": "Ezgi",
           "surname": "Güvercin"
       }
   ]
   ```

2) http://localhost:8080/customers/getCustomer/{subsNumber}	-> Queries and returns spesific customer in the table. Gets the subscriber number of the customer to be queried.

   Response:
   ```json
   {
       "subsNumber": 100,
       "name": "Zeynep",
       "surname": "Yalçınkaya"
   }
   ```

3) http://localhost:8080/bills/getAll	-> Queries and returns all bills in the table.

   Response:
   ```json
   [
       {
           "billNumber": 2000,
           "dept": 100.0,
           "billProcessDate": "2022-07-24",
           "statu": 0,
           "customer": {
           "subsNumber": 101,
           "name": "Elif",
           "surname": "Güvercin"
       }
       },
       {
           "billNumber": 2002,
           "dept": 150.0,
           "billProcessDate": "2022-07-24",
           "statu": 0,
           "customer": {
               "subsNumber": 101,
               "name": "Elif",
               "surname": "Güvercin"
           }
       }
   ]
   ```

4) http://localhost:8080/bills/getBill/{billNumber}	-> Queries and returns spesific bill in the table. Gets the bill number of the bill to be queried.

   Response:	
   ```json
   {
       "billNumber": 2001,
       "dept": 100.0,
       "billProcessDate": "2022-07-24",
       "statu": 0,
       "customer": {
           "subsNumber": 101,
           "name": "Elif",
           "surname": "Güvercin"
       }
   }
   ```

5) http://localhost:8080/bills/getBillByCustomer/{subsNumber}	-> Queries and returns bill list by given subs number in the table. Returns bills if there are any unpaid bills. Otherwise it returns the "There are no unpaid bills." message.

   Response:
   ```json
   "There are no unpaid bills."
   ```

6) http://localhost:8080/payments/getAll	-> Queries and returns all payments in the table.

   Response:
   ```json
   [
       {
           "paymentNumber": 500,
           "totalAmount": 200.0,
           "paymentProcessDate": "2022-07-24",
           "customer": {
               "subsNumber": 100,
               "name": "Zeynep",
               "surname": "Yalçınkaya"
           }
       },
       {
           "paymentNumber": 501,
           "totalAmount": 250.0,
           "paymentProcessDate": "2022-07-24",
           "customer": {
               "subsNumber": 101,
               "name": "Elif",
               "surname": "Güvercin"
           }
       }
   ]
   ```

7) http://localhost:8080/payments/getPayment/{paymentNumber}	-> Queries and returns spesific payment in the table. Gets the payment number of the payment to be queried.

   Response:	
   ```json
   {
       "paymentNumber": 500,
       "totalAmount": 200.0,
       "paymentProcessDate": "2022-07-24",
       "customer": {
           "subsNumber": 100,
           "name": "Zeynep",
           "surname": "Yalçınkaya"
       }
   }
   ```

### DELETE
1) http://localhost:8080/customers/deleteCustomer/{subsNumber}	-> Deletes the specific customer in the table. Gets the subscriber number of the customer to be deleted.

   Response:
   ```json
   "Customer deleted."
   ```

2) http://localhost:8080/bills/deleteBill/{billNumber}	-> Deletes the specific bill in the table. Gets the bill number of the bill to be deleted.

   Response:
   ```json
   "Bill deleted."
   ```

### PUT
1) http://localhost:8080/customers/updateCustomer/{subsNumber}?name=Elif	->Updates the customer in the table. Gets the fields to be updated as parameters and the subscriber number of the customer to be updated.

   Query Params:	
   ```
   KEY	  VALUE
   name	  Elif
   ```

   Response:
   ```json
   "Customer updated."
   ```

2) http://localhost:8080/bills/payBill/{billNumber}	-> Updates the statu field in the table. Statu is equal to 0 by default and and the bill means unpaid. Updates the statu to 1 so the bill is considered paid.

   Response:
   ```json
   "Bill paid."
   ```
