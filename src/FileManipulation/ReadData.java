package FileManipulation;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import java.io.File;

public class ReadData {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("");
        Scanner input = new Scanner(file);
        while(input.hasNext()){
            int reservationNumber = input.nextInt();
            int numberOfPeople = input.nextInt();
/*            LocalDateTime date = input.
            LocalTime time = input.
            Will need to make a separate formatter for the time and date
*/
            int tableNumber = input.nextInt();
/*            int customerId = input.nextInt();
              OR
              String customerId = input.next();
              Depending on how we want to store ID, either int or String
*/

        }
        input.close();
    }
}
