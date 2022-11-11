package till;


import restaurant.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Login {
    private String tillPassword;
    private String ID;
    private Map<String, String> details = new HashMap();
    private Util login = new Util(new File("login.csv"));

    public Login() throws FileNotFoundException {
    }

    /**
     * @param id       - ID of person
     * @param password - Password of person
     *                 Reads the inpoutted parameters and adds them to a csv file
     * @throws IOException
     */
    public void createNewID(Util login) throws IOException {
            login.getValues().forEach(l -> {
            String[] usernameAndPasswordSplitter = l.split(", ");
            details.put(usernameAndPasswordSplitter[0], usernameAndPasswordSplitter[1]);
        });
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
        if (details.containsKey(id)) {
            return details.get(id).equals(id);
        }
        return true;
    }

    //read will read file into itself
    //split each
}








