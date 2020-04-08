package tech.tora.barbercrm.dao;

/* PLEASE NOTE : DATABASE POPULATES VALUES BASED ON THESE ENUMS */
/* ADD NEW VALUES IN SEQUENTIAL ORDER TO PRESERVE DB ID VALUES  */

/**
 * Type of haircut a customer can receive
 */
public enum HaircutType {

	OAP(1),
	BEARDTRIMCUT(2),
	BEARDTRIMBACKSIDES(3),
	BEARDTRIMONLY(4),
	ADULT(5),
	KIDS(6),
	BACKSIDES(7),
	FLATTOP(8),
	CREWCUT(9),
	TWOGRADES(10),
	HAIRWASH(11),
	HIGHLIGHTSCUT(12)
	;
	
	/**
	 * Database ID
	 */
	public int id;
	
	/**
	 * Haircut Type Name and ID
	 * @param id - Database ID Reference
	 */
	HaircutType(int id) {
		this.id = id;
	}
}