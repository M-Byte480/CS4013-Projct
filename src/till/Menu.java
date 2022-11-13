package till;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<Product> products;

    private Scanner in;

    private Till till1 = new Till();

    public Menu() {
        this.in = new Scanner(System.in);
    }

    public void run(Table t) {
        boolean more = true;

        while (more) {
//javaFX will overdo this
            System.out.println("S : Show Products, A : Add A Product To Order, R : Remove A Product From Order, F : Finish, Q : Close Menu");
            String command = in.nextLine().toUpperCase();
            if (command.equals("S")) {
                for (Product p : Product.productsOfRestaurant)
                    System.out.println(p);


                //show all products available
                //select a product using a input
                //add product to table
            } else if (command.equals("A")) {
                t.addProduct(getChoice(products.toArray()));
                System.out.println("Added : " + getChoice(products.toArray()));
                //show all products on table
                //select a product using input
                //remove product from table

            } else if (command.equals("R")) {
                t.deleteProduct(getChoice(productOnTable.toArray()));
                System.out.println("Removed : " + getChoice(productOnTable.toArray()));

                //Pass products added to be made

            } else if (command.equals("F")) {
                productOnTable.removeAll(Product);
                t.bookingStatus();
                t.sendInvoice();
                t.closeTable();
                //remove all from table
                //remove booking
            } else if (command.equals("Q")) {
                productOnTable.set(null);
                Table.bookingStatus();


            }
    }


    // This code was taken from Micheal
    private Object getChoice(Object[] choices) {


        }
    }
}