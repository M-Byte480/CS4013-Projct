package people;

public class Owner extends Person{

private static double profit;
    /**
     * Constructor to make an Owner object
     * @param id
     * @param name
     * @param phoneNumber
     * @param email
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
