package people;

public class Staff extends Person {

    private String discount;
    private static String password;

    public Staff(String name, String address, String phoneNumber, String email){
        super(name, address, phoneNumber, email, "2");
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




    @Override
    public String toString() {
        return "Staff: \n" + super.toString();
    }
}
