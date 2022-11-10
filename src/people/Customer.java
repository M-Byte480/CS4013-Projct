package people;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import restaurant.*;
import people.Customer;



public class Customer extends Person {
    double loyalty;
    int freqOfVisits;

    Customer(){

    }



    /**
     * Constructor to create a customer object
     * @param id
     * @param name
     * @param phoneNumber
     * @param email
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
    public String[] customerDetailsToStringArr(){
        ArrayList<String> cust = new ArrayList();
        cust.add(getName());
        cust.add(getPhoneNumber());
        cust.add(getEmail());
        cust.add(getId());
        cust.add(Double.toString(loyalty));
        String[] custDetails = new String[6];
        for (int i = 0; i < custDetails.length; i++){
            custDetails[i] = cust.get(i);
        }
        return custDetails;
    }



    public void addCustomerToPeople() throws IOException {
        Customer customer = this;
        Util fileWriter = new Util(new File("src/data/people.csv"));
        System.out.println("Writting");
        fileWriter.addDataToFile(new String [] {customer.getName(), customer.getPhoneNumber(), customer.getEmail(), customer.getId(), Double.toString(loyalty)});
        System.out.println("Written");
        fileWriter.close();
    }
    public void addCustomer() throws IOException {
        Customer customer = this;
        Util fileWriter = new Util(new File("src/data/people.csv"));
        System.out.println("Sucessfully written");
        fileWriter.addDataToFile(customer.customerDetails());
        fileWriter.close();
    }



}
