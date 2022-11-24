package people;

import restaurant.Utils;

public class Staff extends Person {
    private double discount;

    /**
     * @param name of staff
     * @param phoneNumber of staff
     * @param password of staff
     * super class gets values from the person superclass
     * Unique ID generated using "Utils.uniqueIdGenerator" using number 2
     * 5% discount for staff
     */
    public Staff(String name, String phoneNumber, String password){
        super(name, phoneNumber, Utils.uniqueIdGenerator("2"), password);
        discount = 0.95;
    }
    /**
     * @param name of staff
     * @param phoneNumber of staff
     * @param id of staff
     * @param password of staff
     * super class gets values from the person superclass
     * 5% discount for staff
     */
    public Staff(String name, String phoneNumber, String id, String password){
        super(name, phoneNumber, id, password); 
        discount = 0.95;
    }

   }
