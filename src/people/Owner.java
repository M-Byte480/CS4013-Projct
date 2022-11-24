package people;

import restaurant.Utils;

public class Owner extends Person {
    /**
     * Constructor to make an Owner object
     * @param name of owner
     * @param phoneNumber of owner
     * @param id of owner
     * @param password of owner
     * super class gets values from the person superclass
     */
    public Owner(String name, String phoneNumber, String id, String password){
        super(name, phoneNumber, id, password);
    }
    /**
     * Constructor to make an Owner object
     * @param name of owner
     * @param phoneNumber of owner
     * @param password of owner
     * super class gets values from the person superclass
     * Unique ID generated using "Utils.uniqueIdGenerator" which starts with 9
     */
    public Owner(String name, String phoneNumber, String password){
        super(name, phoneNumber, Utils.uniqueIdGenerator("9"), password);
    }

    public void promoteStaff(Staff person) {
        
    }
}
