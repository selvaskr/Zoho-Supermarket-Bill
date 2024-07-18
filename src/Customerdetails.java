import java.sql.*;
import java.util.Scanner;

public class Customerdetails {


    static String url = "jdbc:mysql://localhost:3308/supermarket";
    static String user = "root";
    static String pass = "";

    // Reference Objects
    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    Scanner sc=new Scanner(System.in);

    // Function for authorization
    public boolean checkadmin(String admin, String password )
    {   
        String p="ght";
        if(admin.equals(user) && password.equals(p)){
            return true;
        }
        return false;
    }

    // Function for checking wheather the customer is already present in Data Base or New Customer
    public int newCustomerorAlready() throws SQLException {

        System.out.print("Enter Customer Mobile Number : ");
        Integer num = sc.nextInt();

        if (checkcustomer(num)){

            System.out.print("Already a customer");

            String sql="SELECT Cust_id FROM customer WHERE Mobile_No=?";

            conn = DriverManager.getConnection(url, user, pass);
            st = conn.prepareStatement(sql);
            st.setInt(1, num);
            rs=st.executeQuery();
            if(rs.next()){
                return rs.getInt("Cust_id");
            }
        } 
        else {

            // If New ,then add the details to the Data Base
            System.out.print("Enter Customer Name : ");
            String name = sc.next();

            conn = DriverManager.getConnection(url, user, pass);
            String sql = "INSERT INTO customer(Cust_Name,Mobile_No) values(?,?)";
            st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.setInt(2, num);
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Get the generated Cust_id
            }
        }
        return 0;
    }

    public boolean checkcustomer(Integer num) {
        try {
            conn = DriverManager.getConnection(url, user, pass);
            String sql = "select * from customer where Mobile_No = ?";
            st = conn.prepareStatement(sql);
            st.setInt(1, num);
            rs=st.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }

    // Function For Showing The Products That The Particular Customer Brought On Each Invoice ID
    
    public void customer(int num){
        Generatebill g=new Generatebill();
        boolean flag=checkcustomer(num);
        if(!flag){
            System.out.print("Invalid Phone Number ");
        }else{
            try
            {
            String sql = "select * from customer where mobile_No = ?";

            conn = DriverManager.getConnection(url, user, pass);
            st = conn.prepareStatement(sql);
            st.setInt(1, num);
            rs = st.executeQuery();

            String Customer_name ="";
            Integer Customer_number=0;
            int Customer_id=0;

            if(rs.next()) {
                Customer_id=rs.getInt("Cust_id");
                Customer_name =rs.getString("Cust_Name");
                Customer_number =rs.getInt("Mobile_No");
            }
            System.out.println("\nCustomer Name  : "+Customer_name+" \nMobile Number  :  "+Customer_number);

            String sql1= "SELECT bill_id,date,TotalPrice from billing where Cust_id=?";

            st = conn.prepareStatement(sql1);
            st.setInt(1,Customer_id);
            rs = st.executeQuery();

            while(rs.next()){

                int bill_id=rs.getInt("bill_id");
                int Totalamount=rs.getInt("TotalPrice");
                
                System.out.println("\nInvoice ID   : "+bill_id+"\nInvoice Date : "+rs.getString("date")+" \nTotal Amount  :  "+Totalamount);
                g.Billedproductsdisplay(bill_id);
                }
            }catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }
}
