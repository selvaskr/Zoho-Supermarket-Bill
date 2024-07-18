import java.sql.*;

public class Productlisting {
    String productname;
    int id;
    Integer price;
    Integer Quantity;
    static String url = "jdbc:mysql://localhost:3308/supermarket";
    static String user = "root";
    static String pass = "";

    // Reference Objects
    Connection conn;
    PreparedStatement pstmtProduct;
    ResultSet rs;


    // Function for the adding new Products to the DataBase(Restricts to the Admin only)
    public void addproduct(String Name,int Price,int Quantity) throws SQLException
    {
        conn = DriverManager.getConnection(url, user, pass);
        String sql = "INSERT INTO products (Prod_Name,Prod_Price,Prod_Availability) VALUES (?,?,?)";
        pstmtProduct = conn.prepareStatement(sql);
        pstmtProduct.setString(1, Name);
        pstmtProduct.setInt(2, Price);
        pstmtProduct.setInt(3, Quantity);
        pstmtProduct.executeUpdate();

        System.out.print("\nProduct Inserted Successfully\n");
    }
    
    // Function for the Updating Products to the DataBase(Restricts to the Admin only)
    public void UpdatepriceandQuantity(int Quantity,int price,int id)throws SQLException{

        String sql = "UPDATE products SET Prod_Price=?,Prod_Availability=?  WHERE Prod_id=?";

        conn = DriverManager.getConnection(url, user, pass);
        pstmtProduct = conn.prepareStatement(sql);
        pstmtProduct.setInt(1, price);
        pstmtProduct.setInt(2, Quantity);
        pstmtProduct.setInt(3, id);
        pstmtProduct.executeUpdate();

        System.out.print("\nProduct Updated Successfully\n");
    }

    // Function for Showcasing the list of available products to the customer
    public void productitems() throws SQLException{
        String sql = "select * from products";
        conn = DriverManager.getConnection(url, user, pass);
        pstmtProduct = conn.prepareStatement(sql);
        rs = pstmtProduct.executeQuery();

        System.out.println("\n ********* Product Listings *********\n");

        System.out.println("--------------------------------------------------");

        System.out.printf("| %-10s | %-20s | %-10s |%n", "ID", "Name","Price");

        while(rs.next()){

            id=rs.getInt("Prod_id");
            productname=rs.getString("Prod_Name");
            price=rs.getInt("Prod_Price");
            Quantity=rs.getInt("Prod_Availability");
            if(Quantity>0){

                System.out.println("--------------------------------------------------");
                System.out.printf("| %-10s | %-20s | %-10s |%n", id, productname, price);
            }
            
        }
        System.out.println("--------------------------------------------------");
    }

    // Function for getting the price of Particular Product ID
    public int getproductprice(int id) throws SQLException{

        String sql = "select Prod_Price from products where Prod_id=?";

        conn = DriverManager.getConnection(url, user, pass);
        pstmtProduct = conn.prepareStatement(sql);
        pstmtProduct.setInt(1, id);
        rs=pstmtProduct.executeQuery();

        if(rs.next()){
            return rs.getInt("Prod_Price");
        }else{
            return 0;
        }  
    }
    
    // Function for getting the Current Availablilty of Particular Product ID
    public int getavailablilty(int id) throws SQLException{

        String sql = "select Prod_Availability from products where Prod_id=?";

        conn = DriverManager.getConnection(url, user, pass);
        pstmtProduct = conn.prepareStatement(sql);
        pstmtProduct.setInt(1, id);
        rs = pstmtProduct.executeQuery();

        if (rs.next()) {
            return rs.getInt("Prod_Availability");
        } else {
            return 0;
        }
    }
    
    // Function for Updating the Availablilty of Particular Product ID After each time Customer Buys the Product
    public void updateavailablilty(int id,int Quantity) throws SQLException{

        String sql="UPDATE products SET Prod_Availability=? WHERE Prod_id=?";

        conn = DriverManager.getConnection(url, user, pass);
        pstmtProduct = conn.prepareStatement(sql);
        pstmtProduct.setInt(1, Quantity);
        pstmtProduct.setInt(2, id);
        pstmtProduct.executeUpdate();
    }
}
