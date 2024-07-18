
import java.sql.*;

public class BillingItems {

    static String url = "jdbc:mysql://localhost:3308/supermarket";
    static String user = "root";
    static String pass = "";

    // Reference Object
    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    // Function for finding the Each day sales of that Product
    public void Salesofproduct(int id) {
        try {

            String sql = "SELECT * FROM products WHERE Prod_id=? ";

            conn = DriverManager.getConnection(url, user, pass);
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {

                System.out.println("\nProduct Name : " + rs.getString("Prod_Name") + "\nProduct_Price : "
                        + rs.getInt("Prod_Price"));

                // Sql Query for Fetching the Product Quantity that are sold on each Date.
                String sql1 = "SELECT B.date,B.Bill_id,BI.Quantity FROM Billing as B INNER JOIN BillingItems as BI ON B.Bill_id=BI.Bill_id WHERE BI.Prod_id=?";

                PreparedStatement pst = conn.prepareStatement(sql1);
                pst.setInt(1, id);
                ResultSet rs1 = pst.executeQuery();

                System.out.println("--------------------------------------------------");
                System.out.printf("| %-10s | %-18s | %-10s |%n", "Invoice Date", "Invoice ID", "Quantity");
                while (rs1.next()) {
                    System.out.println("--------------------------------------------------");
                    System.out.printf("| %-12s | %-18d | %-10d |%n", rs1.getString("date"), rs1.getInt("Bill_id"),
                            rs1.getInt("Quantity"));
                    // System.out.print("\nInvoice Date : "+rs1.getString("date")+"\nInvoice ID :
                    // "+rs1.getInt("Bill_id")+"\nQuantity : "+rs1.getInt("Quantity"));
                    // System.out.println();
                }
                System.out.println("--------------------------------------------------");

            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    // Function for Display the total number of Products Sold on that Day
    // And also for Display the Customer who bought products and items bought on
    // that day
    public void SalesonParticularDate(String Date) {
        try {

            conn = DriverManager.getConnection(url, user, pass);

            // Sql Query for Fetching the product Details and Quantity Sold on that
            // Particular Day
            String sql = "SELECT BI.Prod_id,P.Prod_Name,SUM(BI.Quantity)AS Quant FROM billing AS B INNER JOIN billingitems AS BI ON B.bill_id=BI.Bill_id "
                    +
                    "INNER JOIN products AS P on BI.Prod_id=P.Prod_id where B.date=? GROUP BY BI.Prod_id ORDER BY P.Prod_id";

            // Ouery for fetching the customer who bought items in that particular Day
            // it als0 checks the Data of Same customer bought multiple Invoice in
            // Particular Day
            String sql1 = "SELECT B.Bill_id,B.Cust_id,C.Cust_Name,C.Mobile_No FROM BILLING AS B INNER JOIN customer AS C ON B.Cust_id=C.Cust_id WHERE date=? ORDER BY Cust_id";

            st = conn.prepareStatement(sql);
            st.setString(1, Date);
            rs = st.executeQuery();

            System.out.println("\n\n \tProduct Sales On That Day");
            System.out.println("--------------------------------------------------");

            System.out.printf("| %-10s | %-20s | %-10s |%n", "Product ID", "Product Name", "Quantity");
            while (rs.next()) {
                System.out.println("--------------------------------------------------");
                System.out.printf("| %-10s | %-20s | %-10s |%n", rs.getInt("Prod_id"), rs.getString("Prod_Name"),
                        rs.getInt("Quant"));
                // System.out.println("\nProduct Name : " + rs.getString("Prod_Name") +
                // "\nQuantity : " + rs.getInt("Quantity"));
            }
            System.out.println("--------------------------------------------------");

            System.out.println("\n\n \t\tCustomer Who Bought Products On That Day\n");
            st = conn.prepareStatement(sql1);
            st.setString(1, Date);
            rs = st.executeQuery();

            int bill_id = 0, customer_id = 0, prev_id = 0;

            while (rs.next()) {

                bill_id = rs.getInt("bill_id");
                customer_id = rs.getInt("Cust_id");

                // If Same Customer have multiple Invoice on that day ,then his details should
                // be printed once.
                if (prev_id != customer_id) {
                    prev_id = customer_id;
                    System.out.println("\nCustomer Name : " + rs.getString("Cust_Name") + "\nMobile_No : "
                            + rs.getInt("Mobile_No"));
                }
                Generatebill g = new Generatebill();
                // Here function call is done to display the items that are bought in that day
                // buy the customer.
                // Data is displayed using bill_id of that customer
                g.Billedproductsdisplay(bill_id);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
