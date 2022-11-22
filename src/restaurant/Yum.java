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

    public ArrayList<Login> getLogins() {
        return logins;
    }
    public void addLogin(Login login) {
        logins.add(login);
    }
    public void removeLogin(Login login) {
        logins.remove(login);
    }
    
    public void addRestaurant(String name) {
        restaurants.add(new Restaurant(name));
    }
    public void removeRestaurant(String name) {
        restaurants.remove(new Restaurant(name));
    }

    public double getOverallProfit() {
        return overallProfit;
    }
    public void addOverallProfit(double overallProfit) {
        this.overallProfit += overallProfit;
    }
}
