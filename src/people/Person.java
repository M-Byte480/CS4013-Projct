package people;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Person {
    private String address;
    private String name;
    private String phoneNumber;
    private String email;
    private String id;
    private static ArrayList<Person> people;
<<<<<<< HEAD
    private static int num = 0;
=======
    private static int uniqueID;
    private static String password;
>>>>>>> 6ccdd289414e669e2d7c7032178b3ec76ab81dd6

    public Person() {

    }

    public Person(String name, String address, String phoneNumber, String email, String id){
        this.address = address;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = uniqueIdGenerator(id);
    }

<<<<<<< HEAD
    private String uniqueIdGenerator(String id) {
        return id + String.format("%09d", num+1);
        // boolean exist = false;
        // String randomID = new String();

        // outter:
        // while(!exist) {
        //     randomID = ((Double) Math.random()).toString().substring(2);
        //     randomID = id + randomID;
        //     for (int i = 0; i < people.size(); i++) {
        //         if(people.get(i).getID().equals(randomID)){
        //             continue outter;
        //         }
        //     }
        //     exist = true;
        // }
        // this.id = randomID;
=======
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
>>>>>>> 6ccdd289414e669e2d7c7032178b3ec76ab81dd6
    }

    @Override
    public String toString() {
         return "Person: \n" +
                 "name = '" + name + "'\n" +
                "address = '" + address + "'\n" +
                "phoneNumber = '" + phoneNumber + "'\n" +
                "email = '" + email + "'\n";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Object getName() {
    return name;
    }
    }
