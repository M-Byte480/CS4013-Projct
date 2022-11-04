package till;

import people.Person;
import people.*;


public class Login {
    private String tillID;
    private String tillPassword;
    private String ID;

    /**
     * Constructor to create a login object with a specified ID and password
     * @param id
     * @param password
     */
    Login(String id, String password) {
         this.tillID = Integer.toString(Person.getUniqueID());
         this.tillPassword = Person.getPassword();
    }

        public void logIn(String id, String password) {
            if (!id.equals(tillID) || !password.equals(tillPassword)){
                System.out.println("Invalid Credentials");
            } else if (!id.equals(tillID) && !password.equals(tillPassword)) {
                System.out.println("Invalid Credentials");
            } else if (id.equals(tillID) && password.equals(tillPassword)) {
                System.out.println("Valid Credentials");
            }
        }

    /**
     * Gives access to private tillID variable
     * @return  tillID
     */
    public String getTillID(){
        return tillID;
    }


    /**
     * Gives access to private tillPassword variable
     * @return tillPassword
     */
    public String getTillPassword(){
        return tillPassword;
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
    }  public static void main(String[] args) {
        Login login = new Login("12345", "hello");
        System.out.println(login.getTillID());
    }
}


