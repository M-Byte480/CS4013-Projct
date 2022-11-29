// Milan: Last edit 28/10

package restaurant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVReader {
    private File file;
    private Scanner scanner;
    private ArrayList<String[]> values;
    private String[] dataFields;

    /**
     * Create a Utility object, by passing the File name or path
     * toRead asks for a boolean to read the data into the Parameters
     * @param file File you want to read from
     */
    public CSVReader(File file, boolean toRead) {
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        dataFields = scanner.nextLine().split(",");
        this.file = file;
        this.values = new ArrayList<>();
        if (toRead) readIntoSystem();
    }

    /**
     * Creates a CSVReader with specified String (CSV) to be appended to the file
     * It will have the datafields created, and you can add values to it which will be saved
     * to the File (which is possibly newly created and passed)
     * @param file
     * @param datafields
     */
    public CSVReader(File file, String datafields) {
        this.file = file;
        this.values = new ArrayList<>();
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ignored) {

        }
        appendToFile(datafields);
    }

    /**
     * Reads CSV file into the system (the datafields)
     */
    private void readIntoSystem() {
        while (scanner.hasNextLine()) {
            values.add(scanner.nextLine().split(","));
        }
    }

    /**
     * Save the system to CSV
     */
    public void saveToCSV() {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            StringBuilder toFile = new StringBuilder();

            toFile.append(String.join(",", dataFields)).append("\n");

            for (String[] line : values) {
                toFile.append(String.join(",", line)).append("\n");
            }

            fileWriter.write(toFile.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a String array, where each element matches the data fields
     * to the system
     * @param data String of comma seperated files
     */
    public void addDataToSystem(String[] data) {
        this.values.add(data);
    }

    /**
     * Add a comma seperated String
     *
     * @param data
     */
    public void addDataToSystem(String data) {
        addDataToSystem(data.split(","));
    }

    /**
     * Appends the comma seperated data to the end of the file
     * @param data String of comma seperated files
     */
    public void appendToFile(String data) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(data + '\n');
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a line of data from the CSV where the
     * the datafield (column) matches the value passed
     *
     * @param value String
     * @param dataField String
     * @return String of line
     */
    public String getData(String value, String dataField) {
        StringBuilder dataLinesString = new StringBuilder();
        String[] dataFieldValues = this.dataFields;

        int index = -1;

        for (int i = 0; i < dataFieldValues.length; i++) {
            if (dataFieldValues[i].equals(dataField)) {
                index = i;
            }
        }

        for (String[] strings : values) {
            if (strings[index].equals(value)) {
                dataLinesString.append(String.join(",", strings));
            }
        }

        return dataLinesString.toString();
    }

    /**
     * Returns each line of the CSV as a String[] in the collection of an ArrayList
     * @return
     */
    public ArrayList<String[]> getValues() {
        return values;
    }
}
