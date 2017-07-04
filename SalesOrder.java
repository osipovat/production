package a2;

public class SalesOrder implements Observer, DisplayElement {
	private static int orderSequence;
	protected int ID;
	protected Customer customer;
	protected double quantity;
	protected Observable inventory;
	
	public SalesOrder(Customer customer, double quantity, Observable inventory) {
		this.customer = customer;
		this.quantity = quantity;
		this.inventory = inventory;
		orderSequence++;
		ID = orderSequence;
		
		update(((Inventory)this.inventory).availableQuantity, quantity);
	}
	
	@Override
	public void update(double availQty, double ordQty) {
		//If there is enough quantity on inventory, the SalesOrder gets shipped immediately 
		//so it does not need to go to the list of observers. 
		//Otherwise it should be registered as an Observer.
		if (availQty >= ordQty) {
			
			this.ship(ordQty);
			display(ordQty);
		}
		else{
			this.inventory.registerObserver(this);
			//update backorderedQuantity and notify ProductionOrder
			((Inventory)this.inventory).updateQuantities(0.0, ordQty);
		}
	}
	
	@Override
	public void display(double displayQuantity) {
		System.out.println(this.toString() + displayQuantity);
	}
	
	private boolean ship(double availableQuantity) {
		
		((Inventory) this.inventory).availableQuantity -= availableQuantity;
		
		return !((Inventory)this.inventory).observers.contains(this);
		
	}
	
	@Override
	public String toString() {
		return "Shipping Order# " + ID + " to " + customer.name + ", Product: " + ((Inventory)this.inventory).product.name + ", Quantity: ";
	}
}
