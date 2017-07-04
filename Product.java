package a2;

public class Product {
	
	protected int ID;
	protected String name;
	
	public Product(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return ID + " " + name;
	}
}
