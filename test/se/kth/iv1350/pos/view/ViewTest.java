package se.kth.iv1350.pos.view;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Test;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.model.PaidAmountObserver;
import se.kth.iv1350.pos.util.Amount;
import se.kth.iv1350.pos.integration.ExternalSystemCreator;
import se.kth.iv1350.pos.integration.ItemIdNotFoundException;
import se.kth.iv1350.pos.integration.InventoryException;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class ViewTest {
	Printer printer;
	ExternalSystemCreator creator;
	private View testInstance;
	private PrintStream originalSysOut;
	private ByteArrayOutputStream printoutBuffer;
	private PaidAmountObserver observer = new TotalRevenueView(new Amount(0));
	
	public ViewTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@Before
	public void setUp() throws IOException {
		printer = new Printer();
		creator = new ExternalSystemCreator();
		Controller contr = new Controller(creator);
		testInstance = new View(contr);
		printoutBuffer = new ByteArrayOutputStream();
		PrintStream inMemSysOut = new PrintStream(printoutBuffer);
		originalSysOut = System.out;
		System.setOut(inMemSysOut);
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@After
	public void tearDown() {
		printer = null;
		creator = null;
		testInstance = null;
		printoutBuffer = null;
		System.setOut(originalSysOut);
	}
	
	@Test
	public void testRunFakeExecution() throws ItemIdNotFoundException, InventoryException, SQLException {
		testInstance.runFakeExecution();
		String printout = printoutBuffer.toString();
		String expectedOutput = "Start";
		assertTrue("The UI failed to start correctly", printout.contains(expectedOutput));
	}
}