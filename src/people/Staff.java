package people;

public class Staff extends Person {

    public Staff(Person person){
        super();
        this.id = "2" + un ;
    }


    @Override
    public String toString() {
        return "Staff: \n" + super.toString() +
                "Title: " + title + "\n";
    }
}
