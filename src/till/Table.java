package till;

import restaurant.*;
import reservation.*;

import java.util.ArrayList;
import java.io.*;
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

    public void setBooking(Table table) {
        booking.setStatus = true;
    }

    //adding product to "order"
    public void addProduct(Product pick) {

        productsOnTable.add(pick);

    }

    //deleting product from "order"
    public void deleteProduct(Product delete) {

        productsOnTable.add(delete);
    }


    public static ArrayList<LineItem> convertToLineItems(ArrayList<Product> products){
        ArrayList<LineItem> items = new ArrayList<>();
        HashMap<Product, Integer> occurences = new HashMap<>();
        Integer count = 0;
        for (Product p : products) {
            count = occurences.get(p.getName());
            if(count == null){
                occurences.put(p.getName(), 1);
            }else{
                occurences.replace(p.name, count + 1);
            }
        }

        for (HashMap.Entry<Product, Integer> item :
                occurences.entrySet()) {
            items.add(new LineItem(item.getKey().getName(), item.getValue(), item.getKey().getCost()));
        }

        return items;
    }



    public void closeTable() {


    }

    //closing/deleting booking
    //check if table is vacant
    public void deleteTable(Table table) {
        productsOnTable.remove(table);

    }

    public ArrayList<Product> getProducts() {
        return productsOnTable;
    }
}
