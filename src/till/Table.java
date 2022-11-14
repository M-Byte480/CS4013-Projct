package till;

import java.util.ArrayList;

public class Table {
    private int tableNumber;
    private int seats;
    private ArrayList<Product> products;
    public boolean isTableFree = false;

    public Table(int tableNumber, int seats) {
        this.tableNumber = tableNumber;
        this.seats = seats;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    public void addProducts(Product p) {
        products.add(p);
    }
    public void removeProduct(Product p) {
        products.remove(p);
    }

    public void clearFood() {
        for (Product p:products) {
            products.remove(p);
        }
    }

	public double getTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getCost();   
        }
		return total;
	}

    public void closeTable(){
        this.isTableFree = true;
        System.out.println("Table has been closed.");
    }
}

