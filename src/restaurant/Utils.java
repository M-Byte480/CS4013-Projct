package restaurant;

public class Utils {
	private static int uniqueID;
	// initiate unique id on boot up

	public static String uniqueIdGenerator(String id){
        uniqueID++;
        id = id + String.format("%07d", uniqueID);
        return id;
    }
}
