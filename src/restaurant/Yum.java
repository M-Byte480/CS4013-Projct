package restaurant;

import java.util.ArrayList;

import till.Login;

public class Yum {
    private double overallProfit;
    private ArrayList<Restaurant> restaurants;
    private ArrayList<Login> logins;

    public Yum(ArrayList<Restaurant> restaurants, ArrayList<Login> logins) {
        overallProfit = 0;
        this.restaurants = restaurants;
        this.logins = logins;
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
