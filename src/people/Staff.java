package people;

import restaurant.Utils;

public class Staff extends Person {

    private String discount;
    private static String password;

    public Staff(String name, String phoneNumber, String email){
        super(name, phoneNumber, email, Utils.uniqueIdGenerator("2"));
    }
    public Staff(String name, String phoneNumber, String email, String id){
        super(name, phoneNumber, email, id);
    }

    public Staff() {
    }

    public void setPassword(String password){
        this.password = password;
    }

    public static String getPassword() {
        return password;
    }

    public String getDiscount(){
        return this.discount;
    }

    public void setDiscount(String discount){
        this.discount = discount;
    }


    public String CSVToString() {
        return String.format("%s,%s,%s,%s", super.getName(), super.getPhoneNumber(), super.getEmail(), super.getId());
    }

    @Override
    public String toString() {
        return "Staff: \n" + super.toString();
    }
}
