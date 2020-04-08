package tech.tora.barbercrm.dao;

public class Haircut {

	private int haircutNumber;
	private int customerNumber;
	
	private int haircutDate;
	private HaircutType haircutType;
	
	public int getHaircutNumber() {
		return haircutNumber;
	}
	public void setHaircutNumber(int haircutNumber) {
		this.haircutNumber = haircutNumber;
	}
	public int getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}
	public int getHaircutDate() {
		return haircutDate;
	}
	public void setHaircutDate(int haircutDate) {
		this.haircutDate = haircutDate;
	}
	public HaircutType getHaircutType() {
		return haircutType;
	}
	public void setHaircutType(HaircutType haircutType) {
		this.haircutType = haircutType;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	private boolean active;
	
}
