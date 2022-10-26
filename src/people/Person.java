package people;

import java.awt.*;
import java.util.ArrayList;

// Hello World
public class Person {
    private String address;
    private String name;
    private String phoneNumber;
    private String email;
    private String id;
    private static ArrayList<Person> people;


    public Person(String name, String address, String phoneNumber, String email, String id){
        this.address = address;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id.uniqueIdGenerator(id);
    }

    public void uniqueIdGenerator(String id){
        boolean exist = false;
        String randomID = new String();

        outter:
        while(!exist) {
            randomID = ((Double) Math.random()).toString().substring(2);
            randomID = id + randomID;
            for (int i = 0; i < people.size(); i++) {
                if(people.get(i).getID().equals(randomID)){
                    continue outter;
                }
            }
            exist = true;
        }
        this.id = randomID;
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
