package restaurant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Utils {
	private static int uniqueID;
	// initiate unique id on boot up

    /**
     * Generates a unique id from a specified starting number.
     * @param id
     * @return
     */
	public static String uniqueIdGenerator(String id){
        uniqueID++;
        id = id + String.format("%07d", uniqueID);
        return id;
    }

    /**
     * Sets the state of the UniqueID
     * @param id
     */
    public static void setUniqueID(int id) {
        uniqueID = id;
    }

    /**
     * Sets the invoice's uniqueID state based on the latest invoice in the CSV
     * @return The value of the latest ID
     */
    public static int getInvoiceLatestID(){
        int id = 0;

        try{
            CSVReader s = new CSVReader(new File("src/data/invoices.csv"), true);
            id = Integer.parseInt(s.getValues().get(s.getValues().size() - 1)[s.getValues().get(0).length - 1]);
        }catch(Exception ignored){
        }
        return id;
    }

    /**
     * Gets choice from an array list, using generics to allow any type to be passing in and returned.
     * Makes its own scanner.
     * @param choices
     * @return
     * @param <T>
     */
    public static <T> T getChoice(ArrayList<T> choices) {
        return getChoice(choices, new Scanner(System.in));
    }

    /**
     * Gets choice from an array list, using generics to allow any type to be passing in and returned.
     * Allows a scanner to be passed in.
     * @param choices
     * @param in
     * @return
     * @param <T>
     */
    public static <T> T getChoice(ArrayList<T> choices, Scanner in) {
        if (choices.size() == 0) {
            return null;
        }
        while (true) {
            char c = 'A';
            for (T choice : choices) {
                System.out.println(c + ") " + choice);
                c++;
            }
            System.out.println(c + ") Cancel");
            String input = in.nextLine();
            int n = input.toUpperCase().charAt(0) - 'A';
            if (0 <= n && n < choices.size())
                return choices.get(n);
            return null;
        }
    }

    /**
     * Makes the CSV Files using the name of the restaurant as a directory.
     * @param name
     * @return
     */

    public static boolean makeCSVFiles(String name) {
		File dir = new File("src/data/" + name + "/");
        if (!dir.mkdir()) return false;

        HashMap<String, String> csvNames = new HashMap<>(Map.ofEntries(
            Map.entry("invoices", "name;number;id;loyalty,tableID;time;length,productName,total,id"),
            Map.entry("products","name,description,cost,allergies"),
            Map.entry("reservations", "tableID,time,length,customerID"),
            Map.entry("tables", "tableNumber,seats")
        ));

        for (String CSVname : csvNames.keySet()) {
            File file = new File("src/data/" + name + "/" + CSVname + ".csv");
            try {
                if (!file.createNewFile()) return false;
            } catch (IOException e) {
                e.printStackTrace();
            }

            new CSVReader(file, csvNames.get(CSVname));
        }
        return true;
    }
}
