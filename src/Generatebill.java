import java.sql.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Generatebill {

    static String url = "jdbc:mysql://localhost:3308/supermarket";
    static String user = "root";
    static String pass = "";

    int bill_id=0;  
    
    Productlisting p = new Productlisting();
    Scanner sc=new Scanner(System.in);

    Connection conn;
    PreparedStatement ps;
    ResultSet  rs;

    // Showcase the products to Customer 

    public void showcaseproducts() throws SQLException{
        p.productitems();
    }

    public int getbillidfornewbill(int cust_id) throws SQLException {

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = currentDate.format(formatter);

        String sql = "INSERT INTO billing (Cust_id,date) VALUES (?,?)";

        conn = DriverManager.getConnection(url, user, pass);
        ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, cust_id);
        ps.setString(2, date);
        ps.executeUpdate();
        rs = ps.getGeneratedKeys();
        int id = 0;

        if (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }

    public void addcustomerbuyeditems(int Bill_id, int Prod_id, int Quantity) throws SQLException {


        conn = DriverManager.getConnection(url, user, pass);
        String sql = "INSERT INTO billingitems (Bill_id,Prod_id,Quantity) VALUES (?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, Bill_id);
        ps.setInt(2, Prod_id);
        ps.setInt(3, Quantity);
        ps.executeUpdate();
    }

    public void buyingitems(int Cust_id) throws SQLException {

        boolean wanttobuy = true;
        int Bill_id = getbillidfornewbill(Cust_id);
        int amount = 0;

        while (wanttobuy) {

            showcaseproducts();

            System.out.print("Enter the id of the product you wanted to buy : ");
            int productid = sc.nextInt();
            System.out.print("Enter Quantity of selected Product : ");
            int quantity = sc.nextInt();
            int avail=p.getavailablilty(productid);
            if(quantity>avail){
                int flag;
                System.out.print("\nAvailable Stock is only "+avail+"\nDo You Want to Buy The Available Stock [ Yes 1/No 0 ] : ");
                flag=sc.nextInt();
                if(flag==1){
                    quantity=avail;
                    p.updateavailablilty(productid,quantity);
                }else{
                    continue;
                }
            }

            p.updateavailablilty(productid, (avail-quantity));

            amount = amount + p.getproductprice(productid) * quantity;

            addcustomerbuyeditems(Bill_id, productid, quantity);
            
            System.out.print("Want to continue shopping [YES 1 /NO 0] : ");
            int input = sc.nextInt();
            if (input == 0) {
                wanttobuy=false;
            }
        }

        // updateamount(Bill_id, amount);
        // billedCustomerdetails(Bill_id);
    }

    public void updateamount(int Bill_id, int amount) throws SQLException {

        String sql = "UPDATE billing SET Total_price = ? WHERE bill_id = ?";
        conn = DriverManager.getConnection(url, user, pass);
        ps = conn.prepareStatement(sql);
        ps.setInt(1, amount);
        ps.setInt(2, Bill_id);
        ps.executeUpdate();
    }


    // To Display the Customer Details of Particular Bill ID.

    public void billedCustomerdetails(int B_id) throws SQLException{
        

        String sql = "SELECT B.Bill_id,B.Cust_id,B.TotalPrice,C.Cust_Name,C.Mobile_No,B.date FROM Billing AS B INNER JOIN customer AS C ON B.Cust_id=C.Cust_id where B.Bill_id = ?";

        conn = DriverManager.getConnection(url, user, pass);
        ps = conn.prepareStatement(sql);
        ps.setInt(1, B_id);

        rs = ps.executeQuery();
        String Customer_name ="";
        Integer Customer_number=0;
        int Bill_id=0,Total=0;
        if(rs.next()) {
            Bill_id=rs.getInt("Bill_id");
            Customer_name =rs.getString("Cust_Name");
            Customer_number =rs.getInt("Mobile_No");
            Total=rs.getInt("TotalPrice");
            }

        System.out.println("\nInvoice Id : "+Bill_id + "\nInvoice Date : " + rs.getString("date")+"\nCustomer Name  : "+Customer_name+" \nMobile Number  :  "+Customer_number+"\nTotal Amount : "+Total);
        Billedproductsdisplay(Bill_id);
        
    }

    // To Display the Items Customer buyed.

    public void Billedproductsdisplay(int bill_id){

    try{
        conn = DriverManager.getConnection(url, user, pass);
        
        String sql2="SELECT  P.Prod_name, P.Prod_Price,I.Quantity FROM Billing AS B INNER JOIN Billingitems AS I ON B.Bill_id = I.Bill_id " +
                    "INNER JOIN Products AS P ON P.Prod_id = I.Prod_id WHERE B.Bill_id = ?";

        ps = conn.prepareStatement(sql2);
        ps.setInt(1,bill_id);
        rs = ps.executeQuery();

        System.out.println("--------------------------------------------------");

        System.out.printf("| %-10s | %-20s | %-10s |%n", "Product", "Quantity", "Price");
        while(rs.next()){

            String product=rs.getString("Prod_name");
            int Quantity=rs.getInt("Quantity");
            int price =rs.getInt("Prod_Price");

            System.out.println("--------------------------------------------------");

            System.out.printf("| %-10s | %-20s | %-10s |%n", product, Quantity, price);
            // System.out.println("Product : "+product+" Quantity : "+Quantity+" Cost : "+price);
        }
        System.out.println("--------------------------------------------------");

        System.out.println("\n");
    } catch (SQLException e) {

        e.printStackTrace();
    }
    }

}

