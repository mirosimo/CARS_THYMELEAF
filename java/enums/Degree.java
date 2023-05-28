package mirosimo.car_showroom2.enums;

public enum Degree {
	ING("Ing."), BC("Bc."), MUDR("Mudr."), DOC("Doc.");
	
	private final String niceValue;
	
	private Degree(String title) {
		this.niceValue = title;
	}
	
	public String getNiceValue() {
		return this.niceValue;
	}
}
