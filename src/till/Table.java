package till;

import reservation.Invoice;
import reservation.LineItem;
import reservation.Reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Table {
    private int tableNumber;
    private int seats;
    private Reservation booking;
    private ArrayList<Product> productsOnTable;
    private Till till;

    public Table(int tableNumber, int seats) {
        this.tableNumber = tableNumber;
        this.seats = seats;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getStaff() {
        return getStaff();
    }

    public Reservation getBooking() {
        return booking;
    }

    public void setBooking(Reservation booking) {
        this.booking = booking;
    }

    public ArrayList<Product> getProducts() {
        return productsOnTable;
    }

    public void addProduct(Product pick) {
        productsOnTable.add(pick);

    }

    //deleting product from "order"
    public void deleteProduct(Product delete) {
        productsOnTable.add(delete);
    }


    public double getTotal() {
        double sum = 0;
        for (Product p : productsOnTable) {
            sum += p.getCost();

        }
        return sum;
    }


    public void closeTable() {
        this.booking = null;

    }

    //closing/deleting booking
    //check if table is vacant
    public void deleteTable(Table table) {
        productsOnTable.remove(table);

    }


    public void closeTable() {
        Invoice invoice = new Invoice(this.booking);
        invoice.sendInvoice();
        this.booking = null;
        this.productsOnTable = null;
    }

    //closing/deleting booking
    //check if table is vacant
    public void deleteTable(Table table) {
        productsOnTable.remove(table);
    }

    public String returnForawrdSlash() {
        for (Product p : productsOnTable) {
            St
            p.toString();
        }
    }

    public void getMenu(Table t) {
        Menu menu = new Menu();
        menu.run(t);
    }

    public ArrayList<Product> getProducts() {
        return productsOnTable;
        public String toString () {
            return String.format("%s,%s", tableNumber, seats);
        }
    }


    public String toString() {
        return String.format("%s,%s", tableNumber, seats);
    }
}
