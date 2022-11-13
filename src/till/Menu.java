import restaurant.Restaurant;
import till.Product;

import java.util.ArrayList;
import java.util.Scanner;
public class Menu {
    private Scanner in;
    private static Restaurant restaurant;

    public void run() {
        boolean more = true;

        while (more) {

            System.out.println("S)how Products, A)dd A Product To Order, R)emove A Product From Order, F)inish, Q)uit");
            String command = in.nextLine().toUpperCase();
            if (command.equals("S")) {
                System.out.println("Products Available : ");
                showProducts();

            } else if (command.equals("A")) {
                System.out.printf("Add A Product To Order : ");
                addProduct();

            } else if (command.equals("R")) {
                System.out.printf("Remove A Product From Order : ");
                removeProduct();

            } else if (command.equals("F")) {

            } else if (command.equals("Q")) {

            }
        }
    }

    private String  showProducts() {
       return restaurant.getProducts().toString();
    }

    private void addProduct(){

    }

}