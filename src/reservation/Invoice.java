package reservation;

import till.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Invoice {
    private Reservation reservation = null;
    private String customerID= null; 
    private ArrayList<Product> products = null;
    private double total;
    private int id;
    private static int uniqueID;
    private LocalDateTime date = null;
    /**
     * Creates an Invoice object with a specified reservation
     * @param reservation
     */
    public Invoice(Reservation reservation) {
        this.reservation = reservation;                         // collects details of reservation
        this.customerID = reservation.getCustomerID();                  // contains details of the person who booked
        this.products = reservation.getTable().getProducts();   // gets an arrayList of all the products on the table
        this.total = reservation.getTable().getTotal();
        this.id = uniqueID++;
    }

    public Invoice(LocalDateTime date, double total){
        this.total = total;
        this.date = date;
    }

    /**
     * 
     * @return
     */
    public LocalDateTime getDate() {
        return date;
    }


    /**
     * Gets the total price associated with an Invoice object
     * @return invoice total as a double
     */
    public double getTotal() {
        return total;
    }


    /**
     * Gets the id contained in a Reservation object
     * @return id
     */
    public int getId() {
        return id;
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
}
