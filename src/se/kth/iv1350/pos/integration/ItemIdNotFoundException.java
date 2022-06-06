package se.kth.iv1350.pos.integration;

/**
 * This class handles error when the registered item id could not be found in the inventory
 * @author magnu
 *
 */
public class ItemIdNotFoundException extends Exception {
	private final int notFoundItemId;
	
	/**
	 * This method creates a new message that specifies what item that was not found
	 * @param notFoundItemId The item that was registered but not found in the inventory
	 */
	public ItemIdNotFoundException(int notFoundItemId) {
		super("The item ID " + notFoundItemId + " was not found in Inventory");
		this.notFoundItemId = notFoundItemId;
	}
	
	public int getNotFoundItemId() {
		return notFoundItemId;
	}

}
