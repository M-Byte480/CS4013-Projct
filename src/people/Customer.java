package people;

public class Customer extends Person {
    double loyalty;
    int freqOfVisits;

    Customer(){

    }

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



    /**
     * Overrides toString from Person class because a Customer has more
     * specific attributes.
     * Customer doesn't contain an address.
     * Customer contains an id and a loyalty value.
     * @return toString of Customer
     */
    @Override
    public String toString(){
        return "Customer: \n" +
                "name = '" + getName() + "'\n" +
                "phoneNumber = '" + getPhoneNumber() + "'\n" +
                "email = '" + getEmail() + "'\n" +
                "id = '" + getId() + "'\n" +
                "loyalty = '" + loyalty + "'\n";
    }
    public String customerDetails(){
        String out = getName()  + "," + getPhoneNumber() + "," + getEmail() + "," + getId() + "," + loyalty;
        return out;
    }

}