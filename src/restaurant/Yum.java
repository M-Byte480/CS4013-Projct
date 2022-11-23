package restaurant;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import people.Owner;
import people.Person;

public class Yum {
    private double overallProfit;
    private ArrayList<Restaurant> restaurants;
    private HashMap<String, Person> people;
    private Owner owner;

    public Yum(ArrayList<Restaurant> restaurants, HashMap<String, Person> people, Owner owner) {
        overallProfit = 0;
        this.restaurants = restaurants;
        this.people = people;
        this.owner = owner;
    }
    
    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }
    public void addRestaurant(String name) {
        restaurants.add(new Restaurant(name));
    }
    public void removeRestaurant(String name) {
        restaurants.remove(new Restaurant(name));
    }

    public double getOverallProfit() {
        return overallProfit;
    }
    public void addOverallProfit(double overallProfit) {
        this.overallProfit += overallProfit;
    }

    public ArrayList<Person> getPeople() {
        return new ArrayList<Person>(people.values());
    }
    public void addPerson(Person person) {
        people.put(person.getId(), person);
    }
    public Person getPerson(String id) {
        return people.get(id);
    }
    public void removePerson(Person person) {
        people.remove(person.getId());
    }

    public Owner getOwner() {
        return owner;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void save() {
        CSVReader resFile = new CSVReader(new File("src/data/restaurants.csv"), false);
        CSVReader peopleFile = new CSVReader(new File("src/data/people.csv"), false);

        restaurants.forEach(res -> {
            resFile.addDataToSystem(res.toString());
        });

        peopleFile.addDataToSystem(owner.toString());
        people.forEach((id, person) -> {
            peopleFile.addDataToSystem(person.toString());
        });

        resFile.saveToCSV();
        peopleFile.saveToCSV();
    }
}
