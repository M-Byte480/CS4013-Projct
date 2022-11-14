package till;

import java.util.ArrayList;

public class Table {
    private int tableNumber;
    private int seats;
    private ArrayList<Product> onTable;

    public Table(int tableNumber, int seats) {
        this.tableNumber = tableNumber;
        this.seats = seats;
    }

    public int getSeats(){
        return seats;
    }
    public void addToTable(Product p) {
        onTable.add(p);
    }

    public void removeFromTable(Product p) {
        onTable.remove(p);
    }

    public Product[] addProduct() {
        Product[] visible = new Product[onTable.size()];
        for (Product x : onTable) {
            visible[onTable.indexOf(x)] = x;

        }
        return visible;
    }

    public Product[] removeProduct() {
        Product[] ifExists = new Product[onTable.size()];
        for (Product x : onTable) {
            ifExists[onTable.indexOf(x)] = x;
        }
        return ifExists;

    }

    public ArrayList<Product> getProducts() {
        return onTable;
    }

    public void clearFood(Table t){
        for (Product p:onTable) {
            removeFromTable(p);
        }
    }
}

