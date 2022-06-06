package se.kth.iv1350.pos.model;
import se.kth.iv1350.pos.util.Amount;

/**
 * This class contains the item description, running total and item price for the registered item
 * @author magnu
 *
 */
public class TotalDTO {
	private final Amount runningTotal;
	private final Amount price;
	private final String itemDesc;
	
	/**
	 * A new instance with the specified attributes is created
	 * @param itemDesc The description of the item
	 * @param runningTotal The running price of the total sale
	 * @param price The price of the specified item
	 */
	public TotalDTO(String itemDesc, Amount runningTotal, Amount price) {
		this.itemDesc = itemDesc;
		this.runningTotal = runningTotal;
		this.price = price;
	}
	
	public String getItemDesc() {
		return itemDesc;
	}
	
	public Amount getRunningTotal() {
		return runningTotal;
	}
	
	public Amount getPrice() {
		return price;
	}
}
