package people;

import restaurant.Utils;

public class Chef extends Person{
    private double discount;

    public Chef(String name, String phoneNumber, String id, String password){
        super(name, phoneNumber, Utils.uniqueIdGenerator("8"), password);
        discount = 0.95;
    }
    public Chef(String name, String phoneNumber, String password){
        super(name, phoneNumber, Utils.uniqueIdGenerator("8"), password);
        discount = 0.95;
    }
}
