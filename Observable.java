package a2;

public interface Observable {
	
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObserver();
}