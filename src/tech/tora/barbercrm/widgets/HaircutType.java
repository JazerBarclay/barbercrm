package tech.tora.zencrm;

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
	
	public int id;
	
	HaircutType(int id) {
		this.id = id;
	}
	
}
