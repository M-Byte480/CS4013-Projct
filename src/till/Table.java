package till;

import restaurant.*;
import reservation.*;

import java.util.ArrayList;
import java.io.*;

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

    //closing table
    //calculate price
    public void billTable(char type) throws IOException {
        switch (type) {
            case 1:
                type = 'C';
                closeTable();
                sendLog("Paid with Credit");
                Invoice.sendInvoice();
                till = new Till().sale(); //invoice amount


                break;


            case 2:
                type = 'D';
                closeTable();
                sendLog("Paid with Debit");
                Invoice.sendInvoice();
                till = new Till().sale(); //invoice amount

                break;


            case 3:
                type = 'X';
                closeTable();
                Invoice.sendInvoice();
                sendLog("Paid with cash");
                till = new Till().sale(); //invoice amount
                till = new Till().cashPaid(); //invoice amount + amount paid in cash

                break;
        }
    }

    private void sendLog(String whatHappened) throws IOException {
        Util writeToLog = new Util(new File("src/data/log.csv"));

        writeToLog.addDataToFile(whatHappened);

        writeToLog.close();
    }

    public void closeTable() {


    }

    //closing/deleting booking
    //check if table is vacant
    public void deleteTable(Table table) {
        productsOnTable.remove(table);

    }
}
