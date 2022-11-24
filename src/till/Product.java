package till;

import java.util.ArrayList;

public class Product {
    private String name;
    private String description;
    private ArrayList<String> allergies;
    private double cost;

    /**
     * @param name of product
     * @param description of product
     * @param cost of product
     * @param allergies within the product
     */
    public Product(String name, String description, double cost, ArrayList<String> allergies) {
        this.description = description;
        this.allergies = allergies;
        this.cost = cost;
        this.name = name;
     }

    /**
     * @return name of specified product
     */
    public String getName(){
        return this.name;
    }

    /**
     * @return price of specified product
     */
    public double getCost() {
        return cost;
    }

    /**
     * @return name, despriction cost and allergies as a formatted string
     */
    public String toString() {
        StringBuilder retString = new StringBuilder(String.format("%s,%s,%.2f,", name, description, cost));
        allergies.forEach(s -> {
            retString.append(s).append(";");
        });
        return retString.deleteCharAt(retString.length()-1).toString();
    }
}
