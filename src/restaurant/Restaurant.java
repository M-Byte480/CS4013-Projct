package restaurant;


import people.Staff;
import reservation.*;
import till.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Restaurant extends Yum {
    private String name;
    private String loaction;
    private ArrayList<Staff> staff;
    private ArrayList<Staff> invoice;
    private ArrayList<Reservation> allReservations;
    private Timetable timetable;
    private ArrayList<Product> products;
    private ArrayList<Table> allTables;
    private ArrayList<Table> table;

    private ArrayList<Restaurant> locations;


    private Menu menu;
    private double profit;

    public Restaurant(String name, String location, ArrayList<Staff> staff, ArrayList<Staff> invoice, ArrayList<Reservation> reservations, Timetable timetable, ArrayList<Product> product, ArrayList<Table> table) throws FileNotFoundException {
        this.name = name;
        this.loaction = location;
        this.staff = staff;
        this.invoice = invoice;
        this.allReservations = reservations;
        this.timetable = timetable;
        this.products = product;
        this.table = table ;


        Util RestaurantDetails = new Util(new File("/src/data/invoices.csv"));
        RestaurantDetails.addDataToFile(new String[] {name, location,staff , invoice, reservations, timetable, product, table   });
    }



    public Restaurant(){

    }
}
