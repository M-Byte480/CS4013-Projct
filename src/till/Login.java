package till;

public class Login {
    private String ID;

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