package restaurant;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Utils {
	private static int uniqueID;
	// initiate unique id on boot up

	public static String uniqueIdGenerator(String id){
        uniqueID++;
        id = id + String.format("%07d", uniqueID);
        return id;
    }

    public static int getInvoiceLatestID(){
        int id = 0;

        try{
            CSVReader s = new CSVReader(new File("src/data/invoices.csv"), true);
            id = Integer.parseInt(s.getValues().get(s.getValues().size() - 1)[s.getValues().get(0).length - 1]);
        }catch(Exception ignored){

        }
        return id;
    }
    
    public static <T> T getChoice(ArrayList<T> choices) {
        Scanner in = new Scanner(System.in);
        if (choices.size() == 0) {
            in.close();
            return null;
        }
        while (true) {
            char c = 'A';
            for (T choice : choices) {
                System.out.println(c + ") " + choice);
                c++;
            }
            String input = in.nextLine();
            int n = input.toUpperCase().charAt(0) - 'A';
            if (0 <= n && n < choices.size())
            in.close();
            return choices.get(n);
        }
    }

    public static String getStringTime(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
    }

    public static boolean makeCSVFiles(String name) {
		File dir = new File("src/data/" + name + "/");
        if (!dir.mkdir()) return false;

        HashMap<String, String> csvNames = new HashMap<>(Map.ofEntries(
            Map.entry("invoices", "name;number;id;loyalty,tableID;time;length,productName,total,id\n"),
            Map.entry("login", "ID,Password\n"),
            Map.entry("people", "name,phoneNumber,id,loyalty/loyalty\n"),
            Map.entry("products","name,description,cost,allergies\n"),
            Map.entry("reservations", "tableID,time,length\n"),
            Map.entry("tables", "tableNumber,seats\n")
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
