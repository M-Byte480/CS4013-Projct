package till;

import restaurant.Restaurant;
import till.Product;
import till.Table;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner in;
    private static Restaurant restaurant;


    public void run() {

        while (true) {

            System.out.println("Select A Table : ");
            Table t = (Table) getChoice(restaurant.getTables());


            while (true) {
                System.out.println("S)how Products, O)rder Details A)dd A Product To Order, R)emove A Product From Order, F)inish, Q)uit");
                String command = in.nextLine().toUpperCase();

                if (command.equals("S")) {
                    System.out.println("Products Available : ");
                    showProducts();

                } else if (command.equals("O")) {
                    System.out.printf("Products In Order : ");
                    showProductsOnTable(t);

                } else if (command.equals("A")) {
                    System.out.printf("Add A Product To Order : ");
                    addProduct(t);


                } else if (command.equals("R")) {
                    System.out.printf("Remove A Product From Order : ");
                    removeProduct(t);


                } else if (command.equals("F")) {
                    removeAllProducts(t);



                } else if (command.equals("Q")) {
                    break;

                }
            }
        }
    }


    private ArrayList<Product> showProductsOnTable(Table t) {
        return t.getProducts();
    }

    private String showProducts() {
        return restaurant.getProducts().toString();
    }


    private void addProduct(Table t) {
        Product p = (Product) getChoice(t.addProduct());
        t.addToTable(p);
    }

    private void removeProduct(Table t) {
        Product p = (Product) getChoice(t.removeProduct());
        t.removeFromTable(p);
    }

    private void removeAllProducts(Table t) {
        t.clearFood(t);
    }

    private Object getChoice(Product[] choices) {
        while (true) {
            char c = 'A';
            for (Object choice : choices) {
                System.out.println(c + ") " + choice);
                c++;
            }
        }
    }

    private Object getChoice(ArrayList<Table> choices) {
        while (true) {
            char c = 'A';
            for (Object choice : choices) {
                System.out.println(c + ") " + choice);
                c++;
            }
        }
    }
}