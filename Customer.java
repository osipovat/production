package a2;

public class Customer {
	
	protected int ID;
	protected String name;
	
	public Customer(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return ID + " " + name;
	}
}
