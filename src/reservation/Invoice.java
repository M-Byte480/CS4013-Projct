package reservation;

import till.*;
import restaurant.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Invoice {
    private Reservation reservation = null;
    private String customerID = null;
    private ArrayList<Product> products = null;
    private double total;
    private int id;
    private static int uniqueID;
    private LocalDateTime date = null;

    /**
     * Creates an Invoice object with a specified reservation
     * This objects key details will be stored in the CSV file
     * @param reservation of type Reservation
     */
    public Invoice(Reservation reservation) {
        this.reservation = reservation;
        this.customerID = reservation.getCustomerID();
        this.products = reservation.getTable().getProducts();
        this.total = reservation.getTable().getTotal();
        this.id = uniqueID++;
    }

    /**
     * Construct an invoice from the CSV files, according to the necessary fields
     * @param date
     * @param total
     */
    public Invoice(LocalDateTime date, double total){
        this.total = total;
        this.date = date;
    }

    /**
     * Returns the date and time the invoice was created.
     * @return LocalDateTime
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the uniqueID state
     * @param uniqueID
     */
    public static void setUniqueID(int uniqueID) {
        Invoice.uniqueID = uniqueID;
    }

    /**
     * Gets the unique ID state
     * @return uniqueID current state
     */
    public static int getUniqueID() {
        return uniqueID;
    }

    /**
     * Gets the total price associated with an Invoice object
     * @return invoice total as a double
     */
    public double getTotal() {
        return total;
    }

    /**
     * Gets the instance of Reservation created using the constructors above
     * @return instance of Reservation
     */
    public Reservation getReservation() {
        return reservation;
    }


    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * Gets the id contained in a Reservation object
     * @return id
     */
    public int getId() {
        return id;
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

    /**
     * Prints out a string of all the products, adds a ";" after each one and
     * removes the ";" from the last product
     * @return a string with the time, duration, the customer's id and their total order
     */
    public String toString() {
        StringBuilder prodString = new StringBuilder();
        for (Product product : products) {
            prodString.append(product.getName()).append(";");
        }
        prodString.deleteCharAt(prodString.length()-1);

        return String.format("%s,%s,%s,%s,%.2f,%s",
            reservation.getTime().format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm")),
            reservation.getLength().format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm")),
            reservation.getCustomerID(),
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
            formattedInvoice.append(Invoice.toLineItem( item.getValue(), item.getKey().getCost(), item.getValue() * item.getKey().getCost()));
            total += item.getValue() * item.getKey().getCost();
        }

        formattedInvoice.append(String.format("%26f\n", total));
        return String.format(formattedInvoice.toString(), this.id, this.customerID);
    }

    public static String toLineItem(int quantity, double unitPrice, double total){
        return String.format("%7S%7S€%5S\n" + "=".repeat(26), quantity, unitPrice, total);
    }
}
