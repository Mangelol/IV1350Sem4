package se.kth.iv1350.pos.integration;

/**
 * Is thrown when something with performing an operation in the Inventory class goes wrong
 * @author magnu
 *
 */
public class InventoryException extends RuntimeException {
	
	public InventoryException(String message, Exception cause) {
		super(message, cause);
	}
}
