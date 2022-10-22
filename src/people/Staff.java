package people;

public class Staff extends Employee {
    private String title;

    public Staff(String name, String address, String phoneNumber, String email, String office, double salary, String title){
        super(name, phoneNumber, address, email, office, salary);
        this.title = title;
    }

    @Override
    public String toString() {
        return "Staff: \n" + super.toString() +
                "Title: " + title + "\n";
    }
}
