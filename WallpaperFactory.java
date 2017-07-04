package a2;

public class WallpaperFactory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Customer wm = new Customer(100, "Wal-Mart");
		Customer hd = new Customer(200, "Home Depot");
		Product flower = new Product(123,"Flower Field");
		Product bath = new Product(321, "Stormy Sea");
		Inventory flowerInv = new Inventory(flower);
		Inventory bathInv = new Inventory(bath);
		
		
		// Add production orders
		ProductionOrder flowerPO = new ProductionOrder(1000, flowerInv);
		ProductionOrder bathPO = new ProductionOrder(2000, bathInv);
		
		
		// Place some customer orders
		SalesOrder o1 = new SalesOrder(wm, 500, flowerInv);
		SalesOrder o2 = new SalesOrder(hd, 700, bathInv);;
		SalesOrder o3 = new SalesOrder(hd, 800, flowerInv);
		SalesOrder o4 = new SalesOrder(hd, 1400, bathInv);
		System.out.println(bathInv);
		System.out.println(flowerInv);
		
		
		
	}
}
