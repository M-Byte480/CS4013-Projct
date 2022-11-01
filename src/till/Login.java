package till;

import people.Person;
import people.Staff;

public class Login extends Staff{



    public Login(String id, String password) {
        super();
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




}
