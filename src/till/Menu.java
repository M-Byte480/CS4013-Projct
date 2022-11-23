package till;

import restaurant.Restaurant;
import restaurant.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import reservation.Invoice;
import reservation.Reservation;

public class Menu {
    private Scanner in;
    private Restaurant restaurant;
    private Reservation reservation;

    public void run(Restaurant restaurant, Scanner in) {
        this.restaurant = restaurant;
        this.in = in;
        while (true) {

            // Add Option to Quit or Select table
            System.out.println("Select A Table : ");
            Table table = Utils.getChoice(restaurant.getFreeTablesBetweenTime(LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
            if (table == null) {
                System.out.println("No tables available!");
                break;
            }
            reservation = new Reservation(table, LocalDateTime.now(), LocalDateTime.now().plusHours(1));

            while (true) {
                System.out.println("S)how Products  O)rder Details  A)dd A Product To Order  R)emove A Product From Order  F)inish  Q)uit");
                String command = in.nextLine().toUpperCase();

                if (command.equals("S")) {
                    System.out.println("Products Available : ");
                    showProducts();

                } else if (command.equals("O")) {
                    System.out.printf("Products In Order : ");
                    showProductsOnTable(table);

                } else if (command.equals("A")) {
                    System.out.printf("Add A Product To Order : ");
                    addProduct(table);

                } else if (command.equals("R")) {
                    System.out.printf("Remove A Product From Order : ");
                    removeProduct(table);

                } else if (command.equals("F")) {
                    restaurant.addInvoice(new Invoice(reservation));
                    removeAllProducts(table);

                } else if (command.equals("Q")) {
                    restaurant.addToOrder(table.getProducts());
                    break;
                }
            }
        }
    }

    private ArrayList<Product> showProductsOnTable(Table table) {
        return table.getProducts();
    }

    private String showProducts() {
        return restaurant.getProducts().toString();
    }

    private void addProduct(Table table) {
        Product p = Utils.getChoice(restaurant.getProducts(), in);
        table.addProducts(p);
    }

    private void removeProduct(Table table) {
        Product p = Utils.getChoice(table.getProducts(), in);
        table.removeProduct(p);
    }

    private void removeAllProducts(Table table) {
        table.clearFood();
    }
}