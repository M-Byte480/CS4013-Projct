package people;

public class Staff extends Person {

    public Staff(Person person){
        super(person);
        this.id = "2" + ;
    }

    @Override
    public String toString() {
        return "Staff: \n" + super.toString() +
                "Title: " + title + "\n";
    }
}
