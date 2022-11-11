package restaurant;

import people.Customer;
import people.Person;
import people.Staff;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {
    private Scanner in;
    private static Restaurant restaurant;
    
    public void menuForDriver() {
        in = new Scanner(System.in);
    }
    
    public static void run() {
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
                
                if (!restaraunt.getLogin(id, password)) {
                    System.out.println("Invalid credentials");
                } else {
                    loginSuccesful(id);
                }
                
                
            } else if (command.equals("S")) {
                signUp();
                
            } else if (command.equals("Q")) {
                CSVReader.save();
                System.out.println("Shutting Down");
                System.exit(0);
            }
        }
    }
    public static void bootUp() throws FileNotFoundException {
        CSVReader resFile = new CSVReader(new File("src/data/reservations.csv"));
        CSVReader tablesFile = new CSVReader(new File("src/data/tables.csv"));
        CSVReader staffFile = new CSVReader(new File("src/data/login.csv"));
        CSVReader productsFile = new CSVReader(new File("src/data/products.csv"));
        
        ArrayList<Table> tables = new ArrayList<>();
        tablesFile.getValues().forEach(line -> {
            tables.add(new Table(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
        });
        
        ArrayList<Reservation> res = new ArrayList<>();
        resFile.getValues().forEach(line -> {
            String[] table = tablesFile.get(line[0], "tableNumber").split(",");
            res.add(new Reservation(
                new Table(Integer.parseInt(table[0]), Integer.parseInt(table[1])), 
                LocalDateTime.parse(line[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 
                Duration.between(LocalTime.MIN, LocalTime.parse(line[2]))
            ));
        });
        
        ArrayList<Staff> staff = new ArrayList<>();
        productsFile.getValues().forEach(line -> {
            staff.add(new Staff(line[0], line[1], line[2], line[3]));
        });

        ArrayList<Product> products = new ArrayList<>();
        staffFile.getValues().forEach(line -> {
            ArrayList<String> alergies = new ArrayList<>(Arrays.asList(line[3].split(";")));
            try {
                products.add(new Product(line[0], line[1], Double.parseDouble(line[2]), alergies));
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        });
        
        restaurant = new Restaurant(res, tables, staff, products);
    }
    
    private Object getChoice(ArrayList<Object> choices) {
        if (choices.size() == 0) return null;
        while (true) {
            char c = 'A';
            for (Object choice : choices) {
                System.out.println(c + ") " + choice);
                c++;
            }
            String input = in.nextLine();
            int n = input.toUpperCase().charAt(0) - 'A';
            if (0 <= n && n < choices.size())
            return choices.get(n);
        }
    }
    
    public void loginSuccesful(String id) {
        int integer = Character.getNumericValue(id.charAt(0));
        if(integer == 9 ) {
            loginOwner();
        }else if (integer == 5 ) {
            loginStaff();
        }else {
            loginCustomer();
        }
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
        
        retaurant.addLogins();
        System.out.println("Sign Up Complete");
        
    }
    
    public void loginOwner() {
        
    }
    
    @Override
    public String toString() {
        return "AppointmentMenu{" +
        "in=" + in +
        '}';
    }
}

