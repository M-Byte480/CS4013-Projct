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


    public void run(Menu menu)

            throws IOException {
        boolean more = true;

        while (more) {
//javaFX will overdo this
            System.out.println("S : Show Products, A : Add A Product To Menu, R : Remove A Product, F : Finish, Q : GTFO");
            String command = in.nextLine().toUpperCase();
            if (command.equals("S")) {
                for (Product p : Product.productsOfRestaurant)
                    System.out.println(p);


                //show all products available
                //select a product using a input
                //add product to table
            } else if (command.equals("A")) {
                Table.addProduct(getChoice(products.toArray()));
                System.out.println("Added : " + getChoice(products.toArray()));
                //show all products on table
                //select a product using input
                //remove product from table

            } else if (command.equals("R")) {
                Table.deleteProduct(getChoice(productOnTable.toArray()));
                System.out.println("Removed : " + getChoice(productOnTable.toArray()));

                //Pass products added to be made

            } else if (command.equals("F")) {


                //remove all from table
                //remove booking
            } else if (command.equals("Q")) {
                productOnTable.removeAll(Product);
                Table.bookingStatus();

            }
        }

    }


        }
    }
}




