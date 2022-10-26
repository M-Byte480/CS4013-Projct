package people;

public class Owner extends Person{

    /*
    Needed to add set methods to access private data fields in Person class
     */
    public Owner(String id, String name, String phoneNumber, String email){
        super();
        setId(id);
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    /*
    Added for template
    */
    public void promoteStaff(Staff person){
    }

}
