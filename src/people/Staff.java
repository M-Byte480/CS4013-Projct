package people;

import restaurant.Utils;

public class Staff extends Person {
    private double discount;

    public Staff(String name, String phoneNumber){
        super(name, phoneNumber, Utils.uniqueIdGenerator("2"));
        discount = 0.95;
    }
    public Staff(String name, String phoneNumber, String id){
        super(name, phoneNumber, id); 
        discount = 0.95;
    }

    public double getDiscount(){
        return this.discount;
    }
    public void setDiscount(double discount){
        this.discount = discount;
    }
}
