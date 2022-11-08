package people;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        Customer milan = new Customer("Milan", "Limerick", "083", "gmail", "20120", 2.0);
        milan.addCustomer();
        System.out.println("Finished");
    }
}
