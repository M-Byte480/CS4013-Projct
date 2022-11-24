package restaurant;

import people.*;
import reservation.Invoice;
import reservation.Reservation;
import till.Menu;
import till.Product;
import till.Table;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Driver {
    private final Scanner in = new Scanner(System.in);
    private Yum yum;
    private Restaurant restaurant;
    private final Menu menu = new Menu();

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
                    loginSuccessful(yum.getPerson(id));
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
        tablesFile.getValues().forEach(line -> tables.add(new Table(Integer.parseInt(line[0]), Integer.parseInt(line[1]))));
        
        ArrayList<Reservation> res = new ArrayList<>();
        resFile.getValues().forEach(line -> res.add(makeReservation(line, tablesFile.getData(line[0], "tableNumber").split(","))));

        ArrayList<Product> products = new ArrayList<>();
        productsFile.getValues().forEach(line -> {
            ArrayList<String> allergies = new ArrayList<>(Arrays.asList(line[3].split(";")));
            products.add(new Product(line[0], line[1], Double.parseDouble(line[2]), allergies));
        });

        // invoices
        ArrayList<Invoice> invoices = new ArrayList<>();
        invoicesFile.getValues().forEach(line -> invoices.add(new Invoice(
                LocalDateTime.parse(line[0], DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm")),
                Double.parseDouble(line[4]))));

        Invoice.getLatestID();
        return new Restaurant(name, res, tables, products, invoices);
    }
    
    private void bootUp() {
        CSVReader restaurantFile = new CSVReader(new File("src\\data\\restaurants.csv"), true);
        CSVReader peopleFile = new CSVReader(new File("src\\data\\people.csv"), true);
        
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        restaurantFile.getValues().forEach(line -> restaurants.add(new Restaurant(line[0])));
        
        HashMap<String, Person> people = new HashMap<>();
        Owner owner = null;
        for (String[] line : peopleFile.getValues()) {
            int level = Character.getNumericValue(line[2].charAt(0));
            if (level < 2) {
                people.put(line[2], new Customer(line[0], line[1], line[2], line[3], Double.parseDouble(line[4])));
            } else if (level < 8) {
                people.put(line[2], new Staff(line[0], line[1], line[2], line[3]));
            } else if (level == 8) {
                people.put(line[2], new Chef(line[0], line[1], line[2], line[3]));
            } else {
                    people.put(line[2], new Owner(line[0], line[1], line[2], line[3]));
                    owner = new Owner(line[0], line[1], line[2], line[3]);
                }
            }
        Utils.setUniqueID(people.size());
        yum = new Yum(restaurants, people, owner);
    }

    private static Reservation makeReservation(String[] ResParams, String[] tableParam) {
        return new Reservation(
                new Table(Integer.parseInt(tableParam[0]), Integer.parseInt(tableParam[1])),
                    LocalDateTime.parse(ResParams[1], DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm")),
                    LocalDateTime.parse(ResParams[2], DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm")),
                    ResParams[3]
                );
    }

    private void loginSuccessful(Person personLogged) {
        System.out.printf("Welcome %s\n", personLogged.getName());
        System.out.println("Select a Restaurant: ");
        restaurant = Utils.getChoice(yum.getRestaurants(), in);
        if (restaurant == null) {
            return;
        }
        restaurant = bootUpRestaurant(restaurant.getName());

        String command;

        while (true) {
            if (personLogged instanceof Chef) {
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
                // cust: show own reservations
                System.out.println("Select the following category: ");
                if (personLogged instanceof Owner) output.append("(Pe)rson    (Pr)ofit    (Res)taurant    ");
                if (personLogged instanceof Owner || personLogged instanceof Staff)
                    output.append("(P)roduct    (T)able    ");
                output.append("(R)eservations    (Q)uit");

                System.out.println(output);
                command = in.nextLine().toUpperCase();

                if (command.equals("Q")) {
                    restaurant.save();
                    break;
                } else if (command.equals("PR")) {     // Get profit between two dates
                    System.out.println("Enter first period of time you want to get the profit from: YYYY-MM-DD");
                    String startingDate = in.nextLine();
                    System.out.println("Enter end period of time you want to get the profit from: YYYY-MM-DD");
                    String endDate = in.nextLine();

                    System.out.printf("Profit: %.2f%n", restaurant.getProfitBetweenTime(LocalDate.parse(startingDate).atStartOfDay(),
                            LocalDate.parse(endDate).atTime(LocalTime.MAX)));
                } else {
                    if (command.equals("T")) {
                        System.out.println("(S)elect, (A)dd or (R)emove");
                    } else {
                        System.out.println("(A)dd or (R)emove");
                    }
                    String showOrAdd = in.nextLine().toUpperCase();


                    if (showOrAdd.equals("R")) {
                        if (command.equals("R")) {
                            Reservation res;
                            if (personLogged instanceof Customer) {
                                res = Utils.getChoice(restaurant.getReservationsForCustomer((Customer) personLogged), in);
                            } else {
                                res = Utils.getChoice(restaurant.getReservations(), in);
                            }
                            if (res == null) {
                                continue;
                            }
                            restaurant.removeReservation(res);
                            System.out.printf("Your reservation has been removed: %s", res);

                            // add data for removing res and stuff
                        } else if (command.equals("T")) {
                            Table table = Utils.getChoice(restaurant.getTables(), in);
                            if (table == null) {
                                continue;
                            }
                            restaurant.removeTable(table);
                            for (Reservation r : restaurant.getReservations()) {
                                if (r.getTable() == table) {
                                    restaurant.removeReservation(r);
                                }
                            }

                            // Edit Product(s)
                        } else if (command.equals("P")) {
                            Product product = Utils.getChoice(restaurant.getProducts(), in);
                            if (product == null) {
                                continue;
                            }
                            restaurant.removeProduct(product);

                            // Edit Person(s)
                        } else if (command.equals("PE")) {
                            Person person = Utils.getChoice(yum.getPeople(), in);
                            if (person == null) {
                                continue;
                            }
                            yum.removePerson(person);
                        }else if(command.equals("RES")){
                            deleteRestaurant();
                        }
                    } else if (showOrAdd.equals("A")) { // Add
                        if (command.equals("R")) {    // Reservation
                            createReservation();
                        } else if (command.equals("T")) { // Table
                            createTable();
                        } else if (command.equals("P")) { // Product
                            createProduct();
                        } else if (command.equals("PE")) { // Staff
                            createStaff();
                        } else if (command.equals("RES")) {
                            makeRestaurant();
                        }
                    } else if (showOrAdd.equals("S")) {
                        menu.run(yum, restaurant, in);
                    }
                }
            }
        }
    }

    /**
     * Method asks the user to enter their name, phone number and a password
     * and uses these user inputs to create a Customer object
     * which is then added to the HashMap that contains objects of type Person and their ID
     * number contained within the Yum class
     */
    private void signUp() {
        System.out.println("Enter full name");
        String name = in.nextLine();

        System.out.println("Enter Phone Number");
        String phoneNumber = in.nextLine();

        System.out.println("Enter A Password : ");
        String password = in.nextLine();

        Customer bob = new Customer(name, phoneNumber, password, 0);
        yum.addPerson(bob);
        System.out.println("Sign Up Complete\tYour ID is: " + bob.getId());
        loginSuccessful(bob);
    }
    
     private void createReservation() {
         System.out.println("Enter Date (YYYY-MM-DD): ");
         String date = in.nextLine();

         System.out.println("Enter Time (HH:mm): ");
         String time = in.nextLine();

         LocalDateTime start;
         try {
             start = LocalDateTime.parse(String.format("%sT%s", date, time), DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm"));
         } catch (DateTimeParseException e) {
             System.out.println("Invalid Date!");
             return;
         }
         if (start.isBefore(LocalDateTime.now())) {
             System.out.println("Time has already pasted!");
             return;
         }

         System.out.println("Enter Seats: ");
         int seats;
         try {
         seats = Integer.parseInt(in.nextLine());
         } catch (NumberFormatException e) {
             System.out.println("Invalid number of seats!");
             return;
         }

         System.out.println("Enter Finish Time (HH:mm) : ");
         String finish = in.nextLine();
         LocalDateTime end;
         try {
            end = LocalDateTime.parse(String.format("%sT%s", date, finish), DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm"));
         } catch (DateTimeParseException e) {
             System.out.println("Invalid Date!");
             return;
         }

         Table table = Utils.getChoice(restaurant.getFreeTablesOfSizeBetweenTime(seats, start, end), in);

         if (table == null) {
             System.out.println("No tables on at time/date specified!");
             return;
         }
         System.out.println("Enter Customer ID: ");
         String custID = in.nextLine();
         if (yum.getPerson(custID) == null) {
             System.out.println("No customer with ID in system. Please sign up.");
             return;
         }
         Reservation newReservation = new Reservation(table, start, end, custID);
         restaurant.addReservation(newReservation);
     }

    /**
     * User inputs a name, phone number and a password which store as variables,
     * then asks the user to choose what type of staff member it wants to create,
     * then uses said variables to create the corresponding Object either regular Staff
     * or a Chef
     */
     private void createStaff() {
         System.out.println("Enter name of new staff member");
         String name = in.nextLine();

         System.out.println("Enter phone number of new staff member");
         String phoneNumber = in.nextLine();

         System.out.println("Enter new staff Password");
         String password = in.nextLine();

         System.out.println("Is staff a (W)aiter or (C)hef : ");
         String type = in.nextLine().toUpperCase();

         if (type.equals("C")) {
             Chef newRecruit = new Chef(name, phoneNumber, password);
             yum.addPerson(newRecruit);
             System.out.println("Chef Added");
         } else if (type.equals("W")) {
             Staff newRecruit = new Staff(name, phoneNumber, password);
             yum.addPerson(newRecruit);
             System.out.println("Waiter Added");
         } else {
             System.out.println("Unknown Command ");
         }
     }

    /**
     * User inputs a table number and an amount seats which then creates
     * a new table object within the system and then adds this newly created table object
     * to the ArrayList of type Table within the restaurant defined above.
     */
     private void createTable() {
         System.out.println("Enter the table number: ");
         int tableNumber = in.nextInt();
         in.nextLine();

         System.out.println("Enter the number of seats: ");
         int seats = in.nextInt();
         in.nextLine();

         Table resTable = new Table(tableNumber, seats);
         System.out.printf("New table created    Table Number: %d    Number of seats: %d%n", resTable.getTableNumber(), resTable.getSeats());
         restaurant.addTable(resTable);
     }

    /**
     * User inputs a name, description and cost when wanting to create
     * a new product. These values are used to create the new Product object. The user
     * is then asked to input the allergies that this product contains.
     * This list gets split so each index is an allergy. All these values are then
     * used to create a new Product object then the object is added to the restaurant products.
     */
     private void createProduct() {
         System.out.println("Enter The Name Of The Product: ");
         String nameOfProduct = in.nextLine();

         System.out.println("Enter The Product Description: ");
         String description = in.nextLine();

         System.out.println("Enter The Cost Of The Product: ");
         double cost = in.nextDouble();
         in.nextLine();

         System.out.println("Enter The Allergies this product contains (Separate using ;) : ");
         String allergy = in.nextLine();
         ArrayList<String> allergies = new ArrayList<>(Arrays.asList(allergy.split(";")));

         Product newProduct = new Product(nameOfProduct, description, cost, allergies);
         restaurant.addProduct(newProduct);
     }

    /**
     * User inputs a name, phone number and a password.
     * These are then used to create a new owner which is then set as the Owner,
     * replacing the previous owner, and is then added to the person ArrayList contained in Yum
     *
     */
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

    /**
     * User inputs the name for the new restaurant, this then creates a new restaurant object
     * then adds the new object to the ArrayList restaurant stored in Yum.
     */
    private void makeRestaurant() {
        System.out.println("Enter Name Of New Restaurant: ");
        String name = in.nextLine();
        yum.addRestaurant(name);
    }

    private void deleteRestaurant() {
        System.out.println("Delete Restaurant: ");
        Restaurant rest = Utils.getChoice(yum.getRestaurants());
        if (rest != null) {
            yum.removeRestaurant(rest.getName());
            // Cant resolve method, method exists?
        }
        // intellij bad
    }

    /**
     * A choice of order is made from the ArrayList which stores an Arraylist of type Product and
     * when some list in chosen and then removed at the end, the order has been 'completed' thus the food is ready.
     */
     private void foodReady() {
         System.out.println("Select which order is ready: ");
         ArrayList<Product> ready = Utils.getChoice(restaurant.getOrders());
         restaurant.removeFromOrder(ready);
     }
}

