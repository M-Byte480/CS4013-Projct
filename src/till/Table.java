package till;

import reservation.*;

import java.util.ArrayList;

public class Table {
    private int id;
    private int seats;
    private Reservation booking;

    private ArrayList<Product> productsOnTable;

    Table(int id, int seats, Reservation booking) {
        this.id = id;
        this.seats = seats;
        this.booking = booking;
    }

    //adding product to "order"
    public void addProduct(Product pick) {

        productsOnTable.add(pick);
    }

    //deleting product from "order"
    public void deleteProduct(Product delete) {

        productsOnTable.add(delete);
    }

    //closing table
    //calculate price
    public void billTable(char type) {
        switch (type) {


            case 1:
                type = 'C';
                closeTable();
                Till.


              break;


            case 2:
                type = 'D';
                closeTable();


                break;


            case 3:
                type = 'X';
                closeTable();


                break;
        }
    }


    public void closeTable() {


    }

    //closing/deleting booking
    //check if table is vacant
    public void deleteTable(Table table) {

    }
}
