package till;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<Product> products;
    private Scanner in;

    public Menu(){
        this.in = new Scanner(System.in);
    }


    public void run() {

    }

    // This code was taken from Micheal
    private Object getChoice(Object[] choices) {

        while (true) {
            char c = 'A';
            for (Object choice : choices) {
                System.out.println(c + ") " + choice);
                c++;
            }
            String input = in.nextLine();
            int n = input.toUpperCase().charAt(0) - 'A';
            if (0 <= n && n < choices.length)
                return choices[n];
        }
    }
}

