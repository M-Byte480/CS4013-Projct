package people;

import restaurant.Utils;


public class Chef extends Person {
    private double discount;

    /**
     * Creates a chef object
     *
     * @param name the name of the chef
     * @param phoneNumber the phone number of the chef
     * @param password the password the chef uses to log in
     * Unique ID generated using "Utils.uniqueIdGenerator" which starts with 8
     * 5% discount for staff
     */
    public Chef(String name, String phoneNumber, String id, String password) {
        super(name, phoneNumber, id, password);
        discount = 0.95;
    }

    /**
     *Create a chef object
     *
     * @param name the name of the chef
     * @param phoneNumber the phone number of the chef
     * @param password the password the chef uses to log in
     * Unique ID generated using "Utils.uniqueIdGenerator" which starts with 9
     * 5% discount for staff
     */
    public Chef(String name, String phoneNumber, String password) {
        super(name, phoneNumber, Utils.uniqueIdGenerator("8"), password);
        discount = 0.95;
    }


}
