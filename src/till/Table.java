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

    //adding product to "order"
    public void addProduct(Product pick) {
        productsOnTable.add(pick);
    }

    //deleting product from "order"
    public void deleteProduct(Product delete) {
        productsOnTable.add(delete);
    }

    public double getTotal(){
        double sum = 0;
        for (Product p : productsOnTable) {
            sum += p.getCost();
        }
        return sum ;
    }

    public String getStaff() {
        return getStaff();          // uhh recursion
    }
    
    // Converts table to lineItem arrayList for invoices
    // Milan
    public static ArrayList<LineItem> convertToLineItems(ArrayList<Product> products) {
        ArrayList<LineItem> items = new ArrayList<>();
        HashMap<Product, Integer> occurences = new HashMap<>();
        Integer count = 0;
        for (Product p : products) {
            count = occurences.get(p);
            if (count == null) {
                occurences.put(p, 1);
            } else {
                occurences.replace(p, count + 1);
            }
        }

        for (HashMap.Entry<Product, Integer> item : occurences.entrySet()) {
            items.add(new LineItem(item.getKey().getName(), item.getValue(), item.getKey().getCost()));
        }
        return items;
    }

    public void closeTable() throws IOException {
        Invoice invoice = new Invoice(this.booking);
        invoice.sendInvoice();
        this.booking = null;
        this.productsOnTable = null;
    }

    //closing/deleting booking
    //check if table is vacant
    public void deleteTable(Table table) {
        productsOnTable.remove(table);          // cant remove table object from arraylist of products
    }

    public String returnForawrdSlaah() {
        for (Product p : productsOnTable) {
            St
            p.toString();                       // ummm what
        }
    }

    public void getMenu(Table t) throws IOException {
        Menu menu = new Menu();
        menu.run(t);
    }

    public ArrayList<Product> getProducts() {
        return productsOnTable;
    }

    public String toString() {
        return String.format("%s,%s", tableNumber, seats);
    }
}
