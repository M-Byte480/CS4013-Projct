package restaurant;

import people.Customer;
import people.Person;
import people.Staff;
import reservation.Reservation;
import till.Login;
import till.Product;
import till.Table;
import reservation.Reservation;
import reservation.Timetable;

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
import java.util.StringTokenizer;

public class Driver {
    private Scanner in;
    private static Restaurant restaurant;

    public void menuForDriver() {
        in = new Scanner(System.in);
    }

    public void run() throws IOException {
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

    public static void bootUp() throws FileNotFoundException {
        CSVReader resFile = new CSVReader(new File("src/data/reservations.csv"));
        CSVReader tablesFile = new CSVReader(new File("src/data/tables.csv"));
        CSVReader staffFile = new CSVReader(new File("src/data/people.csv"));
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
                    LocalDateTime.parse(line[1], DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm")),
                    Duration.between(LocalTime.MIN, LocalTime.parse(line[2]))
            ));
        });

        ArrayList<Staff> staff = new ArrayList<>();
        staffFile.getValues().forEach(line -> {
            int id = Character.getNumericValue(line[3].charAt(0));
            if (id > 1 && id != 9)
                staff.add(new Staff(line[0], line[1], line[2], line[3]));
        });

        ArrayList<Product> products = new ArrayList<>();
        productsFile.getValues().forEach(line -> {
            ArrayList<String> alergies = new ArrayList<>(Arrays.asList(line[3].split(";")));
            try {
                products.add(new Product(line[0], line[1], Double.parseDouble(line[2]), alergies));
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        });

        restaurant = new Restaurant(res, tables, staff, products);
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


    //make interface for loginSuccessful

    public void loginSuccessful(String id) throws IOException {

        System.out.println("M)ake Booking  Q)uit");
        String command = in.nextLine().toUpperCase();
        if (command.equals("M")) {
            createReservation();

        } else if (command.equals("Q")) {
            restaurant.save();
            run();

        }

        if (integer > 1) { //Uses char to specify which action to do i.e create or remove(closeTable)


            System.out.println("M)ake Booking  C)reate Table  D)elete Table   T)ake Order   Q)uit");
            String command = in.nextLine().toUpperCase();
            if (command.equals("M")) {
                createReservation();

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
                System.out.println("M)ake Booking  C)reate Table  D)elete Table   T)ake Order   H)ire Staff   P)rofit  Q)uit");
                if (command.equals("M")) {
                    createReservation();

                } else if (command.equals("C")) {
                    createTable();

                } else if (command.equals("D")) {
                    deleteTable();

                } else if (command.equals("T")) {
                    run(menu);

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

    public void signUp() {
        System.out.println("Enter full name");
        String name = in.nextLine();
        System.out.println("Enter Email");
        String email = in.nextLine().toLowerCase();
        System.out.println("Enter Phone Number");
        String phoneNumber = in.nextLine();
        Customer bob = new Customer(name, phoneNumber, email, "1", 0);

        restaurant.addPerson(bob);
        System.out.println("Your User ID : ");
        System.out.println(bob.getId());
        System.out.println("Enter A New Password : ");
        String password = in.nextLine();

        restaurant.addLogins();
        System.out.println("Sign Up Complete");

    }

    private void createReservation() {
        System.out.println("Enter Table ID, Enter Start Time Enter End Time ");
        Table table = ((Table) getChoice(restaurant.getTables().toArray());
    }

    private void deleteTable() {
        System.out.println("Select a table which you would like to delete :  ");
        restaurant.removetable((Table) getChoice(restaurant.getTables().toArray()));
    }

    private void hireStaff() {
        System.out.println("Enter name of new staff member");
        String name = in.nextLine();
        System.out.println("Enter phone number of new staff member");
        String phoneNumber = in.nextLine();
        Staff newRecruit = new Staff(name, phoneNumber);
        restaurant.addStaff(newRecruit);
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

    private void addToOrder() {

    }

    private void removeFromOrder() {

    }


    int integer = Character.getNumericValue(id.charAt(0));
    String timeInString;
    LocalDateTime time;

    Table resTable = createTable();
    int tableNumber, seats, minutesDurationLength;
        System.out.println("Enter the time you want to book in format: YYYY-MM-DDTHH:mm");
                timeInString=in.nextLine();
                time=LocalDateTime.parse(timeInString,DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm"));
                minutesDurationLength=in.nextInt();
                Duration dur=Duration.of(minutesDurationLength,ChronoUnit.MINUTES);
                Reservation reservation=new Reservation(resTable,time,dur);


