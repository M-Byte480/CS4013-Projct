package reservation;

import people.Staff;
import till.Table;
import java.time.LocalDateTime;

public class Reservation {
	private Table table;
	private Staff staff;
	private LocalDateTime time;
	private LocalDateTime length;
	
	/**
	 * Makes a Reservation object.
	 * @param table
	 * @param staff
	 * @param time
	 * @param length
	 */
	public Reservation(Table table, Staff staff, LocalDateTime time, LocalDateTime length) {
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
	public LocalDateTime getLength() {
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
	public void setLength(LocalDateTime length) {
		this.length = length;
	}


}
