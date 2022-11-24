package people;

public class Person {
    private String name;
    private String phoneNumber;
    private String id;
    private String password;

    /**
     *Create a person object
     *
     * @param name the name of the person
     * @param phoneNumber the phone number of the person
     * @param id the personal ID the person will use to login
     * @param password the password the person uses to login
     *
     */
    public Person(String name, String phoneNumber, String id, String password){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.password = password;
    }


    /**
     * @return name of person
     */
    public String getName() {
        return name;
    }

    /**
     * @param name which will be given to person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return id of a specific person
     */
    public String getId() {
        return id;
    }

    /**
     * @param id of person which is verifying login (must match password)
     * @param password of person which is verifying login (must match id)
     *
     * if the persons id matched with the passed id and persons password matched with the passed password
     *
     * @return true if id matches with its specific password (stored in people csv)
     * @return false if id doesn't match with its specific password (stored in people csv)
     */
    public boolean passwordValidator(String id, String password) {
        if (this.id.equals(id) && this.password.equals(password))
            return true;
        else return false;
    }

    /**
     * @return name, phone number, id, and password as a string
     */
    public String toString() {
        return String.format("%s,%s,%s,%s", name, phoneNumber, id, password);
    }

    public boolean equals(Person person) {
        if (this.id.equals(person.id))
            return true;
        return false;
    }
}