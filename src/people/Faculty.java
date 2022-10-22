package people;

public class Faculty extends Employee{
    private String rank;
    private String hours;

    public Faculty(String name,  String address, String phoneNumber, String email, String office, double salary, String hours, String rank) {
        super(name, phoneNumber, address, email, office, salary);
        this.rank = rank;
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "Faculty: \n" + super.toString() +
                "Rank: " + rank +
                "\nHours: " + hours + "\n";
    }
}
