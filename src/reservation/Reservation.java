package reservation;

import people.Customer;
import till.Product;
import till.Table;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reservation {
	private Table table;
	private Customer cust;
	
	private LocalDateTime time;
	private Duration length;
	
	/**
	 * Makes a Reservation object.
	 * @param table
	 * @param time
	 * @param length
	 */
	public Reservation(Table table, LocalDateTime time, Duration length) {
		this.table = table;
		this.time = time;
		this.length = length;
	}
	
	/**
	 * Gets the table that the reservation is made for.
	 * @return table
	 */
	public Table getTable() {
		return table;
	}
	
	/** 
	 * Returns the customer.
	 * @return Customer
	 */
	public Customer getCust() {
		return cust;
	}
	
	/** 
	 * Sets the customer.
	 * @param cust
	 */
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	/**
	 * Gets the time the reservation is made for.
	 * @return time
	 */
	public LocalDateTime getTime() {
		return time;
	}
	/**
	 * Gets the length the reservation is made for.
	 * @return length
	 */
	public Duration getLength() {
		return length;
	}

	/**
	 * Changes the table to a new table.
	 * @param table
	 */
	public void setTable(Table table) {
		this.table = table;
	}
	/**
	 * Changes the time the reservation is for.
	 * @param time
	 */
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	/**
	 * Changes the length of the reservation.
	 * @param length
	 */
	public void setLength(Duration length) {
		this.length = length;
	}

	/**
	 * Checks to see if a reservation overlaps with another one. Checks table and then time. 
	 * Will return {@code true} if it overlaps else {@code false}.
	 * @param res
	 * @return boolean
	 */
	public boolean overlaps(Reservation res) {
		if (!table.equals(res.table)) return false;
		else if (time.isAfter(res.time) && time.isBefore(res.time.plus(res.length))) return true;
		else if (res.time.isAfter(time) && res.time.isBefore(time.plus(length))) return true;
		else return false;
	}
	
	/** 
	 * Gets the products arraylist.
	 * @return ArrayList<Product>
	 */
	public ArrayList<Product> getProducts() {
		ArrayList<Product> reservationProducts = new ArrayList<Product>();
		for (Product p : table.getProducts()) {
			reservationProducts.add(p);
		}
		return reservationProducts;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s", table.getTableNumber(), time.toString(), length);
	}
}
