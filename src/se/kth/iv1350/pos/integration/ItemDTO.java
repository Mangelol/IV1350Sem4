package se.kth.iv1350.pos.integration;
import se.kth.iv1350.pos.util.Amount;

/**
 * Represents one item
 * @author magnu
 *
 */
public class ItemDTO {
	private final String name;
	private final int itemId;
	private final Amount price;
	private final Amount vatRate;
	private final String itemDesc;
	
	/**
	 * Used to crate a new instance of item
	 * @param name The name of the item
	 * @param itemId The item identification number
	 * @param price The price of the item
	 * @param vatRate The items specific VAT rate
	 * @param itemDesc Contains the name of the item and the VAT rate
	 */
	public ItemDTO(String name, int itemId, Amount price, Amount vatRate, String itemDesc) {
		this.name = name;
		this.itemId = itemId;
		this.price = price;
		this.vatRate = vatRate;
		this.itemDesc = itemDesc;
	}
	
	public String getItemName() {
		return name;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public Amount getPrice() {
		return price;
	}
	
	public Amount getVAT() {
		return vatRate;
	}
	
	public String getItemDesc() {
		return itemDesc;
	}
}
