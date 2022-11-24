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
    private double profit;
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
     * @return  nameof Restaurant
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
     * If neither of these criterias are met
     * @return true;
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
     * @return proudcts (ArrayList<Product>)
     */
    public ArrayList<Product> getProducts() {
        return products;
    }
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    /**
     * @param prod
     */
    public void addProduct(Product prod) {
        products.add(prod);
    }

    /**
     * @param prod
     */
    public void removeProduct(Product prod) {
        products.remove(prod);
    }

    public ArrayList<Table> getTables() {
        return tables;
    }
    public void addTable(Table table) {
        tables.add(table);
    }
    public void removeTable(Table table) {
        // make sure table doesnt have a reservation
        tables.remove(table);
    }

    public double getProfit() {
        return profit;
    }
    public void addProfit(double profit) {
        this.profit += profit;
    }
    
    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }
    public void setInvoices(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }
    public void removeInvoice(Invoice invoice) {
        invoices.remove(invoice);
    }

    public ArrayList<Table> getFreeTablesOfSizeBetweenTime(int size, LocalDateTime timeStart, LocalDateTime timeEnd) {
        ArrayList<Table> freeTables = getFreeTablesBetweenTime(timeStart, timeEnd);
        ArrayList<Table> freeTablesOfSize = new ArrayList<>();
        for (Table table : freeTables) {
            if (table.getSeats() >= size) freeTablesOfSize.add(table);
        }
        return freeTablesOfSize;
    }

    public ArrayList<Table> getFreeTablesBetweenTime(LocalDateTime timeStart, LocalDateTime timeEnd) {
        ArrayList<Table> bookedTables = getTablesBookedBetweenTime(timeStart, timeEnd);
        ArrayList<Table> allTables = new ArrayList<>(tables);
        allTables.removeAll(bookedTables);
        return allTables;
    }

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

    public double getProfitBetweenTime(LocalDateTime timeStart, LocalDateTime timeEnd) {
        double profit = 0;
        for (Invoice invoice : invoices) {
            if ((invoice.getDate().isAfter(timeStart)) && (invoice.getDate().isBefore(timeEnd))) continue;
            profit += invoice.getTotal();
        }
        return profit;
    }
    
    public ArrayList<Reservation> getReservationsForCustomer(Customer cust) {
        ArrayList<Reservation> tempRes = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomerID().equals(cust.getId()))
                tempRes.add(reservation);
        }
        return tempRes;
    }

    public void save() {
        CSVReader resFile = new CSVReader(new File("src\\data\\" + name + "\\reservations.csv"), false);
        CSVReader tablesFile = new CSVReader(new File("src\\data\\" + name + "\\tables.csv"), false);
        CSVReader productsFile = new CSVReader(new File("src\\data\\" + name + "\\products.csv"), false);


        reservations.forEach(res -> {
            resFile.addDataToSystem(res.toString());
        });
        tables.forEach(table -> {
            tablesFile.addDataToSystem(table.toString());
        });
        products.forEach(prod -> {
            productsFile.addDataToSystem(prod.toString());
        });


        resFile.saveToCSV();
        tablesFile.saveToCSV();
        productsFile.saveToCSV();

    }

    public String toString() {
        return name;
    }
}
