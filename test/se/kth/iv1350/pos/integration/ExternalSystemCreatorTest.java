package se.kth.iv1350.pos.integration;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;

public class ExternalSystemCreatorTest {
	ExternalSystemCreator instance;
	
	@Before
	public void createInstanceOfExternalSystemCreator() {
		instance = new ExternalSystemCreator();
	}
	
	@After
	public void removeInstanceOfExternalSystemCreator() {
		instance = null;
	}
	
	@Test
	public void testGetAccounting() {
		System.out.println("getAccounting");
		ExternalSystemCreator instance = new ExternalSystemCreator();
		Accounting result = instance.getAccounting();
		assertNotNull(result);
	}
	
	@Test
	public void testGetInventory() {
		System.out.println("getInventory");
		ExternalSystemCreator instance = new ExternalSystemCreator();
		Inventory result = instance.getInventory();
		assertNotNull(result);
	}

}
