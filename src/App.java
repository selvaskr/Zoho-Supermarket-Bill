import java.util.Scanner;

public class App {


    public static void main(String[] args) throws Exception {

        
        Scanner sc=new Scanner(System.in);
        boolean flag=true;
        BillingItems b;
        Generatebill g;
        Customerdetails c;
        Productlisting p;
        System.out.print("\n\n\t\t\t\t SUPERMARKET BILLING \n");
        while(flag){
            System.out.println("\n 1 . Billing Section \n 2 . Display Customer Details \n 3 . Available Product Listing\n 4 . Check Product Sales\n 5 . Sales On Particular Date \n 6 . Update Product to DB \n 7 . Exit");
            System.out.print("\nEnter Your Choice : ");
            int choice =sc.nextInt();
            System.out.println();
            switch(choice){
                case 1:
                    g= new Generatebill();
                    c=new Customerdetails();
                    int C_id=c.newCustomerorAlready();
                    g.buyingitems(C_id);
                    break;
                case 2:
                    System.out.print("Enter Customer Phone Number : ");
                    int number=sc.nextInt();
                    c=new Customerdetails();
                    c.customer(number);
                    break;
                case 3:
                    p=new Productlisting();
                    p.productitems();
                    break;
                case 4:
                    b=new BillingItems();
                    System.out.print("Enter the Product id : ");
                    int id=sc.nextInt();
                    b.Salesofproduct(id);
                    break;
                case 5:
                    b=new BillingItems();
                    System.out.print("Enter the Date (YYYY-MM-DD): ");
                    String date=sc.next();
                    b.SalesonParticularDate(date);
                    break;

                case 6:
                    System.out.println("\nOnly Admins Can Update Products\n");
                    System.out.print("Enter the Admin User Name : ");
                    String name=sc.next();
                    System.out.print("Enter Admin Password : ");
                    String password =sc.next();
                    c = new Customerdetails();
                    if(c.checkadmin(name, password)){
                            p=new Productlisting();
                            System.out.print("\nNeed to Add Product [1] Or Update Quantity or Price [2] : ");
                            int adminchoice=sc.nextInt();

                            if(adminchoice==1){
                                System.out.print("\nEnter the Number to Products to be added : ");
                                int N=sc.nextInt();
                                while(N>0){
                                    System.out.print("\nProduct Name : ");
                                    String Name =sc.next();
                                    int price,Quantity;
                                    System.out.print("Product Price : ");
                                    price=sc.nextInt();
                                    System.out.print("Product Quantity : ");
                                    Quantity=sc.nextInt();
                                    p.addproduct(Name,price,Quantity);
                                    N--;
                                }
                            }
                            else{
                                System.out.print("\nEnter the Product ID to Update : ");
                                int prodid=sc.nextInt();
                                System.out.print("\nEnter the Product Price to Update : ");
                                int price=sc.nextInt();
                                System.out.print("Enter the Product Quantity to Update : ");
                                int Quantity = sc.nextInt();
                                p.UpdatepriceandQuantity(Quantity,price,prodid);

                            }
                    }
                    break;
                case 7:
                    flag=false;
                    break;

            }
        }
        sc.close();
    }
}