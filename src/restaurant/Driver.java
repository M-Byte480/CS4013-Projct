package restaurant;

import people.Customer;
import people.Person;
import till.Login;

import java.sql.PreparedStatement;
import java.util.Scanner;

public class Driver {
    private Scanner in;

    public void menuForDriver() {
        in = new Scanner(System.in);
    }

    public void run() {
        // This updates the retaurant object
        Util.bootUp();

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
                Util.save();
                System.out.println("Shutting Down");
                System.exit(0);
            }
        }
    }

    private Appointment getChoice(ArrayList<Appointment> choices) {
        if (choices.size() == 0) return null;
        while (true) {
            char c = 'A';
            for (Appointment choice : choices) {
                System.out.println(c + ") " + choice.format());
                c++;
            }
            String input = in.nextLine();
            int n = input.toUpperCase().charAt(0) - 'A';
            if (0 <= n && n < choices.size())
                return choices.get(n);
        }
    }

    public void loginSuccesful(String id) {
        char type = id.charAt(0);
        Integer integer = Integer.parseInt(String.valueOf(type));
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
        Customer bob = new Customer(name,phoneNumber,email,"1",1);

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

