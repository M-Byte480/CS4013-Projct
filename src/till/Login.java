package till;

import people.Person;
import people.Staff;


public class Login {
    private String ID;

    public Login(String id, String password) {
        String tillID = Integer.toString(Person.getUniqueID());
        String tillPassword = getPassword();

        if (!id.equals(tillID) || !password.equals(tillPassword)) {
            System.out.println("Invalid Credentials");
        } else if (!id.equals(tillID) && !password.equals(tillPassword)) {
            System.out.println("Invalid Credentials");
        } else if (id.equals(tillID) && password.equals(tillPassword)){
            System.out.println("Valid Credentials");
        }
    }

    Login(String ID) {
        this.ID = ID;
    }

    public void billTable( String ID) {
        switch (ID) {

            case "customer":
                ID.startsWith("1");


                break;

            case "Employee":
                ID.startsWith("2");

                break;

            case "Owner":
                ID.startsWith("3");

                break;


        }
    }
}
