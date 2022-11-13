package restaurant;

import people.Person;
import people.Staff;
import reservation.Reservation;
import till.Login;
import till.Menu;
import till.Product;
import till.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Restaurant extends Yum {
    private ArrayList<Reservation> reservations;
    private ArrayList<Table> tables;
    private ArrayList<Person> people;
    private ArrayList<Product> products;
    private Menu menu;
    private double profit;
    private ArrayList<Login> logins;



    public Restaurant(ArrayList<Reservation> reservations, ArrayList<Table> tables, ArrayList<Person> people, ArrayList<Product> products) {
        this.reservations = reservations;
        this.tables = tables;
        this.people = people;
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

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addTables(Table table) {
        this.tables.add(table);
    }



    public ArrayList<Person> getPeople() {
        return people;
    }

    public void addStaff(Staff staff) {
        this.people.add(staff);
    }

    public void removeStaff(Staff staff) {
        this.people.remove(staff);
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

	public boolean getLogin(String id, String password) {
		return Login.validateCredentials(id, password);
	}

    public void removetable(Table table) {


    }

    public void save() throws FileNotFoundException {
        CSVReader resFile = new CSVReader(new File("src/data/reservations.csv"));
        CSVReader tablesFile = new CSVReader(new File("src/data/tables.csv"));
        CSVReader staffFile = new CSVReader(new File("src/data/login.csv"));
        CSVReader productsFile = new CSVReader(new File("src/data/products.csv"));

        reservations.forEach(res -> {
            try {
                resFile.addDataToFile(res.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        tables.forEach(table -> {
            try {
                tablesFile.addDataToFile(table.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        people.forEach(staff -> {
            try {
                staffFile.addDataToFile(staff.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        products.forEach(prod -> {
            try {
                productsFile.addDataToFile(prod.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
