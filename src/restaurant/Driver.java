package restaurant;

import people.Customer;
import people.Owner;
import people.Person;
import people.Staff;
import reservation.Invoice;
import reservation.LineItem;
import reservation.Reservation;
import till.Login;
import till.Product;
import till.Table;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.spi.LocaleServiceProvider;


public class Driver {
    private Scanner in;
    private static Restaurant restaurant;

    
    public void menuForDriver() {
        in = new Scanner(System.in);
    }
    
    public void run() {
        // This updates the retaurant object
        bootUp();
        
        boolean more = true;
        
        while (more) {
            System.out.println("L)ogin  S)ign up  Q)uit");
            String command = in.nextLine().toUpperCase();
            
            
            //If login
            
            if (command.equals("L")) {
                System.out.println("Enter User ID");
                String id = in.nextLine();
                System.out.println("Enter Password : ");
                String password = in.nextLine();
                
                if (!restaurant.getLogin(id, password)) {
                    System.out.println("Invalid credentials");
                } else {
                    // Once logged in, allow the person to have a access to certain options based on their level of access
                    loginSuccesful(id);
                }
                
                
            } else if (command.equals("S")) {
                // Signs up the person, create new person object and add it to the arraylist of people.
                signUp();
                
            } else if (command.equals("Q")) {
                restaurant.save();
                System.out.println("Shutting Down");
                System.exit(0);
            }
        }
    }
    public static void bootUp() {
        CSVReader resFile = new CSVReader(new File("src/data/reservations.csv"), true);
        CSVReader tablesFile = new CSVReader(new File("src/data/tables.csv"), true);
        CSVReader peopleFile = new CSVReader(new File("src/data/people.csv"), true);
        CSVReader productsFile = new CSVReader(new File("src/data/products.csv"), true);
        CSVReader invoicesFile = new CSVReader(new File("src/data/invoices.csv"), true);
        
        ArrayList<Table> tables = new ArrayList<>();
        tablesFile.getValues().forEach(line -> {
            tables.add(new Table(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
        });
        
        ArrayList<Reservation> res = new ArrayList<>();
        resFile.getValues().forEach(line -> {
            res.add(makeReservation(line, tablesFile.get(line[0], "tableNumber").split(",")));
        });
        
        HashMap<String, Person> people = new HashMap<>();
        peopleFile.getValues().forEach(line -> {
            int level = Character.getNumericValue(line[2].charAt(0));
            if (level < 2)
                people.put(line[2], new Customer(line[0], line[1], line[2], Double.parseDouble(line[3])));
            else if (level < 9)
                people.put(line[2], new Staff(line[0], line[1], line[2]));
            else people.put(line[2], new Owner(line[0], line[1], line[2]));
        });

        ArrayList<Product> products = new ArrayList<>();
        productsFile.getValues().forEach(line -> {
            ArrayList<String> alergies = new ArrayList<>(Arrays.asList(line[3].split(";")));
            products.add(new Product(line[0], line[1], Double.parseDouble(line[2]), alergies));
        });

        ArrayList<Invoice> invoices = new ArrayList<>();
        invoicesFile.getValues().forEach(line -> {
            String[] resString = line[1].split(";");
            invoices.add(new Invoice(
                makeReservation(resString, tablesFile.get(resString[0], "tableNumber").split(",")), 
                Integer.parseInt(line[4])
                ));
        });
        
        restaurant = new Restaurant(res, tables, people, products, invoices);
    }
    private static Reservation makeReservation(String[] ResParams, String[] TableParams) {
        return new Reservation(
            new Table(Integer.parseInt(TableParams[0]), Integer.parseInt(TableParams[1])), 
            LocalDateTime.parse(ResParams[1], DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm")),
            LocalTime.parse(ResParams[2])
        );
    }
    
    private Object getChoice(Object[] choices) {
        if (choices.length == 0) return null;
        while (true) {
            char c = 'A';
            for (Object choice : choices) {
                System.out.println(c + ") " + choice);
                c++;
            }
            String input = in.nextLine();
            int n = input.toUpperCase().charAt(0) - 'A';
            if (0 <= n && n < choices.length)
                return choices[n];
        }
    }

    private Table createTable(){
        int tableNumber, seats;
        System.out.println("Enter the table number");
        tableNumber = in.nextInt();
        System.out.println("Enter in the number of seats you need");
        seats = in.nextInt();
        Table resTable = new Table(tableNumber, seats);
        return resTable;
    }

    private void addToOrder(){

    }
    private void removeFromOrder(){

    }

    public void loginSuccessful(String id) {
        int integer = Character.getNumericValue(id.charAt(0));
        String timeInString;LocalDateTime time;

        Table resTable = createTable();
        int tableNumber, seats, minutesDurationLength;
        System.out.println("Enter the time you want to book in format: YYYY-MM-DDTHH:mm");
        timeInString = in.nextLine();
        time = LocalDateTime.parse(timeInString, DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm"));
        minutesDurationLength = in.nextInt();
        Duration dur = Duration.of(minutesDurationLength, ChronoUnit.MINUTES);
        Reservation reservation = new Reservation(resTable, time ,dur);
        if (integer > 1){ //Uses char to specify which action to do i.e create or remove(closeTable)
            Table create = createTable();
            resTable.closeTable();
            restaurant.getProducts();
            Invoice invoice = new Invoice(reservation);
            invoice.getTotal();
            if (integer == 9){
                restaurant.getProfit();
                String name, phoneNumber, email, newStaffID;
                System.out.println("Enter name of new staff member");
                name = in.nextLine();
                System.out.println("Enter phone number of new staff member");
                phoneNumber = in.nextLine();
                System.out.println("Enter new staff member's email address");
                email = in.nextLine();
                //
                System.out.println("For a regular staff member enter 2 -8, or 0 for a manager");
                newStaffID = in.nextLine();
                restaurant.addStaff(new Staff(name, phoneNumber, email, newStaffID));
            }
        }
        

        /*
        Start: make and create reservation

            if(ID > 1)
                create + remove table
                add + remove order
                pay

                if( ID == 9)
                    view profit
                    add staff
         */

    }


    
    public void signUp() {
        System.out.println("Enter full name");
        String name = in.nextLine();
        System.out.println("Enter Email");
        String email = in.nextLine().toLowerCase();
        System.out.println("Enter Phone Number");
        String phoneNumber = in.nextLine();
        Customer bob = new Customer(name,phoneNumber,email,"1",0);
        
        restaurant.addPerson(bob);
        System.out.println("Your User ID : ");
        System.out.println(bob.getId());
        System.out.println("Enter A New Password : ");
        String password = in.nextLine();
        
        restaurant.addLogins();
        System.out.println("Sign Up Complete");
        
    }

    public void loginOwner() {
        // Owner has access: create and delete table, view profit, add staff, add and remove order. pay

        
    }

    public void loginStaff(){
        // Staff will have access to: create and remove table, add and remove order, make and cancel reservation, pay

    }

    public void loginCustomer(){
        // Customer: Make and cancel reservation, pay

    }

    /*
     * Reservation option will give option to make booking and delete
     * It is literally copy and paste of the Appointment work from last lab
     *
     * Create and remove table is kind of like view, remove and add products
     *
     * Add and remove order is the same, it will be applied on top ofa table
     *
     * Pay wraps it up
     *
     * You need to make a method that handles each of these
     *
     */

    @Override
    public String toString() {
        return "AppointmentMenu{" +
        "in=" + in +
        '}';
    }
}

