package se.kth.iv1350.pos.integration;

/**
 * Creates the external systems.
 * @author magnu
 *
 */
public class ExternalSystemCreator {
	private Inventory inventory;
	private Accounting accounting;
	
	public ExternalSystemCreator() {
		this.inventory = new Inventory();
		this.accounting = new Accounting();
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public Accounting getAccounting() {
		return accounting;
	}
}
