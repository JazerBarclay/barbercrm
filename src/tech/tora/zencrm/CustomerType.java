package tech.tora.zencrm;

public enum CustomerType {
	
	CHILD(1),
	ADULT(2),
	OAP(3)
	;
	
	public int type;
	
	CustomerType(int value) {
		this.type = value;
	}
	
}
