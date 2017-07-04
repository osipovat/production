package a2;

import java.util.ArrayList;

import java.util.List;

public class Inventory implements Observable{ 

	
	protected List<Observer> observers;
	protected Product product;
	protected double availableQuantity;
	protected double backorderedQuantity;
	
	
	public Inventory(Product product) {
		this.product = product;
		observers = new ArrayList<Observer>();
		availableQuantity = 0;
		backorderedQuantity = 0;
		
	}
	
	protected void updateQuantities(double stock, double backord) {
		this.availableQuantity += stock;
		this.backorderedQuantity += backord;
		
		
		notifyObserver();
		
	}
	
	@Override
	public void registerObserver(Observer o){
		this.observers.add(o);
	}
	
	@Override
	public void removeObserver(Observer o){
		int i = observers.indexOf(o);
		if (i >= 0)
			observers.remove(i);
	}
	
	@Override
	public void notifyObserver(){
		//find the production order and update the quantity
		for(int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer) observers.get(i);
			if (observer instanceof ProductionOrder){
				//prodord = (ProductionOrder) observer;
				observer.update(availableQuantity, backorderedQuantity);
			}
		}
	}
	@Override
	public String toString(){
		return product.toString() + ", Available: " + availableQuantity + ", Backorderes: " + backorderedQuantity;
	}
}
