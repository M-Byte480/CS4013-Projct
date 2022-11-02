package reservation;

import people.*;
import till.*;
import restaurant.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Invoice {

    private Reservation reservation;
    private Customer customer;
    private ArrayList<Product> products;
    private double total;
    private static int uniqueID;

    public Invoice(Reservation reservation, Customer customer) throws IOException {
        this.reservation = reservation;                     // collects details of reservation
        this.customer = customer;                               // contains details of the person who booked
        this.products = reservation.getTable().getProducts();   // gets an arrayList of all the products on the table
        this.total = products.getSum();
        uniqueID++;
        sendInvoice();
    }

    public static void setUniqueID(int uniqueID) {
        Invoice.uniqueID = uniqueID;
    }

    public static int getUniqueID() {
        return uniqueID;
    }

    public static void getLatestID() throws IOException {
        if(getUniqueID() == 0){
            setUniqueID(1);
            return;
        }
        Util invoiceReader = new Util(new File("/src/data/invoices.csv"));
        String[] allID = invoiceReader.getAllArray("id");
        setUniqueID(Integer.parseInt(allID[allID.length - 1]));
        invoiceReader.close();
    }

    public void sendInvoice() throws IOException {
        Util writeToLog = new Util(new File("src/data/log.csv"));           // Create a writer to logs
        Util writeToInvoices = new Util(new File("src/data/invoices.csv")); // Create a writer to invoices

        String contact = null;                                                      // Check preferences of contact
        if(customer.getPhoneNumber() == null){
            contact = customer.getAddress();
        }else{
            contact = customer.getPhoneNumber();
        }
        // Write to the files
        writeToLog.addDataToFile(new String[] {Util.getTimeNow(), reservation.getStaff(), "Sent away invoice"});
        writeToInvoices.addDataToFile(customer.getName(), customer.getAddress(), contact, reservation.getTime(),reservation.getProducts(),total);

        // End the utils
        writeToInvoices.close();
        writeToLog.close();
    }

    public String format(){
        ArrayList<LineItem> items = new ArrayList<>();
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(customer.getAddress()).append("\n").append(customer).append("\n");
        for (LineItem l : LineItem.convert(products)) {
            toReturn.append(l.toString()).append("=".repeat(48));
        }
        return toReturn.toString();
    }
}
