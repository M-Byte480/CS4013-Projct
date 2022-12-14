package restaurant;

import people.Customer;
import reservation.Invoice;
import reservation.Reservation;
import till.Product;
import till.Table;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Restaurant {
    private String name;
    private ArrayList<Reservation> reservations;
    private ArrayList<Table> tables;
    private ArrayList<Product> products;
    private ArrayList<ArrayList<Product>> orders;
    private ArrayList<Invoice> invoices;

    /**
     * Constructor to create a new Restaurant Object
     * @param name of the restaurant
     */
    public Restaurant(String name) {
        this.name = name;
        this.reservations = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.products = new ArrayList<>();
        this.invoices = new ArrayList<>();
        this.orders = new ArrayList<>();
        Utils.makeCSVFiles(name);
    }

    /**
     * Constructor to create a new Restaurant Object
     * @param name of restaurant
     * @param reservations within the restaurant
     * @param tables within the restaurant
     * @param products within the restaurant
     * @param invoices within the restaurant
     */
    public Restaurant(String name, ArrayList<Reservation> reservations, ArrayList<Table> tables, ArrayList<Product> products,
        ArrayList<Invoice> invoices) {
        this.name = name;
        this.reservations = reservations;
        this.tables = tables;
        this.products = products;
        this.invoices = invoices;
        this.orders = new ArrayList<>();
    }

    /**
     * @param p added to orders
     */
    public void addToOrder(ArrayList<Product> p){
            orders.add(p);
    }

    /**
     * @param p removed from orders
     */
    public void removeFromOrder(ArrayList<Product> p){
        orders.remove(p);
    }

    /**
     * @return orders (VArrayList<ArrayList<Product>>) which is each order in the restaurant
     */
    public ArrayList<ArrayList<Product>> getOrders(){
        return orders;
    }


    /**
     * @return  name of Restaurant
     */
    public String getName() {
        return name;
    }

    /**
     * @return reservations (Each reservation within the Restaurant)
     */
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    /**
     * @param reservation which is trying to be added to reservations(ArrayList<Reservation>)
     * If reservation is before this exact moment
     * @return false
     * If reservation overlaps with another reservation
     * @return false;
     *
     * If neither of these criteria are met
     * true;
     */
    public boolean addReservation(Reservation reservation) {
        if (reservation.getTime().isBefore(LocalDateTime.now())) return false;
        for (Reservation res : reservations) {
            if (res.overlaps(reservation)) return false;
        }

        reservations.add(reservation);
        return true;
    }

    /**
     * @param reservation which is being removed.
     * Removes selected Reservation from reservations(ArrayLIst<Reservation>)
     */
    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    /**
     * @return products (ArrayList<Product>)
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * @param prod
     */
    public void addProduct(Product prod) {
        products.add(prod);
    }

    /**
     * Removes the product passed in as a parameter from the ArrayList products
     * @param prod
     */
    public void removeProduct(Product prod) {
        products.remove(prod);
    }

    /**
     * Returns a list of the current tables in the tables ArrayList
     * @return An ArrayList of object type Table
     */
    public ArrayList<Table> getTables() {
        return tables;
    }

    /**
     * Adds a table to restaurant array.
     * @param table
     */
    public void addTable(Table table) {
        tables.add(table);
    }

    /**
     * Removes the table passed in as an argument from the tables ArrayList
     * @param table
     */
    public void removeTable(Table table) {
        // make sure table doesn't have a reservation
        tables.remove(table);
    }

    /**
     * Adds an invoice to the invoice ArrayList within the Restaurant class
     * @param invoice
     */
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    /**
     * Returns an arrayList of tables that are within the time fram and of atleast a certain size.
     * @param size
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public ArrayList<Table> getFreeTablesOfSizeBetweenTime(int size, LocalDateTime timeStart, LocalDateTime timeEnd) {
        ArrayList<Table> freeTables = getFreeTablesBetweenTime(timeStart, timeEnd);
        ArrayList<Table> freeTablesOfSize = new ArrayList<>();
        for (Table table : freeTables) {
            if (table.getSeats() >= size) freeTablesOfSize.add(table);
        }
        return freeTablesOfSize;
    }

    /**
     * Returns an arrayList of tables that are free between a time frame.
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public ArrayList<Table> getFreeTablesBetweenTime(LocalDateTime timeStart, LocalDateTime timeEnd) {
        ArrayList<Table> bookedTables = getTablesBookedBetweenTime(timeStart, timeEnd);
        ArrayList<Table> allTables = new ArrayList<>(tables);
        allTables.removeAll(bookedTables);
        return allTables;
    }

    /**
     * Returns an arrayList of tables that are booked between the time frame.
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public ArrayList<Table> getTablesBookedBetweenTime(LocalDateTime timeStart, LocalDateTime timeEnd) {
        ArrayList<Table> tempTables = new ArrayList<>();
        for (Reservation res : reservations) {
            if ((res.getTime().isAfter(timeStart)) && (res.getTime().isBefore(timeEnd))) continue;
            else if ((res.getLength().isAfter(timeStart)) && (res.getLength().isBefore(timeEnd))) continue;
            else if ((res.getTime().isBefore(timeStart)) && (res.getLength().isAfter(timeEnd))) continue;
            tempTables.add(res.getTable());
        }
        return tempTables;
    }

    /**
     * Gets the profit made between the time frame.
     * @param timeStart
     * @param timeEnd
     * @return
     */
    public double getProfitBetweenTime(LocalDateTime timeStart, LocalDateTime timeEnd) {
        double profit = 0;
        for (Invoice invoice : invoices) {
            if ((invoice.getDate().isAfter(timeStart)) && (invoice.getDate().isBefore(timeEnd))) continue;
            profit += invoice.getTotal();
        }
        return profit;
    }

    /**
     * Gets all the reservations booked for the given Customer
     * @param cust Customer
     * @return  ArrayList<Reservation>
     */
    public ArrayList<Reservation> getReservationsForCustomer(Customer cust) {
        ArrayList<Reservation> tempRes = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomerID().equals(cust.getId()))
                tempRes.add(reservation);
        }
        return tempRes;
    }

    /**
     * Saves the state of the Restaurant to its folder of CSVs
     */
    public void save() {
        CSVReader resFile = new CSVReader(new File("src\\data\\" + name + "\\reservations.csv"), false);
        CSVReader tablesFile = new CSVReader(new File("src\\data\\" + name + "\\tables.csv"), false);
        CSVReader productsFile = new CSVReader(new File("src\\data\\" + name + "\\products.csv"), false);


        reservations.forEach(res -> resFile.addDataToSystem(res.toString()));
        tables.forEach(table -> tablesFile.addDataToSystem(table.toString()));
        products.forEach(prod -> productsFile.addDataToSystem(prod.toString()));


        resFile.saveToCSV();
        tablesFile.saveToCSV();
        productsFile.saveToCSV();

    }

    /**
     * Returns the name of the Restaurant
     * @return name of the Restaurant
     */
    public String toString() {
        return name;
    }
}
