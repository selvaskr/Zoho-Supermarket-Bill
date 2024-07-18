# Zoho-Supermarket-Bill

A Java-based terminal application utilizing JDBC MySQL for managing customer details, product listings, generating bills, and tracking product sales in a supermarket setting.


 ## DataBase Design

 ### Customer
This table contains information about customers.

| Column Name | Data Type | Description         |
|-------------|------------|---------------------|
| Cust_id     | INT        | Unique identifier for the customer |
| Cust_name   | VARCHAR    | Name of the customer |
| Cust_number | VARCHAR    | Contact number of the customer |

### Product
This table contains information about products.

| Column Name | Data Type | Description         |
|-------------|------------|---------------------|
| Prod_id     | INT        | Unique identifier for the product |
| Prod_Name   | VARCHAR    | Name of the product |
| Prod_price  | DECIMAL    | Price of the product |

### Billing
This table contains information about bills.

| Column Name | Data Type | Description         |
|-------------|------------|---------------------|
| bill_id     | INT        | Unique identifier for the bill |
| cust_id     | INT        | Foreign key referencing `Customer(Cust_id)` |
| Total_price | DECIMAL    | Total price of the bill |

### Billing Items
This table contains information about items in a bill.

| Column Name | Data Type | Description         |
|-------------|------------|---------------------|
| item_id     | INT        | Unique identifier for the billing item |
| bill_id     | INT        | Foreign key referencing `Billing(bill_id)` |
| product_id  | INT        | Foreign key referencing `Product(Prod_id)` |
| quantity    | INT        | Quantity of the product in the bill |

<br>

 ## Database Design Diagram

![image](https://github.com/user-attachments/assets/3eb10835-f187-4e23-a271-d45154c4cde5)

<br>

 # Modules and Description
 
 ## 1. Billing Management
    
   ### Description:

   * Generate New Bill: This module creates a new bill for the customer. It checks whether the customer is new or returning, showcases available items, allows the customer to buy items, and then displays the new bill.
     
   * New Customer Registration: When a new customer arrives, their details are added to the database for future reference. 

  ## 2. Customer Details Display
   
   ### Description:
     
   * Customer Information: Displays detailed information about the customer.
   
   * Purchase History: Shows the different items bought by the customer on various dates, along with invoice IDs.
     
   ## 3. Product Listing
      
   ### Description:

   * Available Products: Lists all products that are currently in stock.
   
   ## 4. Sales Analytics
      
   ### Description:
  
   * Overall Product Sales: Displays overall sales data for each product.
   
   * Date-wise Sales: Shows sales data for each product on a date-by-date basis.
     
   ## 5. Daily Sales Report
    
   ### Description:
  
   * Daily Sales Numbers: Shows the number of sales for each product on a specific day.
   
   * Invoice and Customer Details: Displays the number of invoices generated and customer details for each invoice on that day.
   
   ## 6. Product Database Management
      
   ### Description:
 
   * Admin Access Only: Restricts access to admin users for adding or updating products.
   
   * Product Management: Allows admin to add new products or update the availability of existing products.
 

