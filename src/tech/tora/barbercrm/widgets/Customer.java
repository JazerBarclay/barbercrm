package tech.tora.barbercrm.widgets;

public class Customer {
	
	private int customerID;

	private String firstName, lastName;

	private CustomerType customerType;
	
	private int currentStreak;
	
	private int totalFreeHaircuts;
	
	private String email;
	
	private String comments;
	
	private boolean active;

	public Customer() {
		customerID = 0;
		customerType = CustomerType.ADULT;
		currentStreak = 0;
		totalFreeHaircuts = 0;
		active = true;
	}
	
	public Customer(int id, String firstName, String lastname, CustomerType type, int currentStreak, int totalFreeHaircuts, String email, String comments, boolean active) {
		this.customerID = id;
		this.firstName = firstName;
		this.lastName = lastname;
		this.customerType = type;
		this.currentStreak = currentStreak;
		this.totalFreeHaircuts = totalFreeHaircuts;
		this.email = email;
		this.comments = comments;
		this.active = active;
	}
	
	public void setID(int customerID) {
		this.customerID = customerID;
	}
	
	public int getID() {
		return this.customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public int getCurrentStreak() {
		return currentStreak;
	}

	public void setCurrentStreak(int currentStreak) {
		this.currentStreak = currentStreak;
	}

	public int getTotalFreeHaircuts() {
		return totalFreeHaircuts;
	}

	public void setTotalFreeHaircuts(int totalFreeHaircuts) {
		this.totalFreeHaircuts = totalFreeHaircuts;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	
}
