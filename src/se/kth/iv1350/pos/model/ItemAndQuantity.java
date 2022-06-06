package se.kth.iv1350.pos.model;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.util.Amount;

/**
 * Represents an item and quantity of that item
 * @author magnu
 *
 */
public class ItemAndQuantity {
	private final ItemDTO item;
	private final int quantity;
	
	/**
	 * This method creates a new instance of an item and its quantity
	 * @param item The registered item
	 * @param quantity The quantity of the registered item
	 */
	public ItemAndQuantity(ItemDTO item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public ItemDTO getItem() {
		return item;
	}
	
	public String getItemName() {
		return item.getItemName();
	}
	
	public int getItemId() {
		return item.getItemId();
	}
	
	public String getItemDesc() {
		return item.getItemDesc();
	}
	
	public Amount getPrice() {
		return item.getPrice();
	}
	
	public int getQuantity() {
		return quantity;
	}
}
