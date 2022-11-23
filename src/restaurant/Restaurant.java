package restaurant;

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

    public Restaurant(String name) {
        this.name = name;
        this.reservations = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.products = new ArrayList<>();
        this.invoices = new ArrayList<>();
        Utils.makeCSVFiles(name);
    }

    public Restaurant(String name, ArrayList<Reservation> reservations, ArrayList<Table> tables, ArrayList<Product> products, 
        ArrayList<Invoice> invoices) {
        this.name = name;
        this.reservations = reservations;
        this.tables = tables;
        this.products = products;
        this.invoices = invoices;
    }
public void addToOrder(ArrayList<Product> p){
        orders.add(p);
}
    public void removeFromOrder(ArrayList<Product> p){
        orders.remove(p);
    }
    public ArrayList<ArrayList<Product>> getOrders(){
        return orders;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }
    public boolean addReservation(Reservation reservation) {
        boolean isBooked = false;
        for (Reservation res : reservations) {
            if (res.overlaps(reservation)) isBooked = true;
        }
        
        if (isBooked) return false;
        this.reservations.add(reservation);
        return true;
    }
    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    public void addProduct(Product prod) {
        products.add(prod);
    }
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
        ArrayList<Table> freeTables = getTablesBookedBetweenTime(timeStart, timeEnd);
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
            tempTables.add(res.getTable());
        }
        return tempTables;
    }

    public double getProfitBetweenTime(LocalDateTime timeStart, LocalDateTime timeEnd) {
        double profit = 0;
        for (Invoice invoice : invoices) {
            if ((invoice.getReservation().getTime().isAfter(timeStart)) && (invoice.getReservation().getTime().isBefore(timeEnd))) continue;
            else if ((invoice.getReservation().getLength().isAfter(timeStart)) && (invoice.getReservation().getLength().isBefore(timeEnd))) continue;
            profit += invoice.getTotal();
        }
        return profit;
    }

    public void save() {
        CSVReader resFile = new CSVReader(new File("src\\data\\" + name + "\\reservations.csv"), false);
        CSVReader tablesFile = new CSVReader(new File("src\\data\\" + name + "\\tables.csv"), false);
        CSVReader productsFile = new CSVReader(new File("src\\data\\" + name + "\\products.csv"), false);
        CSVReader invoiceFile = new CSVReader(new File("src\\data\\" + name + "\\invoices.csv"), false);

        reservations.forEach(res -> {
            resFile.addDataToSystem(res.toString());
        });
        tables.forEach(table -> {
            tablesFile.addDataToSystem(table.toString());
        });
        products.forEach(prod -> {
            productsFile.addDataToSystem(prod.toString());
        });
        invoices.forEach(invoice -> {
            invoiceFile.addDataToSystem(invoice.toString());
        });

        resFile.saveToCSV();
        tablesFile.saveToCSV();
        productsFile.saveToCSV();
        invoiceFile.saveToCSV();
    }
}
