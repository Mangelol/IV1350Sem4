package se.kth.iv1350.pos.integration;
import static org.junit.Assert.*;
import org.junit.Test;
import se.kth.iv1350.pos.util.Amount;
import java.sql.SQLException;

public class InventoryTest {
	
	@Test
	public void testGetExistingItemInInventory() throws SQLException {
		ItemDTO existingItem = new ItemDTO("Bread", 0, new Amount(3.99), new Amount(0.12), "Bread... VAT: 12%");
		Inventory instance = new Inventory();
		
		try {
			ItemDTO expResult = existingItem;
			ItemDTO result = instance.fetchItem(0);
			assertEquals("Could not find existing item", expResult, result);
			fail("InventoryException should have been thrown, existing item was found");
		} catch(InventoryException except) {
			assertTrue("Could not find existing item: " + except.getMessage(), except.getMessage().contains("database"));
		}
	}
	
	@Test
	public void testGetNonExistingItemInInventory() throws SQLException {
		ItemDTO nonexistantItem = null;
		Inventory instance = new Inventory();
		
		try {
			ItemDTO expResult = nonexistantItem;
			ItemDTO result = instance.fetchItem(12);
			assertEquals("Found nonexistant item", expResult, result);
			fail("InventoryException should have been thrown, found nonexisting item");
		} catch(InventoryException except) {
			assertTrue("Could not find nonexistant item: " + except.getMessage(), except.getMessage().contains("database"));
		}
	}
	

}