package people;

public class Customer extends Person {
   int loyalty;

    /*
    Needed to add set methods to access private data fields in Person class
     */
    public Customer(String id, String name, String phoneNumber, String email, int loyalty) {
        super();
        setId(id);
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        this.loyalty = loyalty;
    }

/*
Added for template
 */
  public void increaseLoyalty(){
  }


    public String toString(){
        return super.toString();
    }

}
