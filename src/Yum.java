import java.util.ArrayList;

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
