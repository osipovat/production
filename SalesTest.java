/**
 * 
 */
package a2;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SalesTest {

	@Test
	public final void testSimple1() {
		Product shirt = new Product(234,"Shirt");
		Inventory shirtInv = new Inventory(shirt);
		assertEquals("initial quantity should be zero", (int)shirtInv.availableQuantity, (int)0);
	}
	
	@Test
	public final void testSimple2() {
		Product mango = new Product(214,"mango");
		Inventory mangoInv = new Inventory(mango);
		assertEquals("initial quantity should be zero", (int)mangoInv.backorderedQuantity, (int)0);
	}
	
	@Test
	public final void testSimple3() {
		Product shirt2 = new Product(235,"Shirt2");
		Inventory shirt2Inv = new Inventory(shirt2);
		shirt2Inv.updateQuantities(50, 50);
		assertEquals("quantity should be 50", (int)shirt2Inv.availableQuantity, (int)50);
	}
	
	@Test
	public final void testSimple4() {
		Product mango2 = new Product(235,"mango2");
		Inventory mango2Inv = new Inventory(mango2);
		mango2Inv.updateQuantities(50, 50);
		assertEquals("quantity should be 50", (int)mango2Inv.backorderedQuantity, (int)50);
	}
	
	@Test
	public final void testPO1() {
		Product shirt3 = new Product(235,"Shirt3");
		Inventory shirt3Inv = new Inventory(shirt3);
		
		ProductionOrder shirt3PO = new ProductionOrder(0, shirt3Inv);
		shirt3PO.update(0, 40);
		assertEquals("quantity should be 40", (int)shirt3Inv.availableQuantity, (int)40);
	}
	
	@Test
	public final void testPO2() {
		Product shirt4 = new Product(236,"Shirt4");
		Inventory shirt4Inv = new Inventory(shirt4);
		
		ProductionOrder shirt4PO = new ProductionOrder(0, shirt4Inv);
		shirt4PO.update(40, 50);
		assertEquals("quantity should be 0", (int)shirt4Inv.availableQuantity, (int)10);
	}

	@Test
	public final void testPO3() {
		Product shirt5 = new Product(237,"Shirt5");
		Inventory shirt5Inv = new Inventory(shirt5);
		
		ProductionOrder shirt5PO = new ProductionOrder(20, shirt5Inv);
		shirt5PO.update(40, 50);
		assertEquals("quantity should be 0", (int)shirt5Inv.availableQuantity, (int)0);
	}
	
	@Test
	public final void testPO4() {
		Product shirt6 = new Product(238,"Shirt6");
		Inventory shirt6Inv = new Inventory(shirt6);
		
		ProductionOrder shirt6PO1 = new ProductionOrder(0, shirt6Inv);
		ProductionOrder shirt6PO2 = new ProductionOrder(0, shirt6Inv);
		assertNotEquals("ids should not be equal", shirt6PO1.ID, shirt6PO2.ID);
	}
	
	@Test
	public final void testPO5() {
		Product s7 = new Product(239,"Shirt7");
		Inventory s7Inv = new Inventory(s7);
		
		ProductionOrder s7PO = new ProductionOrder(10, s7Inv);
		s7PO.update(0, 11);
		assertEquals("quantity should be 11", (int)s7Inv.availableQuantity, (int)11);
	}
	
	@Test
	public final void testPO6() {
		Product s8 = new Product(240,"Shirt8");
		Inventory s8Inv = new Inventory(s8);
		
		ProductionOrder s8PO = new ProductionOrder(9, s8Inv);
		s8PO.update(41, 50);
		assertEquals("quantity should be 9", (int)s8Inv.availableQuantity, (int)9);
	}
	
	@Test
	public final void testPO7() {
		Product s9 = new Product(241,"Shirt9");
		Inventory s9Inv = new Inventory(s9);
		ProductionOrder s9PO = new ProductionOrder(2, s9Inv);
		s9PO.update(4000, 4001);
		assertEquals("quantity should be 0", (int)s9Inv.availableQuantity, (int)0);
	}
	
	@Test
	public final void testPO8() {
		Product s10 = new Product(238,"Shirt10");
		Inventory s10Inv = new Inventory(s10);
		Inventory s11Inv = new Inventory(s10);
		ProductionOrder s10PO1 = new ProductionOrder(0, s10Inv);
		ProductionOrder s10PO2 = new ProductionOrder(0, s11Inv);
		assertNotEquals("ids should not be equal", s10PO1.ID, s10PO2.ID);
	}
	
	@Test
	public final void testSO1() {
		Customer c1 = new Customer(100, "C1");
		Product p1 = new Product(123,"P1");
		Inventory v1 = new Inventory(p1);
		ProductionOrder po1 = new ProductionOrder(0, v1);
		po1.update(0, 1000);
		SalesOrder o1 = new SalesOrder(c1, 500, v1);
		assertEquals("quantity should be 500", (int)v1.availableQuantity, (int)500);
	}
	
	@Test
	public final void testSO2() {
		Customer c2 = new Customer(101, "C2");
		Product p2 = new Product(124,"P2");
		Inventory v2 = new Inventory(p2);
		SalesOrder o2 = new SalesOrder(c2, 500, v2);
		SalesOrder o2_ = new SalesOrder(c2, 500, v2);
		assertNotEquals("ids should not be equal", o2.ID, o2_.ID);
	}
	
	@Test
	public final void testSO3() {
		Customer c3 = new Customer(102, "C3");
		Product p3 = new Product(125,"P3");
		Inventory v3 = new Inventory(p3);
		ProductionOrder po2 = new ProductionOrder(0, v3);
		po2.update(0, 1000);
		SalesOrder o3 = new SalesOrder(c3, 500, v3);
		SalesOrder o3_ = new SalesOrder(c3, 800, v3);
		assertEquals("first sale should go through", (int)v3.availableQuantity, 500);
	}
	
	@Test
	public final void testSO4() {
		Customer c4 = new Customer(102, "C4");
		Product p4 = new Product(125,"P4");
		Inventory v4 = new Inventory(p4);
		ProductionOrder po4 = new ProductionOrder(1000, v4);
		SalesOrder o4 = new SalesOrder(c4, 500, v4);
		SalesOrder o4_ = new SalesOrder(c4, 400, v4);
		assertEquals("quantity should be 0", (int)v4.availableQuantity, 0);
		assertEquals("quantity should be 900", (int)v4.backorderedQuantity, 900);
	}
	
	@Test
	public final void testSO5() {
		Customer c5 = new Customer(103, "C5");
		Product p5 = new Product(126,"P5");
		Inventory v5 = new Inventory(p5);
		// Add production orders
		ProductionOrder po5 = new ProductionOrder(1000, v5);
		po5.update(0, 1000);
		// Place some customer orders
		SalesOrder o5 = new SalesOrder(c5, 500, v5);
		SalesOrder o5_ = new SalesOrder(c5, 400, v5);
		SalesOrder o5__ = new SalesOrder(c5, 400, v5);
		assertEquals("quantity should be 100", (int)v5.availableQuantity, 100);
	}
	
	@Test
	public final void testSO6() {
		Customer c6 = new Customer(103, "C6");
		Product p6 = new Product(126,"P6");
		Inventory v6 = new Inventory(p6);
		ProductionOrder po6 = new ProductionOrder(1000, v6);
		po6.update(0, 1000);
		SalesOrder o6 = new SalesOrder(c6, 500, v6);
		SalesOrder o6_ = new SalesOrder(c6, 400, v6);
		SalesOrder o6__ = new SalesOrder(c6, 400, v6);
		assertEquals("quantity should be 400", (int)v6.backorderedQuantity, 400);
	}
	
	@Test
	public final void testSO7() {
		Customer c7 = new Customer(400, "C7");
		Product p7 = new Product(423,"P7");
		Inventory v7 = new Inventory(p7);
		ProductionOrder po7 = new ProductionOrder(0, v7);
		po7.update(0, 1000);
		SalesOrder o7 = new SalesOrder(c7, 1000, v7);
		assertEquals("quantity should be 0", (int)v7.availableQuantity, (int)0);
	}
	
	@Test
	public final void testSO8() {
		Customer c8 = new Customer(402, "C8");
		Product p8 = new Product(425,"P8");
		Inventory v8 = new Inventory(p8);
		ProductionOrder po8 = new ProductionOrder(0, v8);
		po8.update(0, 1000);
		SalesOrder o8 = new SalesOrder(c8, 400, v8);
		SalesOrder o8_ = new SalesOrder(c8, 600, v8);
		assertEquals("quantity should be 0", (int)v8.availableQuantity, 0);
	}
	
	@Test
	public final void testSO9() {
		Customer c9 = new Customer(402, "C9");
		Product p9 = new Product(425,"P9");
		Inventory v9 = new Inventory(p9);
		ProductionOrder po9 = new ProductionOrder(1000, v9);
		SalesOrder o9 = new SalesOrder(c9, 400, v9);
		SalesOrder o9_ = new SalesOrder(c9, 599, v9);
		assertEquals("no production should be done", (int)v9.availableQuantity, 0);
		assertEquals("sales shouldn't go through", (int)v9.backorderedQuantity, 999);
	}
	
	
	@Test
	public final void testSO11() {
		Customer c11 = new Customer(403, "C11");
		Product p11 = new Product(426,"P11");
		Inventory v11 = new Inventory(p11);
		ProductionOrder po11 = new ProductionOrder(1000, v11);
		po11.update(0, 1000);
		SalesOrder o11 = new SalesOrder(c11, 500, v11);
		SalesOrder o11_ = new SalesOrder(c11, 499, v11);
		SalesOrder o11__ = new SalesOrder(c11, 2, v11);
		assertEquals("last sale shouldn't go through", (int)v11.availableQuantity, 1);
	}
	
	@Test
	public final void testSO12() {
		Customer c12 = new Customer(500, "C12");
		Product p12 = new Product(523,"P12");
		Inventory v12 = new Inventory(p12);
		ProductionOrder po12 = new ProductionOrder(1, v12);
		po12.update(0, 1);
		SalesOrder o12 = new SalesOrder(c12, 1, v12);
		assertEquals("quantity should be 0", (int)v12.availableQuantity, (int)0);
	}
	
	@Test
	public final void testSO13() {
		Customer c13 = new Customer(500, "C13");
		Product p13 = new Product(523,"P13");
		Inventory v13 = new Inventory(p13);
		ProductionOrder po12 = new ProductionOrder(1, v13);
		po12.update(0, 2);
		SalesOrder o13 = new SalesOrder(c13, 1, v13);
		assertEquals("quantity should be 1", (int)v13.availableQuantity, (int)1);
	}
}
