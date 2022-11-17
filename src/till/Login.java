package till;



import restaurant.CSVReader;
import restaurant.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Login {
    private String tillPassword;
    private String ID;
    private ArrayList<String> details = new ArrayList<String>();
    private ArrayList<Login> logins = new ArrayList<Login>();

    public Login(String ID, String tillPassword) {
        this.ID = ID;
        this.tillPassword = tillPassword;
    }

    /**
     * @param id - ID of person
     * @param password - Password of person
     *                 Reads the inpoutted parameters and adds them to a csv file
     */
    public void createNewID(Utils login) {
        login.getValues().forEach(l -> {
            String[] usernameAndPasswordSplitter = l.split(", ");
            details.add(usernameAndPasswordSplitter[0], usernameAndPasswordSplitter[1]);
        });
    }
    public void createNewID(String ID, String tillPassword) {
        this.ID = ID;
        this.tillPassword = tillPassword;

        CSVReader u = new CSVReader(new File("login.csv"));
        u.appendToFile(details + "\n");

    }

    public void addLogin(Login login){
        logins.add(login);
    }

    public String getID() {
        return ID;
    }

    public String getTillPassword() {
        return tillPassword;
    }

    public ArrayList<Login> getLogins() {
        return logins;
    }

    public boolean loginExistence(Login login){
        boolean exists = false;
        for (int i = 0; i < logins.size(); i++){
            if (logins.get(i).getID().equals(login.getID())
                    && logins.get(i).getTillPassword().equals(login.getTillPassword())){
                exists = true;
            }
        }
        return exists;
    }


    /**
     * @param path
     * @return
     */
    public List<String> getLines(String path) {
        List<String> lines = new ArrayList();
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lines;
    }


    /**
     * @param id
     * @param password Make Hashmap
     *                 For each line in the csv split using regex and store in hash map
     *                 if correct key and pass allow logine
     *                 else
     *                 return  false
     * @return
     */
    public boolean validateLogin(String id, String password) {
        for (int i = 0; i < details.size() ; i++) {
            getLines("login.csv");


            if (login.containsKey(id)) {
                return login.get(id).equals(password);
            }
            return false;
        }

    //read will read file into itself
    //split each
}
}








