package till;
import java.util.ArrayList;


//Breny
//Adding product parameters and storing products in a product array list
public class Product {
    private String description;
    private ArrayList<String> allergies;
    private double cost;

    private ArrayList<Product> productsList;

    public Product(String description, ArrayList<String> allergies, double cost) {
        this.description = description;
        this.allergies = allergies;
        this.cost = cost;


        productsList.add(this);
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public double getCost() {
        return cost;
    }
}
