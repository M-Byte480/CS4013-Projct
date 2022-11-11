package restaurant;

import reservation.Reservation;
import till.Login;
import till.Menu;
import till.Product;
import till.Table;
import people.Staff;

import java.util.ArrayList;


public class Restaurant extends Yum {
    private ArrayList<Reservation> reservations;
    private ArrayList<Table> tables;
    private ArrayList<Staff> staff;
    private ArrayList<Product> products;
    private Menu menu;
    private double profit;
    private ArrayList<Login> logins = new ArrayList<>();


    public Restaurant(ArrayList<Reservation> reservations, ArrayList<Table> tables, ArrayList<Staff> staff, ArrayList<Product> products) {
        this.reservations = reservations;
        this.tables = tables;
        this.staff = staff;
        this.products = products;
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
        this.reservations.remove(reservation);
    }
    public ArrayList<Table> getTables() {
        return tables;
    }
    public void addTables(Table table) {
        this.tables.add(table);
    }
    public void removetables(Table table) {
        this.tables.remove(table);
    }
    public ArrayList<Staff> getStaff() {
        return staff;
    }
    public void addStaff(Staff staff) {
        this.staff.add(staff);
    }
    public void removeStaff(Staff staff) {
        this.staff.remove(staff);
    }
    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public double getProfit() {
        return profit;
    }
    public void setProfit(double profit) {
        this.profit = profit;
    }
}
