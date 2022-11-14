package restaurant;

import java.io.File;
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
            CSVReader s = new CSVReader(new File("data/invoices.csv"), true);
            id = Integer.parseInt(s.getValues().get(s.getValues().size() - 1)[s.getValues().get(0).length - 1]);
        }catch(Exception ignored){

        }
        return id;
    }
}
