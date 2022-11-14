package restaurant;

import java.util.ArrayList;

public class Yum {
    private double overallProfit;
    private ArrayList<Restaurant> restaurants;

    public Yum() {
        overallProfit = 0;
        restaurants = new ArrayList<>();
    }
    
    public void addRestaurant(String name) {
        restaurants.add(new Restaurant(name));

        // make .csv files
    }

    public double getOverallProfit() {
        return overallProfit;
    }
    public void addOverallProfit(double overallProfit) {
        this.overallProfit += overallProfit;
    }

}
