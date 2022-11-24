package till;

import java.util.ArrayList;

public class Table {
    private int tableNumber;
    private int seats;
    private ArrayList<Product> products;

    /**
     * Constructor creates a Table object using a specified table number and the amount of seats available
     * @param tableNumber
     * @param seats
     */
    public Table(int tableNumber, int seats) {
        this.tableNumber = tableNumber;
        this.seats = seats;
        this.products = new ArrayList<>();
    }

    /**
     * Method returns the table number of the table being checked
     * @return the number of the corresponding table
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     * method returns the number of seats the table being checked has
     * @return number of seats the table has
     */
    public int getSeats() {
        return seats;
    }

    /**
     * Method sets the number of seats available for a specified table
     * @param seats
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * Gets a list of all the products currently added to the products ArrayList
     * @return Returns the current status of products as an ArrayList of object type Product
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * Adds an object of type Product denoted p, to the products ArrayList
     * @param p
     */
    public void addProducts(Product p) {
        products.add(p);
    }

    /**
     * Passes in some object of type Product denoted p, and removes it from the products ArrayList
     * @param p
     */
    public void removeProduct(Product p) {
        products.remove(p);
    }

    /**
     * Clears the products ArrayList by re-initializing it as a new ArrayList i,e empty list
     */
    public void clearFood() {
        products = new ArrayList<>();
    }

    /**
     * Passes in the products ArrayList, loops through each product and gets the associated cost
     * @return total price of all the products added together
     */
	public double getTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getCost();   
        }
		return total;
	}

    /**
     * Prints out a string with the corresponding table number and seats associated with a certain table
     * @return Formatted String of a table's number and it's amount of seats
     */
    public String toString() {
        return String.format("%s,%s", tableNumber, seats);
    }
}

