package mirosimo.car_showroom2.enums;

public enum PropellantType {
	PETROL("Petrol"), DIESEL("Diesel"), ELECTRIC("Electric"), LPG("LPG");
	
	private final String niceValue;
	
	private PropellantType(String fuel) {
		this.niceValue = fuel;
	}
	
	public String getNiceValue() {
		return this.niceValue;
	}
}
