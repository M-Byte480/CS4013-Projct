package people;

public class Owner extends Person {
    /**
     * Constructor to make an Owner object
     * @param id
     * @param phoneNumber
     * @param name
     */
    public Owner(String name, String phoneNumber, String id, String password){
        super(name, phoneNumber, id, password);
    }

    public void promoteStaff(Staff person) {
        
    }
}
