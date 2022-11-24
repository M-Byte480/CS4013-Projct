package people;

import restaurant.Utils;

public class Customer extends Person {
    private double loyalty;

    /**
     * Constructor to create a customer object with auto generated id
     * @param name of customer
     * @param phoneNumber of customer
     * @param loyalty of customer
     */
    public Customer(String name, String phoneNumber, String password, double loyalty){
        super(name, phoneNumber, Utils.uniqueIdGenerator("0"), password);
        this.loyalty = loyalty;
    }
        /**
     * Constructor to create a customer object with a certain id
         * @param name of customer
         * @param phoneNumber of customer
         * @param id of customer
         * @param password of customer
         * @param loyalty of customer
         */
    public Customer(String name, String phoneNumber, String id, String password, double loyalty) {
        super(name, phoneNumber, id, password);
        this.loyalty = loyalty;
    }

    public double getLoyalty() {
        return loyalty;
    }
    public void setLoyalty(double loyalty) {
        this.loyalty = loyalty;
    }
    public void increaseLoyalty() {
        loyalty++;
    }

    /**
     * @return name, phone number, id, password and loyalty as a string
     */
    public String toString() {
        return String.format("%s,%s", super.toString(), loyalty);
    }
}