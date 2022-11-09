package till;

import restaurant.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

//Breny
//Adding product parameters and storing products in a product array list
public class Product {
    private String name;
    private String description;
    private ArrayList<String> allergies;
    private double cost;

    public static ArrayList<Product> productsOfRestaurant;

    public Product(String name, String description, double cost, ArrayList<String> allergies) throws IOException {
        this.description = description;
        this.allergies = allergies;
        this.cost = cost;
        this.name = name;

        productsOfRestaurant.add(this);

        Util readProducts = new Util(new File("src/data/products.csv"));
        readProducts.addDataToFile(new String[] {name, description, cost + "", String.join( "-", allergies)});
    }

    public void removeFood(Product product) throws IOException {
        productsOfRestaurant.remove(product);
        Util readProducts = new Util(new File("src/data/products.csv"));
        readProducts.read();
        readProducts.remove("name", product.getName());
        readProducts.save();
        readProducts.close();
    }

    public ArrayList<Product> getProductsList() {
        return productsOfRestaurant;
    }

    public static void loadProducts() throws IOException{
        Util readProducts = new Util(new File("/src/data/products.csv"));
        readProducts.read();
        for (int i = 1; i < readProducts.getValues().size(); i++) {
            String[] details = readProducts.getValues().get(i);
            productsOfRestaurant.add(new Product(
                details[0],
                details[1],
                Double.parseDouble(details[2]),
                new ArrayList<String> (Arrays.asList(details[3].split("-")))));
        }
        readProducts.close();
    }

    public double getCost() {
        return cost;
    }

    public String getName(){
        return this.name;
    }



}
