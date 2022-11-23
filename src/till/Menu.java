package till;

import restaurant.Restaurant;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner in;
    private Restaurant restaurant;

    public void run(Restaurant restaurant) {

        while (true) {

            // Add Option to Quit or Select table
            System.out.println("Select A Table : ");
            Table t = (Table) getChoice(restaurant.getTables().toArray());

            while (true) {
                System.out.println("S)how Products, O)rder Details  A)dd A Product To Order  C)onfrim Order   R)emove A Product From Order, F)inish, Q)uit");
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

                } else if (command.equals("C")) {
                    System.out.printf("Order Confirmed");
                    restaurant.addToOrder(t.getProducts());


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
        Product p = (Product) getChoice(t.getProducts().toArray());
        t.addProducts(p);
    }

    private void removeProduct(Table t) {
        Product p = (Product) getChoice(t.getProducts().toArray());
        t.removeProduct(p);
    }

    private void removeAllProducts(Table t) {
        t.clearFood();
    }

    private Object getChoice(Object[] choices) {
        if (choices.length == 0) return null;
        while (true) {
            char c = 'A';
            for (Object choice : choices) {
                System.out.println(c + ") " + choice);
                c++;
            }
            String input = in.nextLine();
            int n = input.toUpperCase().charAt(0) - 'A';
            if (0 <= n && n < choices.length)
                return choices[n];
        }
    }
}