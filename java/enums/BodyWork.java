package mirosimo.car_showroom2.enums;

public enum BodyWork {
	SEDAN("Sedan"), 
	COMBI("Combi"), 
	CABRIO("Cabrio"), 
	COUPE("Coupe"),
	PICKUP("Pickup"),
	VAN("Van"),
	SUV("SUV");
	
	private final String niceValue;
	
	private BodyWork(String title) {
		this.niceValue = title;
	}
	
	public String getNiceValue() {
		return this.niceValue;
	}
}
