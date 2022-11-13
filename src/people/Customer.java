package people;

public class Customer extends Person {
    double loyalty;
    int freqOfVisits;

    Customer(){

    }

    /**
     * Constructor to create a customer object
     * @param name
     * @param phoneNumber
     * @param email
     * @param id
     * @param loyalty
     */
    public Customer(String name, String phoneNumber, String email, String id, double loyalty) {
        super();
        setId(id);
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        this.loyalty = loyalty;
    }

    /**
     * Method to increase loyalty value of customer depending on freq of visits
     */
    public void increaseLoyalty() {

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