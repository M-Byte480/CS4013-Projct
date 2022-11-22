package people;

import restaurant.Utils;

public class Staff extends Person {
    private double discount;

    public Staff(String name, String phoneNumber, String password){
        super(name, phoneNumber, Utils.uniqueIdGenerator("2"), password);
        discount = 0.95;
    }
    public Staff(String name, String phoneNumber, String id, String password){
        super(name, phoneNumber, id, password); 
        discount = 0.95;
    }

    public double getDiscount(){
        return this.discount;
    }
    public void setDiscount(double discount){
        this.discount = discount;
    }
    public String toString() {
        return String.format("%s,%s", super.toString(), discount);
    }
}
