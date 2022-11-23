package till;

public class Login {
    private String password;
    private String ID;

    public Login(String ID, String password){
        this.ID = ID;
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean passwordValidator(String ID, String password) {
        if (this.ID.equals(ID) && this.password.equals(password)) 
            return true;
        else return false;
    }
}








