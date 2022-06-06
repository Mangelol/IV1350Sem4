package se.kth.iv1350.pos.controller;
import static org.junit.Assert.*;
import org.junit.Test;
import se.kth.iv1350.pos.model.TotalDTO;
import se.kth.iv1350.pos.integration.InventoryException;
import se.kth.iv1350.pos.integration.ItemIdNotFoundException;
import se.kth.iv1350.pos.integration.ExternalSystemCreator;
import java.sql.SQLException;

public class ControllerTest {

	@Test
	public void testToRegisterNonexistantItem() throws Exception {
		ExternalSystemCreator systmC = new ExternalSystemCreator();
		Controller instance = new Controller(systmC);
		System.out.println("registerItem");
		int itemId = 12;
		int quantity = 1;
		
		try {
			TotalDTO expResult = null;
			TotalDTO result = instance.registerItem(itemId, quantity);
			assertEquals("Successfully registered nonexistant item", expResult, result);
			fail("Exception should have been thrown because item ID was not found");
		} catch(InventoryException | ItemIdNotFoundException except) {
			assertTrue("Wrong exception message: " + except.getMessage(),
								except.getMessage().contains("not found") || 
								except.getMessage().contains("database"));
		}
	}

}

