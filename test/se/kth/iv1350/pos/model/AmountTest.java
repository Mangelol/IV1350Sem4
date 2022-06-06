package se.kth.iv1350.pos.model;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Test;
import java.text.DecimalFormat;
import se.kth.iv1350.pos.util.Amount;

public class AmountTest {
	private Amount amountNoArgConstr;
	private Amount amountWithAmountThree;
	DecimalFormat df = new DecimalFormat("#.##");
	
	public AmountTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@Before
	public void setUp() {
		amountNoArgConstr = new Amount();
		amountWithAmountThree = new Amount(3);
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@After
	public void tearDown() {
		amountNoArgConstr = null;
		amountWithAmountThree = null;
	}
	
	@Test
	public void testSubtract() {
		double amountOfOp1 = 7;
		double amountOfOp2 = 4;
		Amount op1 = new Amount(amountOfOp1);
		Amount op2 = new Amount(amountOfOp2);
		Amount expectedResult = new Amount(amountOfOp1 - amountOfOp2);
		Amount result = op1.subtract(op2);
		assertEquals("Result of subtraction is wrong", expectedResult.toString(), result.toString());
	}
	
	@Test
	public void testSubtractNegativeResult() {
		double amountOfOp1 = 4;
		double amountOfOp2 = 7;
		Amount op1 = new Amount(amountOfOp1);
		Amount op2 = new Amount(amountOfOp2);
		Amount expectedResult = new Amount(amountOfOp1 - amountOfOp2);
		Amount result = op1.subtract(op2);
		assertEquals("Result of subtraction is wrong", expectedResult.toString(), result.toString());
	}
	
	@Test
	public void testSubtractZeroResultWithNegativeOperand() {
		double amountOfOp1 = -7;
		double amountOfOp2 = -7;
		Amount op1 = new Amount(amountOfOp1);
		Amount op2 = new Amount(amountOfOp2);
		Amount expectedResult = new Amount(amountOfOp1 - amountOfOp2);
		Amount result = op1.subtract(op2);
		assertEquals("Result of subtration is wrong", expectedResult.toString(), result.toString());
	}
	
	@Test
	public void testAddition() {
		double amountOfOp1 = 7;
		double amountOfOp2 = 4;
		Amount op1 = new Amount(amountOfOp1);
		Amount op2 = new Amount(amountOfOp2);
		Amount expectedResult = new Amount(amountOfOp1 + amountOfOp2);
		Amount result = op1.add(op2);
		assertEquals("Result of addition is wrong", expectedResult.toString(), result.toString());
	}
	
	@Test
	public void testAdditionNegativeResult() {
		double amountOfOp1 = 4;
		double amountOfOp2 = -7;
		Amount op1 = new Amount(amountOfOp1);
		Amount op2 = new Amount(amountOfOp2);
		Amount expectedResult = new Amount(amountOfOp1 + amountOfOp2);
		Amount result = op1.add(op2);
		assertEquals("Result of addition is wrong", expectedResult.toString(), result.toString());
	}
	
	@Test
	public void testAdditionZeroResultWithNegativeOperand() {
		double amountOfOp1 = -4;
		double amountOfOp2 = -4;
		Amount op1 = new Amount(amountOfOp1);
		Amount op2 = new Amount(amountOfOp2);
		Amount expectedResult = new Amount(amountOfOp1 + amountOfOp2);
		Amount result = op1.add(op2);
		assertEquals("Result of addition is wrong", expectedResult.toString(), result.toString());
	}
	
	@Test
	public void testMultiplication() {
		double amountOfOp1 = 7;
		double amountOfOp2 = 4;
		Amount op1 = new Amount(amountOfOp1);
		Amount op2 = new Amount(amountOfOp2);
		Amount expectedResult = new Amount(amountOfOp1 * amountOfOp2);
		Amount result = op1.multiply(op2);
		assertEquals("Result of multiplication is wrong", expectedResult.toString(), result.toString());
	}
	
	@Test
	public void toStringPosAmount() {
		double representedAmount = 15;
		Amount amount = new Amount(representedAmount);
		String expectedResult = Double.toString(representedAmount);
		String result = amount.toString();
		assertEquals("String returned by toString is wrong", expectedResult, result);
	}
	
	@Test
	public void toStringNegativeAmount() {
		double representedAmount = -15;
		Amount amount = new Amount(representedAmount);
		String expectedResult = Double.toString(representedAmount);
		String result = amount.toString();
		assertEquals("String returned by toString is wrong", expectedResult, result);
	}
	
	@Test
	public void toStringZeroAmount() {
		double representedAmount = 0;
		Amount amount = new Amount(representedAmount);
		String expectedResult = Double.toString(representedAmount);
		String result = amount.toString();
		assertEquals("String returned by toString is wrong", expectedResult, result);
	}

}
