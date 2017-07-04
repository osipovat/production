package a2;

public class ProductionOrder implements Observer, DisplayElement {

	
	private static int orderSequence;
	protected int ID;
	protected double minQuantity;
	protected Observable inventory;
	
	public ProductionOrder(double minQty, Observable inventory) {
		this.minQuantity = minQty;
		this.inventory = inventory;
		orderSequence++;
		ID = orderSequence;
		
		
		((Inventory)this.inventory).availableQuantity = 0.0;
		this.inventory.registerObserver(this); 
	}
	
	@Override
	public void update(double availQty, double ordQty) {
		// if the backorderedQuantity is greater or equal than minQuantity, 
		//the ProductionOrder activates the manufacturing facility to make some quantity
		//(sufficient to cover all SalesOrders!) and updates the availableQuantity.
		
		if (ordQty - availQty >= this.minQuantity) {
			((Inventory)this.inventory).availableQuantity += ordQty - availQty;
			display(ordQty);
			//The SalesOrders should get notified on the availability and ship out. 
			//The inventory should be maintained accordingly.
			for(int i = 0; i < ((Inventory)this.inventory).observers.size(); i++) {
				Observer observer = ((Inventory)this.inventory).observers.get(i);
				if (observer instanceof SalesOrder){
					((Inventory)this.inventory).backorderedQuantity -= ((SalesOrder) observer).quantity;
					((Inventory)this.inventory).availableQuantity -= ((SalesOrder) observer).quantity;
					//If a quantity is made by a ProductionOrder or 
					//if a quantity is shipped in behalf of a SalesOrder the display method 
					//should display the appropriate information on the console.
					((SalesOrder) observer).display(((SalesOrder) observer).quantity);;
				}
			}
		}
	}
	
	@Override
	public void display(double disQty) {
		System.out.println(this.toString() + disQty);
	}
	
	@Override
	public String toString( ) {
		return "Production Order# " + ID + ", item " + ((Inventory)this.inventory).product.name + ", produced "; 
	}
}
