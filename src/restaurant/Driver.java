package restaurant;

import people.Customer;
import reservation.Reservation;
import reservation.Timetable;

import java.util.Scanner;

public class Driver {
    private Scanner in;

    public void menuForDriver() {
        in = new Scanner(System.in);
    }

    private Timetable timetable = new Timetable();

    public void run() {
        // This updates the retaurant object
        Util.bootUp();

        boolean more = true;

        while (more) {
            System.out.println("L)ogin  S)ign up  Q)uit");
            String command = in.nextLine().toUpperCase();


            if (command.equals("L")) {
                System.out.println("Enter User ID");
                String id = in.nextLine();
                System.out.println("Enter Password : ");
                String password = in.nextLine();

                if (!restaurant.getLogin(id, password)) {
                    System.out.println("Invalid credentials");
                } else {
                    loginSuccesful(id);
                }


            } else if (command.equals("S")) {
                signUp();

            } else if (command.equals("Q")) {
                Util.save();
                System.out.println("Shutting Down");
                System.exit(0);
            }
        }
    }

    public void loginOwner() {
        boolean ownerMore = true;

        while (ownerMore) {
            System.out.println("M)ake Booking  C)reate Table  D)elete Table   T)ake Order   H)ire Staff   P)rofit  Q)uit");
            String command = in.nextLine().toUpperCase();
            if (command.equals("M")) {
                createReservation():

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
                Util.save();
                run();

            }

        }
    }

    public void loginStaff() {
        boolean staffMore = true;

        while (staffMore) {
            System.out.println("M)ake Booking  C)reate Table  D)elete Table   T)ake Order   Q)uit");
            String command = in.nextLine().toUpperCase();
            if (command.equals("M")) {
                createReservation():

            } else if (command.equals("C")) {
                createTable();

            } else if (command.equals("D")) {
                deleteTable();

            } else if (command.equals("T")) {
                run(menu);

            } else if (command.equals("Q")) {
                Util.save();
                run();

            }

        }
    }

    public void loginCustomer() {
        boolean custumerMore = true;

        while (custumerMore) {
            System.out.println("M)ake Booking  Q)uit");
            String command = in.nextLine().toUpperCase();
            if (command.equals("M")) {
                createReservation():

            } else if (command.equals("Q")) {
                Util.save();
                run();

            }

        }
    }

    public void loginSuccesful(String id) {
        char type = id.charAt(0);
        Integer integer = Integer.parseInt(String.valueOf(type));
        if (integer == 9) {
            loginOwner();
        } else if (integer == 5) {
            loginStaff();
        } else {
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
        Customer bob = new Customer(name, phoneNumber, email, "1", 0);

        restaurant.addPerson(bob);
        System.out.println("Your User ID : ");
        System.out.println(bob.getId());
        System.out.println("Enter A New Password : ");
        String password = in.nextLine();

        retaurant.addLogins();
        System.out.println("Sign Up Complete");

    }

    public void createReservation() {
        System.out.println("Enter ID : ");
        String ID = in.nextLine();
        System.out.println("Enter Date DD/MM/YYYY : ");
        String DATE = in.nextLine().toLowerCase();
        System.out.println("Enter Start Time : ");
        String startTime = in.nextLine();
        System.out.println("Enter End Time : ");
        String endTime = in.nextLine();
        Reservation newRes = new Reservation();
    }
}

