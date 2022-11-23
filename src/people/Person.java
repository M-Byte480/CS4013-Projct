package people;

public class Person {
    private String name;
    private String phoneNumber;
    private String id;
    private String password;

    public Person(String name, String phoneNumber, String id, String password){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean passwordValidator(String id, String password) {
        if (this.id.equals(id) && this.password.equals(password))
            return true;
        else return false;
    }
    public String toString() {
        return String.format("%s,%s,%s,%s", name, phoneNumber, id, password);
    }
}