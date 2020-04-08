package tech.tora.barbercrm.dao;

/* PLEASE NOTE : DATABASE POPULATES VALUES BASED ON THESE ENUMS */
/* ADD NEW VALUES IN SEQUENTIAL ORDER TO PRESERVE DB ID VALUES  */

/**
 * Type of customer
 */
public enum CustomerType {
	
	CHILD(1),
	ADULT(2),
	OAP(3)
	;
	
	/**
	 * Database ID
	 */
	public int id;
	
	/**
	 * Customer Type Name and ID
	 * @param id - Database ID Reference
	 */
	CustomerType(int value) {
		this.id = value;
	}
}