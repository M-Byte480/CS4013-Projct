package people;

import restaurant.Utils;

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
    public Owner(String name, String phoneNumber, String password){
        super(name, phoneNumber, Utils.uniqueIdGenerator("9"), password);
    }

    public void promoteStaff(Staff person) {
        
    }
}
