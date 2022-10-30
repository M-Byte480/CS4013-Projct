package people;

public class Staff extends Person {

    private String discount;
    private String password;

    public Staff(String name, String address, String phoneNumber, String email){
        super(name, address, phoneNumber, email, "2");
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getDiscount(){
        return this.discount;
    }
    public void setDiscount(String discount){
        this.discount = discount;
    }

    public void getStaffMembers(){

    }


    @Override
    public String toString() {
        return "Staff: \n" + super.toString();
    }
}
