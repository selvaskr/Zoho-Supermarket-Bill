# Zoho-Supermarket-Bill

 DataBase Design

![image](https://github.com/user-attachments/assets/3eb10835-f187-4e23-a271-d45154c4cde5)

 
 
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
 
 ## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

