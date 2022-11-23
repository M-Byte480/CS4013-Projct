package till;

import java.util.ArrayList;

public class Product {
    private String name;
    private String description;
    private ArrayList<String> allergies;
    private double cost;

    public Product(String name, String description, double cost, ArrayList<String> allergies) {
        this.description = description;
        this.allergies = allergies;
        this.cost = cost;
        this.name = name;



        // CSVReader readProducts = new CSVReader(new File("src/data/products.csv"));
        // readProducts.addDataToFile(new String[] {name, description, cost + "", String.join( "-", allergies)});
    }
    
    public String getName(){
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }
    
    public double getCost() {
        return cost;
    }

    public String toString() {
        StringBuilder retString = new StringBuilder(String.format("%s,%s,%f,", name, description, cost  + "n"));
        allergies.forEach(s -> {
            retString.append(s).append(";");
        });
        return retString.deleteCharAt(retString.length()-1).toString();
    }
}
