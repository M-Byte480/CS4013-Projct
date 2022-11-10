package people;

import java.util.ArrayList;

public class Person {
    private String name;
    private String phoneNumber;
    private String email;
    private String id;
    private static ArrayList<Person> people;
    private static int num = 0;
    private static int uniqueID;
    private static String password;

    public Person() {

    }

    public Person(String name, String phoneNumber, String email, String id){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = uniqueIdGenerator(id);
    }

    public static String getPassword() {
        return password;
    }


    public String uniqueIdGenerator(String id){
        uniqueID++;
        this.id = id + String.format("%07d", uniqueID);
        return this.id;
    }

    public void addPerson(Person person){
        people.add(person);
    }
    public void removePerson(Person person){
        people.remove(person);
    }

    public void getGroupPeople(String idIndicator){

    }

    public Person getPerson(String id){
        for (Person person :
                people ) {
            if(person.id.equals(id)){
                return person;
            }
        }
        return null;
    }

    @Override
    public String toString() {
         return "Person: \n" +
                 "name = '" + name + "'\n" +
                "phoneNumber = '" + phoneNumber + "'\n" +
                "email = '" + email + "'\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static ArrayList<Person> getPeople() {
        return people;
    }

    public static void setPeople(ArrayList<Person> people) {
        Person.people = people;
    }

    public static int getUniqueID() {
        return uniqueID;
    }

    public static void setUniqueID(int uniqueID) {
        Person.uniqueID = uniqueID;
    }


}
