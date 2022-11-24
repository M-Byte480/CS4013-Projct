package reservation;

import till.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
	private Table table;
	private String customerID;
	private LocalDateTime time;
	private LocalDateTime length;

	/**
	 * Makes a Reservation object.
	 * @param table which a reservation will be made for
	 * @param time which the reservation will start
	 * @param length of the reservation
	 * @param customerID of the person at the table
	 */
	public Reservation(Table table, LocalDateTime time, LocalDateTime length, String customerID) {
		this.table = table;
		this.time = time;
		this.length = length;
		this.customerID = customerID;
	}

	/**
	 * Gets the table that the reservation is made for.
	 * @return table
	 */
	public Table getTable() {
		return table;
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
	public LocalDateTime getLength() {
		return length;
	}

	/**
	 * Gets the id of the customer object
	 * @return id
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * Checks to see if a reservation overlaps with another one. Checks table and then time. 
	 * Will return {@code true} if it overlaps else {@code false}.
	 * @param res
	 * @return boolean
	 */
	public boolean overlaps(Reservation res) {
		if (!table.equals(res.table)) return false;
		else if (time.isAfter(res.time) && time.isBefore(res.length)) return true;
		else return res.time.isAfter(time) && res.time.isBefore(length);
	}


	/**
	 * @return table number, time, length and customer ID formatted to string
	 */
	@Override
	public String toString() {
		return String.format("%s,%s,%s,%s", table.getTableNumber(), time.format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm")), length, customerID);
	}
}
