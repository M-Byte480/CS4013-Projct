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

    /**
     * @param restaurants within the Yum chain
     * @param people within the Yum chain
     * @param owner of the Yum chain
     * Profit set to 0 as Yum object has no sales
     */
    public Yum(ArrayList<Restaurant> restaurants, HashMap<String, Person> people, Owner owner) {
        overallProfit = 0;
        this.restaurants = restaurants;
        this.people = people;
        this.owner = owner;
    }

    /**
     * @return restaurant (arraylist of Restaurant objects)
     */
    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    /**
     * @param name of restaurant,  you would like to add restaurants restaurant (arraylist of Restaurant objects)
     */
    public void addRestaurant(String name) {
        restaurants.add(new Restaurant(name));
    }
    public void removeRestaurant(String name) {
        restaurants.remove(new Restaurant(name));
    }

    /**
     * @return people (arraylist of Person objects)
     */
    public ArrayList<Person> getPeople() {
        return new ArrayList<Person>(people.values());
    }

    /**
     * @param person you would like to add to people (arraylist of Person objects) using persons ID
     */
    public void addPerson(Person person) {
        people.put(person.getId(), person);
    }

    /**
     * @param id of Person you would like to get from people (arraylist of Person objects)
     * @return Person object which correlates with id passed
     */
    public Person getPerson(String id) {
        return people.get(id);
    }

    /**
     * @param person you would like to remove from people (arraylist of Person objects)
     */
    public void removePerson(Person person) {
        people.remove(person.getId());
    }

    /**
     * @return owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * @param owner is used to set owner of Yum
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     *
     */
    public void save() {
        CSVReader resFile = new CSVReader(new File("src\\data\\restaurants.csv"), false);
        CSVReader peopleFile = new CSVReader(new File("src\\data\\people.csv"), false);

        restaurants.forEach(res -> {
            resFile.addDataToSystem(res.toString());
        });

        people.forEach((id, person) -> {
            peopleFile.addDataToSystem(person.toString());
        });

        resFile.saveToCSV();
        peopleFile.saveToCSV();
    }
}
