package people;

import restaurant.Utils;

public class Customer extends Person {
    private double loyalty;

    /**
     * Constructor to create a customer object with auto generated id
     * @param name
     * @param phoneNumber
     * @param loyalty
     */
    public Customer(String name, String phoneNumber, double loyalty){
        super(name, phoneNumber, Utils.uniqueIdGenerator("2"));
        this.loyalty = loyalty;
    }
        /**
     * Constructor to create a customer object with a certain id
     * @param name
     * @param phoneNumber
     * @param id
     * @param loyalty
     */
    public Customer(String name, String phoneNumber, String id, double loyalty) {
        super(name, phoneNumber, id);
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
    public String toString() {
        return String.format("%s,%s", super.toString(), loyalty);
    }
}