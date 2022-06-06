package se.kth.iv1350.pos.startup;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Test;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import se.kth.iv1350.pos.integration.InventoryException;
import se.kth.iv1350.pos.integration.ItemIdNotFoundException;

public class MainTest {
	private Main testInstance;
	private PrintStream originalSysOut;
	private ByteArrayOutputStream printoutBuffer;
	
	@Before
	public void setUp() {
		testInstance = new Main();
		printoutBuffer = new ByteArrayOutputStream();
		PrintStream inMemSysOut = new PrintStream(printoutBuffer);
		originalSysOut = System.out;
		System.setOut(inMemSysOut);
	}
	
	@After
	public void tearDown() {
		testInstance = null;
		printoutBuffer = null;
		System.setOut(originalSysOut);
	}
	
	@Test
	public void testMain() throws ItemIdNotFoundException, InventoryException, SQLException, IOException {
		String[] args = null;
		Main.main(args);
		String printout = printoutBuffer.toString();
		String expectedOutput = "Started";
		assertTrue("The UI failed to start correctly", printout.contains(expectedOutput));
	}
	

}
