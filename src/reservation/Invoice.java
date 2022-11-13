package reservation;

import people.*;
import till.*;
import restaurant.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Invoice {
    private Reservation reservation;
    private Customer customer;
    private ArrayList<Product> products;
    private double total;
    private int id;
    private static int uniqueID;

    /**
     * Creates an invoice Object
     *
     * @param reservation
     */
    public Invoice(Reservation reservation) {
        this.reservation = reservation;                     // collects details of reservation
        this.customer = reservation.getCust();                               // contains details of the person who booked
        this.products = reservation.getTable().getProducts();   // gets an arrayList of all the products on the table
        this.total = reservation.getTable().getTotal();
        this.id = uniqueID++;
        try {
            sendInvoice();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Invoice(Reservation reservation, int id) {
        this.reservation = reservation;                     // collects details of reservation
        this.customer = reservation.getCust();                               // contains details of the person who booked
        this.products = reservation.getTable().getProducts();   // gets an arrayList of all the products on the table
        this.total = reservation.getTable().getTotal();
        this.id = id;
        try {
            sendInvoice();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the uniqueID state
     * @param uniqueID
     */
    public static void setUniqueID(int uniqueID) {
        Invoice.uniqueID = uniqueID;
    }

    /**
     * Gets the ID state
     * @return uniqueID current state
     */
    public static int getUniqueID() {
        return uniqueID;
    }

    public double getTotal() {
        return total;
    }

    /**
     * Gets the latest uniqueID in the file and then it sets the uniqueID state from last usage
     * @throws IOException
     */
    public static void getLatestID() throws IOException {
        if(getUniqueID() == 0){
            setUniqueID(1);
            return;
        }
        CSVReader invoiceReader = new CSVReader(new File("/src/data/invoices.csv"));
        String[] allID = invoiceReader.getAllArray("id");
        setUniqueID(Integer.parseInt(allID[allID.length - 1]));
        invoiceReader.close();
    }

    public String toString() {
        StringBuilder prodString = new StringBuilder();
        for (Product product : products) {
            prodString.append(product.getName()).append(";");
        }
        prodString.deleteCharAt(prodString.length()-1);

        return String.format("%s,%s,%s,%s,%s", 
            customer.toString().replace(",", ";"),
            reservation.toString().replace(",", ";"),
            prodString,
            total,
            id
        );
    }
}
