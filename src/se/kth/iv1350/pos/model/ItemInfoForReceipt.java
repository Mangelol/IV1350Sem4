package se.kth.iv1350.pos.model;
import se.kth.iv1350.pos.util.Amount;

/**
 * Represents the name, price and quantity of every bought item
 * @author magnu
 *
 */
public class ItemInfoForReceipt {
	private final String name;
	private final int quantity;
	private final Amount price;
	
	/**
	 * Creates a new instance with all the info about an item that is to be printed on the receipt
	 * @param name The name of the item
	 * @param quantity The quantity of the item
	 * @param price The price of that item
	 */
	public ItemInfoForReceipt(String name, int quantity, Amount price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	public String getItemName() {
		return name;
	}
	
	public int getItemQuantity() {
		return quantity;
	}
	
	public Amount getItemPrice() {
		return price;
	}
	
	/**
	 * The format when printing the whole list.
	 * Not used in the program since each element is printed separatly
	 */
	@Override
	public String toString() {
		String s = "";
		s += name + " ";
		s += quantity + " x ";
		s += price;
		
		return s;
	}
}
