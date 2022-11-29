package till;

import people.Customer;
import reservation.Invoice;
import reservation.Reservation;
import restaurant.CSVReader;
import restaurant.Restaurant;
import restaurant.Utils;
import restaurant.Yum;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Menu {
    private Scanner in;
    private Restaurant restaurant;
    private Reservation reservation;

    /**
     * It runs the menu system for the staff for to interact with, including;
     * Taking orders of a customer and storing it on the table.
     * @param yum Yum
     * @param restaurant Restaurant we take the order in
     * @param in the System.in()
     */
    public void run(Yum yum, Restaurant restaurant, Scanner in) {
        this.restaurant = restaurant;
        this.in = in;
        boolean notFinished = true;
        while (notFinished) {
            System.out.println("Select A Table : ");
            Table table = Utils.getChoice(restaurant.getFreeTablesBetweenTime(LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
            if (table == null) {
                break;
            }
            System.out.println("Enter Customer ID: ");
            String custID = in.nextLine();
            if (yum.getPerson(custID) == null) {
                System.out.println("Invalid Customer ID!");
                break;
            }
            reservation = new Reservation(table, LocalDateTime.now(), LocalDateTime.now().plusHours(1), custID);

            while (true) {
                System.out.println("S)how Products    O)rder Details    A)dd A Product To Order    " +
                        "R)emove A Product From Order    F)inish    Q)uit");
                String command = in.nextLine().toUpperCase();

                if (command.equals("S")) {
                    System.out.println("Products Available: ");
                    showProducts();

                } else if (command.equals("O")) {
                    System.out.println("Products In Order: ");
                    showProductsOnTable(table);

                } else if (command.equals("A")) {
                    System.out.println("Add A Product To Order: ");
                    addProduct(table);

                } else if (command.equals("R")) {
                    System.out.println("Remove A Product From Order: ");
                    removeProduct(table);

                } else if (command.equals("F")) {
                    ((Customer) yum.getPerson(custID)).increaseLoyalty();
                    restaurant.addInvoice(new Invoice(reservation));
                    finishPayment(table);
                    notFinished = false;
                    break;

                } else if (command.equals("Q")) {
                    restaurant.addToOrder(table.getProducts());
                    break;
                }
            }
        }
    }

    /**
     * Prints out the products that are on the table passed in as a parameter
     * @param table
     */
    private void showProductsOnTable(Table table) {
        System.out.println(table.getProducts());
    }

    /**
     * Shows products that are available in the restaurant.
     */
    private void showProducts() {
        System.out.println(restaurant.getProducts().toString());
    }

    /**
     * Adds a chosen product from a list to the table of the user
     * @param table
     */
    private void addProduct(Table table) {
        Product p = Utils.getChoice(restaurant.getProducts(), in);
        table.addProducts(p);
    }

    /**
     * Removes a chosen product from a list to the table of the user
     * @param table
     */
    private void removeProduct(Table table) {
        Product p = Utils.getChoice(table.getProducts(), in);
        table.removeProduct(p);
    }

    /**
     * When payment is finished, a new File is created of the restaurants name and adds the invoice created
     * into the CSV.
     * @param table
     */
    private void finishPayment(Table table){
        CSVReader invoiceFile = new CSVReader(new File("src\\data\\" + restaurant.getName() + "\\invoices.csv"), false);
        invoiceFile.appendToFile(new Invoice(reservation).toString());

        table.clearFood();
    }
}