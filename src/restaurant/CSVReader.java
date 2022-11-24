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
     *
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

    public CSVReader(File file, String datafields) {
        this.file = file;
        this.values = new ArrayList<>();
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        appendToFile(datafields);
    }

    /**
     * Reads CSV file into the system
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
     * Get line where dataField = value
     *
     * @param value
     * @param dataField
     * @return String
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

    public ArrayList<String[]> getValues() {
        return values;
    }
}
