// Milan: Last edit 28/10

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Util {
    private File file;
    private Scanner scanner;
    private ArrayList<String[]> values;

    /**
     * Create a Utility object, by passing the File name or path
     *
     * @param file File you want to read from
     * @throws FileNotFoundException Throws error if its not found
     */
    private Util(File file) throws FileNotFoundException {
        scanner = new Scanner(file);
        this.file = file;
        this.values = new ArrayList<>();
    }

    /**
     * Read file into memory
     *
     * @throws FileNotFoundException
     */
    public void read() throws FileNotFoundException {
        //        this.dataFields = reader.scanner.nextLine().split(",");
        while (scanner.hasNextLine()) {
            values.add(scanner.nextLine().split(","));
        }
    }

    /**
     * Once you done adding data, you must save to file
     *
     * @throws IOException
     */
    public void save() throws IOException {
        FileWriter fileWriter = new FileWriter(this.file);
        StringBuilder toFile = new StringBuilder();

        for (String[] line : values) {
            toFile.append(String.join(",", line)).append("\n");
        }

        fileWriter.write(toFile.toString());
        fileWriter.close();
    }

    /**
     * Add a String array, where each element matches the data fields
     *
     * @param data
     */
    public void addData(String[] data) {
        this.values.add(data);
    }

    /**
     * Add a comma seperated String
     *
     * @param data
     */
    public void addData(String data) {
        addData(data.split(","));
    }

    /**
     * Write directly to the file. this does not require the read() in method.
     *
     * @param data
     * @throws IOException
     */
    public void addDataToFile(String[] data) throws IOException {
        addDataToFile(String.join(",", data));
    }

    public void addDataToFile(String data) throws IOException {
        FileWriter fileWriter = new FileWriter(this.file, true);
        fileWriter.write(data + '\n');
        fileWriter.close();
    }

    /**
     * Close Util
     */
    public void close() {
        this.scanner.close();
    }

    /**
     * It requires to read in the file before you can edit it.
     *
     * @param dataField
     * @param value
     * @throws IOException
     */
    public void remove(String dataField, String value) {
        String[] dataFieldValues = values.get(0).clone();
        int index = -1;
        for (int i = 0; i < dataFieldValues.length; i++) {
            if (dataField.equals(dataFieldValues[i])) {
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("There was no dataField found");
            return;
        }

        for (String[] line :
                values) {
            if (line[index].equals(value)) {
                values.remove(line);
            }
        }
    }

    /**
     * Get every line where
     * @param value
     * @param dataField
     * @return
     */
    public String get(String value, String dataField) {
        StringBuilder everything = new StringBuilder();
        String[] dataFieldValues = values.get(0).clone();
        int index = -1;
        for (int i = 0; i < dataFieldValues.length; i++) {
            if (dataFieldValues[i].equals(dataField)) {
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("Failed to find the Data Field");
            return null;
        }
        for (int i = 1; i < values.size() - 1; i++) {
            if (values.get(i)[index].equals(value)) {
                everything.append(i).append(") ");
                everything.append(String.join(", ", values.get(i)));
                everything.append('\n');
            }
        }
        if(everything.toString().equals("")){
            return null;
        }

        return everything.toString();
    }

    /**
     * Returns a combination of corresponding parallel arrays of Values to DataFieldName
     * @param fields
     * @param dataFields
     * @return
     */
    public String getCombinations(String[] fields, String[] dataFields){
        int size = dataFields.length;

        if(size != fields.length){
            return null;
        }

        String[] dataFieldValues = values.get(0).clone();
        int[] indexes = new int[size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < values.get(0).length; j++) {
                if (dataFields[i].equals(dataFieldValues[j])){
                    indexes[i] = j;
                }
            }
        }
        StringBuilder everything = new StringBuilder();
        boolean incorrect = true;
        for (int line = 1; line < values.size(); line++) {
            String[] temp = values.get(line);

            for (int element : indexes) {
                if(!temp[element].equals(fields[element])){
                    incorrect = false;
                }
            }


            if(incorrect){
                everything.append(line).append(") ");
                everything.append(String.join(", ", values.get(line)));
                everything.append("\n");
            }
            incorrect = true;
        }

        return everything.toString();
    }

    /**
     * Returns a string of every line of the data Field with its corresponding line number
     * @param dataField
     * @return
     */
    public String getAll(String dataField) {
        StringBuilder everything = new StringBuilder();
        String[] dataFieldValues = values.get(0).clone();
        int index = -1;
        for (int i = 0; i < dataFieldValues.length; i++) {
            if (dataFieldValues[i].equals(dataField)) {
                index = i;
            }
        }
        if (index == -1) {
            System.out.println("Failed to find the Data Field");
            return null;
        }
        for (int i = 1; i < values.size() - 1; i++) {
            everything.append(i).append(") ").append(values.get(i)[index]).append('\n');
        }

        return everything.toString();
    }

    public int count(String value, String datafield){
        return get(value, datafield).split(value).length - 1;
    }

    public static void main(String[] args) throws IOException {
        Util hello = new Util(new File("Hello.txt"));
        hello.read();
        System.out.println(hello.get("Stephen", "firstName"));
        System.out.println(hello.count("Stephen", "firstName"));
        System.out.println(hello.getCombinations( new String[] {"Stephen","Fitzpatrick"}, new String[] {"firstName","lastName"}));
        hello.save();
        hello.close();
    }
}
