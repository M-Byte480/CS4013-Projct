package reservation;

import people.*;
import till.*;
import restaurant.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Invoice {
    private final Reservation reservation;
    private final Customer customer;
    private final ArrayList<Product> products;
    private final double total;
    private final int id;
    private static int uniqueID;

    /**
     * Creates an invoice Object
     *
     */
    public Invoice(Reservation reservation) {
        this.reservation = reservation;                         // collects details of reservation
        this.customer = reservation.getCust();                  // contains details of the person who booked
        this.products = reservation.getTable().getProducts();   // gets an arrayList of all the products on the table
        this.total = reservation.getTable().getTotal();
        this.id = uniqueID++;
    }


    public Invoice(Reservation reservation, int id) {
        this.reservation = reservation;                         // collects details of reservation
        this.customer = reservation.getCust();                  // contains details of the person who booked
        this.products = reservation.getTable().getProducts();   // gets an arrayList of all the products on the table
        this.total = reservation.getTable().getTotal();
        this.id = id;
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
     * Gets the latest uniqueID in the file, and then it sets the uniqueID state from last usage
     */
    public static void getLatestID() {
        if(getUniqueID() == 0){
            setUniqueID(1);
            return;
        }

        setUniqueID(Utils.getInvoiceLatestID());
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

    public String toFormat() {
        HashMap<Product, Integer> occurrences = new HashMap<>();
        Integer count = 0;
        StringBuilder formattedInvoice = new StringBuilder();

        formattedInvoice.append("Invoice Number: %10s\n"); // ID
        formattedInvoice.append("%26s\n");   // Name
        formattedInvoice.append("=".repeat(26));

        for (Product p : products) {
            count = occurrences.get(p);
            if (count == null) {
                occurrences.put(p, 1);
            } else {
                occurrences.replace(p, count + 1);
            }
        }

        double total = 0;
        for (HashMap.Entry<Product, Integer> item :
                occurrences.entrySet()) {
            formattedInvoice.append(Invoice.toLineItem(item.getKey().getName(), item.getValue(), item.getKey().getCost(), item.getValue() * item.getKey().getCost()));
            total += item.getValue() * item.getKey().getCost();
        }

        formattedInvoice.append(String.format("%26f\n", total));
        return String.format(formattedInvoice.toString(), this.id, this.customer.getName());
    }

    public static String toLineItem(String name, int quantity, double unitPrice, double total){
        return String.format("%7S%7S%7S€%5S\n" + "=".repeat(26), name, quantity, unitPrice, total);
    }
}
