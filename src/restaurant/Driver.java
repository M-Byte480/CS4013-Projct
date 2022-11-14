package restaurant;

import people.Customer;
import people.Owner;
import people.Person;
import people.Staff;
import reservation.Invoice;
import reservation.Reservation;
import till.Product;
import till.Table;
import java.io.File;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;



public class Driver {
    private Scanner in;
    private static Restaurant restaurant;




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

                if (restaurant.getPerson(id) == null) {
                    System.out.println("Invalid credentials");
                } else {
                    // Once logged in, allow the person to have a access to certain options based on their level of access
                    loginSuccessful(id);
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
            res.add(makeReservation(line, tablesFile.getData(line[0], "tableNumber").split(",")));
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
                    makeReservation(resString, tablesFile.getData(resString[0], "tableNumber").split(",")),
                    Integer.parseInt(line[4])
            ));
        });

        restaurant = new Restaurant(res, tables, people, products, invoices);
    }


    private static Reservation makeReservation(String[] ResParams, String[] TableParams) {
        return new Reservation(
                new Table(Integer.parseInt(TableParams[0]), Integer.parseInt(TableParams[1])),
                LocalDateTime.parse(ResParams[1], DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm")),
                LocalDateTime.parse(ResParams[2], DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm"))
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


    public void loginSuccessful(String id)  {
        int integer = Character.getNumericValue(id.charAt(0));
        System.out.println("M)ake Booking  Q)uit");
        String command = in.nextLine().toUpperCase();
        if (command.equals("M")) {
            createReservation();

        } else if (command.equals("Q")) {
            restaurant.save();
            run();

        }

        if (integer > 1) { //Uses char to specify which action to do i.e create or remove(closeTable)


            System.out.println("M)ake Booking  A)dd Product  C)reate Table  D)elete Table   T)ake Order   Q)uit");
            if (command.equals("M")) {
                createReservation();

            } else if (command.equals("A")) {
                addProduct();

            } else if (command.equals("C")) {
                createTable();

            } else if (command.equals("D")) {
                deleteTable();

            } else if (command.equals("T")) {
                run(Menu);

            } else if (command.equals("Q")) {
                restaurant.save();
                run();

            }

            if (integer == 9) {
                System.out.println("M)ake Booking   A)dd Product  C)reate Table  D)elete Table   T)ake Order   H)ire Staff   P)rofit  Q)uit");
                if (command.equals("M")) {
                    createReservation();

                } else if (command.equals("A")) {
                    addProduct();

                } else if (command.equals("C")) {
                    createTable();

                } else if (command.equals("D")) {
                    deleteTable();

                } else if (command.equals("T")) {
                    run(Menu);

                } else if (command.equals("H")) {
                    hireStaff();


                } else if (command.equals("P")) {
                    checkProfit();

                } else if (command.equals("Q")) {
                    restaurant.save();
                    run();

                }
            } else throw new RuntimeException("Invalid Command, Please Select Another");
        }
    }

    public void signUp() {
        System.out.println("Enter full name");
        String name = in.nextLine();
        System.out.println("Enter Phone Number");
        String phoneNumber = in.nextLine();
        Customer bob = new Customer(name, phoneNumber, 0);
        restaurant.addPerson(bob);
        System.out.println("Your User ID : ");
        System.out.println(bob.getId());
        System.out.println("Enter A New Password : ");
        String password = in.nextLine();
        restaurant.getPerson(bob.getId());
        System.out.println("Sign Up Complete");

    }

    private void createReservation() {
        Table table = ((Table) getChoice(restaurant.getTables().toArray()));
        System.out.println("Enter Start Date (YYYY-MM-DD'T'HH:mm) : ");
        String time = in.nextLine();
        System.out.println("Enter Length (HH:mm) : ");
        String length = in.nextLine();
        LocalDateTime start = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm"));
        LocalTime end = LocalTime.parse(length);

        Reservation newReservaion = new Reservation(table, start, end);
        restaurant.addReservation(newReservaion);
    }

    private void deleteTable() {
        System.out.println("Select a table which you would like to delete :  ");
        restaurant.removeTable((Table) getChoice(restaurant.getTables().toArray()));
    }

    private void hireStaff() {
        System.out.println("Enter name of new staff member");
        String name = in.nextLine();
        System.out.println("Enter phone number of new staff member");
        String phoneNumber = in.nextLine();
        Staff newRecruit = new Staff(name, phoneNumber);
        restaurant.addPerson(newRecruit);
    }

    public double checkProfit() {
        double tadhgRyanShmoney = restaurant.getProfit();
        return tadhgRyanShmoney;
    }

    private Table createTable() {
        int tableNumber, seats;
        System.out.println("Enter the table number");
        tableNumber = in.nextInt();
        System.out.println("Enter in the number of seats you need");
        seats = in.nextInt();
        Table resTable = new Table(tableNumber, seats);
        return resTable;
    }

    private void addProduct() {
        System.out.println("Enter The Name Of The Product : ");
        String nameOFProdcut = in.nextLine();
        System.out.println("Enter The Product Description : ");
        String description = in.nextLine();
        System.out.println("Enter The Cost Of The Product: ");
        double cost = in.nextDouble();
        in.nextLine();
        System.out.println("Enter The Name Of The Product (Seperate using ,)  : ");
        String allergy = in.nextLine();
        ArrayList<String> allergies = new ArrayList<>(Arrays.asList(allergy.split(";")));
        Product newProduct = new Product(nameOFProdcut, description, cost, allergies);
        restaurant.addProduct(newProduct);
    }
}

