package till;


import restaurant.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Login {
    private String tillPassword;
    private String ID;
    private ArrayList<String> details = new ArrayList<String>();

    public Login() throws FileNotFoundException {
    }

    /**
     * @param id - ID of person
     * @param password - Password of person
     * Reads the inpoutted parameters and adds them to a csv file
     * @throws IOException
     */
    public void createNewID(String id, String password) throws IOException {
        this.ID = ID;
        this.tillPassword = tillPassword;

        Util u = new Util(new File("login.csv"));
        u.addDataToFile(details + "\n");

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
     * @param password
     * Make Hashmap
     * For each line in the csv split using regex and store in hash map
     * if correct key and pass allow logine
     * else
     * return  false
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








