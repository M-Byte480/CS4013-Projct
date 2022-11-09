package reservation;

import people.Customer;
import people.Staff;
import till.Table;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

public class Reservation {
	private Table table;
	private Staff staff;
	private Customer cust;
	
	private LocalDateTime time;
	private TemporalAmount length;
	
	/**
	 * Makes a Reservation object.
	 * @param table
	 * @param staff
	 * @param time
	 * @param length
	 */
	public Reservation(Table table, Staff staff, LocalDateTime time, TemporalAmount length) {
		this.table = table;
		this.staff = staff;
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
	public Customer getCust() {
		return cust;
	}
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	/**
	 * Gets the staff member who made the reservation.
	 * @return staff
	 */
	public Staff getStaff() {
		return staff;
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
	public TemporalAmount getLength() {
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
	 * Changes the staff member the reservation is under.
	 * @param staff
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
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
	public void setLength(TemporalAmount length) {
		this.length = length;
	}

	public boolean overlaps(Reservation res) {
		if (!table.equals(res.table)) return false;
		else if (time.isAfter(res.time) && time.isBefore(res.time.plus(res.length))) return true;
		else if (res.time.isAfter(time) && res.time.isBefore(time.plus(length))) return true;
		else return false;
	}
}
