<<<<<<< HEAD:src/Yum.java
import java.util.ArrayList;
=======
package restaurant;
>>>>>>> main:src/restaurant/Yum.java

public class Yum {
    private double overallProfit;
    private ArrayList<Restaurant> restaurants;

    public Yum() {
        overallProfit = 0;
        restaurants = new ArrayList<>();
    }
    public void addRestaurant() {
        restaurants.add(new Restaurant());
    }
}
