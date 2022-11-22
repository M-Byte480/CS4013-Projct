package people;

import restaurant.Utils;

public class Chef extends Person{

    public Chef(String name, String phoneNumber, String id, String password) {
        super(name, phoneNumber, Utils.uniqueIdGenerator("8"), password);
    }
    //
}
