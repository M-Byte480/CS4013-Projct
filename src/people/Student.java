package people;

public class Student extends Person {

    private int yearOfStudy;
    private String programmeOfStudy;

    public Student(String name,  String address, String phoneNumber, String email, int yearOfStudy, String programmeOfStudy) {
        super(name, phoneNumber, address, email);
        this.yearOfStudy = yearOfStudy;
        this.programmeOfStudy = programmeOfStudy;
    }

    @Override
    public String toString() {
        return "Student: \n" + super.toString() +
                "Year of Study: " + yearOfStudy +
                "\nProgramme of Study: " + programmeOfStudy + "\n";
    }
}