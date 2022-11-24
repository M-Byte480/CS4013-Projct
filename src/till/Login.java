package till;

public class Login {
    private String password;
    private String ID;

    /**
     * Creates a Login object with a specified ID and password
     * @param ID
     * @param password
     */
    public Login(String ID, String password){
        this.ID = ID;
        this.password = password;
    }

    /**
     * Returns the ID of the login
     * @return ID
     */
    public String getID() {
        return ID;
    }

    /**
     * Returns the password of the login
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the ID for the login
     * @param ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * Checks if the login id and password is valid
     * @param ID
     * @param password
     * @return true if the password is valid
     * @return false if the password isn't valid
     */
    public boolean passwordValidator(String ID, String password) {
        if (this.ID.equals(ID) && this.password.equals(password)) 
            return true;
        else return false;
    }
}








