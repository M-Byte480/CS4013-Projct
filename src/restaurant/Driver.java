package restaurant;

import people.*;
import reservation.Invoice;
import reservation.Reservation;
import till.Menu;
import till.Product;
import till.Table;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Driver {
    private Scanner in = new Scanner(System.in);
    private Yum yum;
    private Restaurant restaurant;
    private Menu menu = new Menu();

    public void run() {
        bootUp();
        
        if (yum.getOwner() == null) makeOwner();
        if (yum.getRestaurants().size() == 0) makeRestaurant();
        
        while (true) {
            System.out.println("(L)ogin    (S)ign up    (Q)uit");
            String command = in.nextLine().toUpperCase();
            
            if (command.equals("L")) {
                System.out.println("Enter ID: ");
                String id = in.nextLine();
                System.out.println("Enter Password: ");
                String password = in.nextLine();

                if (yum.getPerson(id) != null && yum.getPerson(id).passwordValidator(id, password)) {
                    loginSuccessful(id);
                } else {
                    System.out.println("Invalid credentials");
                }
            } else if (command.equals("S")) {
                signUp();
            } else if (command.equals("Q")) {
                yum.save();
                System.out.println("Shutting Down");
                System.exit(0);
            }
        }
    }
    
    private Restaurant bootUpRestaurant(String name) {
        CSVReader resFile = new CSVReader(new File("src\\data\\" + restaurant.getName() + "\\reservations.csv"), true);
        CSVReader tablesFile = new CSVReader(new File("src\\data\\" + restaurant.getName() + "\\tables.csv"), true);
        CSVReader productsFile = new CSVReader(new File("src\\data\\" + restaurant.getName() + "\\products.csv"), true);
        CSVReader invoicesFile = new CSVReader(new File("src\\data\\" + restaurant.getName() + "\\invoices.csv"), true);
        
        ArrayList<Table> tables = new ArrayList<>();
        tablesFile.getValues().forEach(line -> {
            tables.add(new Table(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
        });
        
        ArrayList<Reservation> res = new ArrayList<>();
        resFile.getValues().forEach(line -> {
            res.add(makeReservation(line, tablesFile.getData(line[0], "tableNumber").split(",")));
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
            makeReservation(resString, tablesFile.getData(resString[0], "tableNumber").split(",")),
            Integer.parseInt(line[4])
            ));
        });
        
        return new Restaurant(name, res, tables, products, invoices);
    }
    
    private void bootUp() {
        CSVReader restaurantFile = new CSVReader(new File("src\\data\\restaurants.csv"), true);
        CSVReader peopleFile = new CSVReader(new File("src\\data\\people.csv"), true);
        
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        restaurantFile.getValues().forEach(line -> {
            restaurants.add(new Restaurant(line[0]));
        });
        
        HashMap<String, Person> people = new HashMap<>();
        Owner owner = null;
        for (String[] line : peopleFile.getValues()) {
            int level = Character.getNumericValue(line[2].charAt(0));
            if (level < 2)
            people.put(line[2], new Customer(line[0], line[1], line[2], line[3], Double.parseDouble(line[4])));
            else if (level < 9)
            people.put(line[2], new Staff(line[0], line[1], line[2], line[3]));
            else {
                people.put(line[2], new Owner(line[0], line[1], line[2], line[3]));
                owner = new Owner(line[0], line[1], line[2], line[3]);
            }
        }
        yum = new Yum(restaurants, people, owner);
    }
    
    private static Reservation makeReservation(String[] ResParams, String[] TableParams) {
        return new Reservation(
        new Table(Integer.parseInt(TableParams[0]), Integer.parseInt(TableParams[1])),
        LocalDateTime.parse(ResParams[1], DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm")),
        LocalDateTime.parse(ResParams[2], DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm"))
        );
    }
    
    private void loginSuccessful(String id) {
        System.out.println("Select a Restaurant");
        restaurant = Utils.getChoice(yum.getRestaurants(), in);
        bootUpRestaurant(restaurant.getName());

        int integer = Character.getNumericValue(id.charAt(0));
        String command;

        while (true) {
            if (integer == 8) {
                System.out.println("F)ood ready   Q)uit");
                command = in.nextLine().toUpperCase();
                if (command.equals("F")) {
                    foodReady();
                } else if (command.equals("Q")) {
                    break;
                } else {
                    System.out.println("Invalid Command, Please Select Another");
                }
            } else {
                StringBuilder output = new StringBuilder();
                // owner: shows staff, profit
                // staff: show products, show tables, show reservations
                // cust: show own reseravations
                if (integer == 9) output.append("Show (S)taff    (A)dd Staff    (P)rofit    ");
                if (integer > 1) output.append("Show (P)roduct    Add Product    Show (T)able    Add Table    ");
                output.append("Show (R)eservations    (Q)uit");
                System.out.println(output);
                
                command = in.nextLine().toUpperCase();
                if (command.equals("M")) {
                    createReservation();
                } else if (command.equals("Q")) {
                    break;
                } else if (integer > 1) {
                    if (command.equals("A")) {
                        addProduct();
                    } else if (command.equals("C")) {
                        createTable();
                    } else if (command.equals("D")) {
                        deleteTable();
                    } else if (command.equals("T")) {
                        menu.run(restaurant, in);
                    } else if (integer == 9) {
                        if (command.equals("H")) {
                            hireStaff();
                        } else if (command.equals("R")) {
                            removeStaff();
                        } else if (command.equals("P")) {
                            checkProfit();
                        }
                    }
                } else {
                    System.out.println("Invalid Command, Please Select Another");
                }
            }  
        }
    }
    
    
//     private void removeStaff() {
//         System.out.println("Enter staff ID you would like to remove");
//         String id = in.nextLine();
//         yum.removePerson(yum.getPerson(id));
//     }
    
    public void signUp() {
        System.out.println("Enter full name");
        String name = in.nextLine();
        System.out.println("Enter Phone Number");
        String phoneNumber = in.nextLine();
        System.out.println("Enter A Password : ");
        String password = in.nextLine();
        Customer bob = new Customer(name, phoneNumber, password, 0);
        yum.addPerson(bob);
        System.out.println("Sign Up Complete");
        loginSuccessful(bob.getId());
    }
    
//     private void createReservation() {
//         Table table = Utils.getChoice(restaurant.getTables());
//         System.out.println("Enter Date (YYYY-MM-DD) : ");
//         String date = in.nextLine();
//         System.out.println("Enter Time (HH:mm) : ");
//         String time = in.nextLine();
//         System.out.println("Enter Finish Time (HH:mm) : ");
//         String finish = in.nextLine();
//         LocalDateTime start = LocalDateTime.parse(String.format("%sT%s", date, time), DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm"));
//         LocalDateTime end = LocalDateTime.parse(String.format("%sT%s", date, finish), DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm"));
        
//         Reservation newReservation = new Reservation(table, start, end);
//         restaurant.addReservation(newReservation);
//     }
    
//     private void deleteTable() {
//         System.out.println("Select a table which you would like to delete :  ");
//         restaurant.removeTable(Utils.getChoice(restaurant.getTables()));
//     }
    
//     private void hireStaff() {
        
//         System.out.println("Enter name of new staff member");
//         String name = in.nextLine();
//         System.out.println("Enter phone number of new staff member");
//         String phoneNumber = in.nextLine();
//         System.out.println("Enter new staff Password");
//         String password = in.nextLine();
//         System.out.println("Is staff a W)aiter or C)hef : ");
//         String type = in.nextLine().toUpperCase();
//         if (type.equals("C")) {
//             Chef newRecruit = new Chef(name, phoneNumber, password);
//             yum.addPerson(newRecruit);
//             System.out.println("Chef Added");
//         } else if (type.equals("W")) {
//             Staff newRecruit = new Staff(name, phoneNumber, password);
//             yum.addPerson(newRecruit);
//             System.out.println("Waiter Added");
//         } else {
//             System.out.println("Unknown Command ");
//         }
//     }
    
//     public double checkProfit() {
//         return restaurant.getProfit();
//     }
    
//     private void createTable() {
//         int tableNumber, seats;
//         System.out.println("Enter the table number");
//         tableNumber = in.nextInt();
//         in.nextLine();
//         System.out.println("Enter in the number of seats you need");
//         seats = in.nextInt();
//         in.nextLine();
//         Table resTable = new Table(tableNumber, seats);
//         System.out.println("New table created   Table Number : " + resTable.getTableNumber() + "Number of seats : " + resTable.getSeats());
//         restaurant.addTable(resTable);
//     }
    
//     private void addProduct() {
//         System.out.println("Enter The Name Of The Product : ");
//         String nameOfProduct = in.nextLine();
//         System.out.println("Enter The Product Description : ");
//         String description = in.nextLine();
//         System.out.println("Enter The Cost Of The Product: ");
//         double cost = in.nextDouble();
//         in.nextLine();
//         System.out.println("Enter The Name Of The Product (Seperate using ;)  : ");
//         String allergy = in.nextLine();
//         ArrayList<String> allergies = new ArrayList<>(Arrays.asList(allergy.split(";")));
//         Product newProduct = new Product(nameOfProduct, description, cost, allergies);
//         restaurant.addProduct(newProduct);
//     }
    
    private void makeOwner() {
        System.out.println("Enter name of Owner");
        String name = in.nextLine();
        System.out.println("Enter Owners Phone Number");
        String phoneNumber = in.nextLine();
        System.out.println("Enter Owners Password");
        String password = in.nextLine();
        Owner owner = new Owner(name, phoneNumber, password);
        yum.setOwner(owner);
        yum.addPerson(owner);
        System.out.println("Owners User ID : " + owner.getId());
    }
    
    private void makeRestaurant() {
        System.out.println("Enter The Name Of The New Restaurant: ");
        String name = in.nextLine();
        yum.addRestaurant(name);
    }
    
//     private void foodReady() {
//         System.out.println("Select which order is ready : ");
//         ArrayList<Product> ready = Utils.getChoice(restaurant.getOrders());
//         restaurant.removeFromOrder(ready);
//     }
}

