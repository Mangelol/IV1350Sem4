package se.kth.iv1350.pos.util;
import java.text.DecimalFormat;

/**
 * Represents an amount of money. Immutable instances
 * @author magnu
 *
 */
public class Amount {
	private final double amount;
	
	/**
	 * Creates a new instance that represents the amount 0
	 */
	public Amount() {
		this(0);
	}
	
	/**
	 * This method creates a new instance and represents the specified amount
	 * @param amount This is the amount represented
	 */
	public Amount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * Subtracts an amount with another amount
	 * @param other The amount used to subtract
	 * @return Returns the result after subtracting
	 */
	public Amount subtract(Amount other) {
		return new Amount(amount - other.amount);
	}
	
	/**
	 * Adds an amount with another amount
	 * @param other The amount used to add
	 * @return Returns the result after addition
	 */
	public Amount add(Amount other) {
		return new Amount(amount + other.amount);
	}
	
	/**
	 * Multiplies an amount with another amount
	 * @param other The amount used to multiply
	 * @return Returns the result after multiplication
	 */
	public Amount multiply(Amount other) {
		return new Amount(amount * other.amount); 
	}
	
	/**
	 * Formatting the output for amount
	 * @return Returns a readable value for the amount using 2 decimals
	 */
	@Override
	public String toString() {
		DecimalFormat df2 = new DecimalFormat("#.##");
		String s = "";
		s += df2.format(amount);
		
		return s;
	}
}
