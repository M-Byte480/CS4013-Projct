package people;

// Hello World
public class Person {
    private String address;
    private String name;
    private String phoneNumber;
    private String email;


    public Person(String name, String address, String phoneNumber, String email){
        this.address = address;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
         return "Person: \n" +
                 "name = '" + name + "'\n" +
                "address = '" + address + "'\n" +
                "phoneNumber = '" + phoneNumber + "'\n" +
                "email = '" + email + "'\n";
    }
}
