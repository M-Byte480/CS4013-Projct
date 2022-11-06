package FileManipulation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class WriteData {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("example.csv"); //Need to create CSV files
        if (file.exists()){
            System.out.println("File exits");
            System.exit(0);
        }

        PrintWriter outputFile = new PrintWriter(file);

    /*    public void writeToFile(int restaurantID, int tableNo, int capacity){ //Order of CSV headers -->
            outputFile.print(restaurantID);
            outputFile.print(tableNo);
            outputFile.print(capacity);
        }
    */
    }

}
