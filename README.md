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


# Modules ScreenShots

 ## 1. Billing Management
 
 ![image](https://github.com/user-attachments/assets/ac6af11c-b105-4819-adb9-4d66e9a0aee6)

![image](https://github.com/user-attachments/assets/550c087b-ab10-4c9e-bd40-e7b970950dfa)

![image](https://github.com/user-attachments/assets/53826faf-ffb0-43da-aa2d-d99f1510e882)

### 1.1 If Suppose a Custommer enters the Quantity more than the Available Stock

![image](https://github.com/user-attachments/assets/8d056669-f7d3-465c-abc5-0cf0bfe90624)

![image](https://github.com/user-attachments/assets/614e9bba-24e2-4fb5-a58c-2522ac499781)


## 2. Customer Details Display

![image](https://github.com/user-attachments/assets/6732816c-2179-45ad-8918-76996c3b5881)

![image](https://github.com/user-attachments/assets/0002ca70-cbe8-4c4d-b416-72607597be23)

## 3. Product Listing

![image](https://github.com/user-attachments/assets/b09a4613-18af-450a-b56b-8a05a7127473)

## 4. Sales Analytics

![image](https://github.com/user-attachments/assets/6486df75-3927-4c28-a8df-e48efc0c10a6)

## 5. Daily Sales Report

![image](https://github.com/user-attachments/assets/0cb14846-b9bb-43cf-93f0-da024ca52e09)

![image](https://github.com/user-attachments/assets/af5ff709-b1ad-4386-837c-47122042413b)

## 6. Product Database Management

### 6.1 Add Product

![image](https://github.com/user-attachments/assets/c50898e8-2599-4d5f-ad6a-4407791a4562)

### 6.2 Update Product Stock or Price

![image](https://github.com/user-attachments/assets/59c6f1d7-ffb9-473e-a0e1-ebe644b4652b)





