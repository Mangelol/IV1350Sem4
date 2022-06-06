package se.kth.iv1350.pos.startup;
import se.kth.iv1350.pos.integration.ExternalSystemCreator;
import se.kth.iv1350.pos.integration.InventoryException;
import se.kth.iv1350.pos.integration.ItemIdNotFoundException;
import se.kth.iv1350.pos.view.View;
import se.kth.iv1350.pos.controller.Controller;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The Main class is used to start the application
 * @author magnu
 *
 */
public class Main {
	/**
	 * The Main method is used to start the application
	 * @param args Does not take any command line parameters
	 * @throws se.kth.iv1350.pos.integration.ItemIdNotFoundException
	 * @throws se.kth.iv1350.pos.integration.InventoryException
	 * @throws java.sql.SQLException
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws ItemIdNotFoundException, InventoryException,
												  SQLException, IOException {
		
		ExternalSystemCreator systemCreator = new ExternalSystemCreator();
		Controller contrl = new Controller(systemCreator);
		
		View view = new View(contrl);
		
		int i = 3;
		while(i > 0) {
			view.runFakeExecution();
			i--;
		}
	}

}