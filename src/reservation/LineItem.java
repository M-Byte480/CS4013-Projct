package reservation;

import till.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class LineItem {
    private String id;
    private int quantity;
    private double unitPrice;
    private double total;

    public LineItem(String id, int quantity, double unitPrice){
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.total = quantity * unitPrice;
    }

    // Move this block of code to table and invoke it through the table of products
    public static ArrayList<LineItem> convertToLineItems(ArrayList<Product> products){
        ArrayList<LineItem> items = new ArrayList<>();
        HashMap<Product, Integer> occurences = new HashMap<>();
        Integer count = 0;
        for (Product p : products) {
            count = occurences.get(p.name);
            if(count == null){
                occurences.put(p.name, 1);
            }else{
                occurences.replace(p.name, count + 1);
            }
        }

        for (HashMap.Entry<Product, Integer> item :
                occurences.entrySet()) {
            items.add(new LineItem(item.getKey().getName(), item.getValue(), item.getKey().getCost()));
        }

        return items;
    }

    public void increaseQuantity(LineItem item){
        quantity++;
    }

    public String toString(){
        return String.format("%12S%12S%12S€%5S",id, quantity,unitPrice,total);
    }
}
