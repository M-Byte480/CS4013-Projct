package till;

import restaurant.CSVReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Product {
    private String name;
    private String description;
    private ArrayList<String> allergies;
    private double cost;

    // public static ArrayList<Product> productsOfRestaurant;

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
    
    // public static ArrayList<Product> getProducts() {
    //     return productsOfRestaurant;
    // }

    // public void removeFood(Product product) {
    //     productsOfRestaurant.remove(product);
    //     CSVReader readProducts = new CSVReader(new File("src/data/products.csv"));
    //     readProducts.remove("name", product.getName());
    //     readProducts.save();
    //     readProducts.close();
    // }

    // public static void loadProducts() {
    //     CSVReader readProducts = new CSVReader(new File("/src/data/products.csv"));
    //     for (int i = 1; i < readProducts.getValues().size(); i++) {
    //         String[] details = readProducts.getValues().get(i);
    //         productsOfRestaurant.add(new Product(
    //             details[0],
    //             details[1],
    //             Double.parseDouble(details[2]),
    //             new ArrayList<String> (Arrays.asList(details[3].split("-")))
    //         ));
    //     }
    //     readProducts.close();
    // }



    public String toString() {
        StringBuilder retString = new StringBuilder(String.format("%s,%s,%f,", name, description, cost  + "n"));
        allergies.forEach(s -> {
            retString.append(s).append(";");
        });
        return retString.deleteCharAt(retString.length()-1).toString();
    }
}
