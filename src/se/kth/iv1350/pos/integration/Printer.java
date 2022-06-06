package se.kth.iv1350.pos.integration;

/**
 * The printer that prints the receipt
 * @author magnu
 *
 */
public class Printer {
	/**
	 * Creates a new instance and should only be initialized once
	 */
	public Printer() {
		System.out.println("Printer is online");
		System.out.println();
	}
	
	/**
	 * This method prints the receipt. The receipt is printed to View since no real printer is used in this project
	 * @param receipt Contains all the relevant information within a string
	 */
	public void printReceipt(StringBuilder receipt) {
		System.out.println(receipt);
	}
}
