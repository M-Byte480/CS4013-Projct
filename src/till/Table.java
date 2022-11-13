package till;

import reservation.Reservation;

import java.util.ArrayList;

public class Table {
    private int tableNumber;
    private int seats;
    private Reservation booking;
    private ArrayList<Product> productsOnTable;

    public Table(int tableNumber, int seats) {
        this.tableNumber = tableNumber;
        this.seats = seats;
    }

    public int getTableNumber() {
        return tableNumber;
    }
    
    public String getStaff() {
        return getStaff();
    }

    public Reservation getBooking() {
        return booking;
    }
    public void setBooking(Reservation booking) {
        this.booking = booking;
    }

    public ArrayList<Product> getProducts() {
        return productsOnTable;
    }
    public void addProduct(Product pick) {
        productsOnTable.add(pick);
    }
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

    public String toString() {
        return String.format("%s,%s", tableNumber, seats);
    }
}
