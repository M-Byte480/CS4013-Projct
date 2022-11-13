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

        setUniqueID(Integer.parseInt(invoiceReader.getValues().get(invoiceReader.getValues().size() - 2)[invoiceReader.getValues().get(0).length - 1]));
    }
    public String[] customerDetailsToStringArr () {
        // name, phoneNumber, timeOfBooking, TimeOfSending, products
        ArrayList<String> invoice = new ArrayList<>();
        invoice.add(customer.getName());
        invoice.add(customer.getPhoneNumber());
        invoice.add(reservation.getTime().toString());
        invoice.add(CSVReader.getTimeNow());
        invoice.add(reservation.getProducts().toString());
        String[] custDetails = new String[4];
        for (int i = 0; i < custDetails.length; i++) {
            custDetails[i] = invoice.get(i);
        }
        return custDetails;
    }


    /**
     * Formats the CSV file into a readable state in terminal
     * @return
     */
    public String format(){
        ArrayList<LineItem> items = new ArrayList<>();
        StringBuilder toReturn = new StringBuilder();
        toReturn.append(customer.getEmail()).append("\n")
                .append(customer).append("\n");

        for (LineItem l : Table.convertToLineItems(products)) {
            toReturn.append(l.toString()).append("=".repeat(48));
        }

        return toReturn.toString();
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
