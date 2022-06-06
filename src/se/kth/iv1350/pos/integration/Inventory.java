package se.kth.iv1350.pos.integration;
import se.kth.iv1350.pos.util.Amount;
import se.kth.iv1350.pos.model.ItemAndQuantity;
import se.kth.iv1350.pos.model.SaleDTO;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;

/**
 * The inventory contains all items available in the store
 * @author magnu
 *
 */
public class Inventory {
	private Amount price;
	private Amount vatRate;
	private String name;
	private String itemDesc;
	private final boolean connectedToInvDb = false;
	private final List<ItemAndQuantity> items = new ArrayList<>();
	
	/**
	 * Creates a new hardcoded instance. Adds items with their info and quantity to items list.
	 */
	public Inventory() {
		items.add(new ItemAndQuantity(new ItemDTO("Bread", 0, new Amount(3.99), new Amount(0.12), "Bread... VAT: 12%"), 54));
		items.add(new ItemAndQuantity(new ItemDTO("Milk", 1, new Amount(1.99), new Amount(0.12), "Milk... VAT: 12%"), 76));
		items.add(new ItemAndQuantity(new ItemDTO("Coca-Cola", 2, new Amount(1.79), new Amount(0.25), "Coca-Cola... VAT 25%"), 64));
		items.add(new ItemAndQuantity(new ItemDTO("Cheese", 3, new Amount(2.99), new Amount(0.12), "Cheese... VAT 12%"), 47));
		items.add(new ItemAndQuantity(new ItemDTO("Toiletpaper", 4, new Amount(5.99), new Amount(0.25), "Toiletpaper... VAT 25%"), 30));
		items.add(new ItemAndQuantity(new ItemDTO("Icecream", 5, new Amount(1.99), new Amount(0.25), "Icecream... VAT 25%"), 42));
	}
	
	/**
	 * This method checks if a registered item is in the inventory
	 * @param itemID The method bases its search on this item identifier
	 * @return If the item is in inventory, return true
	 * @throws java.sql.SQLException
	 */
	public boolean findItem(int itemId) throws SQLException {
		boolean itemFound = false;
		ItemDTO registeredItem = checkForMatchingItemInInventory(itemId);
		
		if (registeredItem.getItemId() == itemId) {
			itemFound = true;
		}
		
		return itemFound;
	}
	
	/**
	 * This method represents a registered item and its attributes
	 * @param itemId This is used to identify a specified item
	 * @return Returns the item description and the specified quantity
	 * @throws java.sql.SQLException
	 */
	public ItemDTO fetchItem(int itemId) throws SQLException {
		ItemDTO foundItem = checkForMatchingItemInInventory(itemId);
		
		if (foundItem != null) {
			name = foundItem.getItemName();
			itemDesc = foundItem.getItemDesc();
			price = foundItem.getPrice();
			vatRate = foundItem.getVAT();
		}
		
		ItemDTO registeredItem = new ItemDTO(name, itemId, price, vatRate, itemDesc);
		
		return registeredItem;
	}
	
	private ItemDTO checkForMatchingItemInInventory(int itemId) throws InventoryException, SQLException {
		try {
			ItemDTO currentIterationItem = null;
			if (!connectedToInvDb) {
				throw new SQLException();
			}
		
			for (int i = 0; i < items.size(); i++ ) {
				currentIterationItem = items.get(i).getItem();
				if (currentIterationItem.getItemId() == itemId)
					break;
			}
		
			return currentIterationItem;
		} catch(SQLException sqle) {
			throw new InventoryException("Connection to database failed", sqle);
		}
	}
	
	/**
	 * The inventory is updated after a sale is completed by subtracting the bought quantity
	 * of each item from the inventory
	 * @param saleInfo This contains the information about all items bought and their prices in a sale
	 */
	public void updateInventory(SaleDTO saleInfo) {
		int itemId;
		int itemQuantity;
		List<ItemAndQuantity> registeredItemsList = saleInfo.getRegisteredItems();
		ItemDTO currentIterationItem = null;
		
		for (int i = 0; i < registeredItemsList.size(); i++) {
			itemId = registeredItemsList.get(i).getItemId();
			itemQuantity = registeredItemsList.get(i).getQuantity();
			updateItemsList(currentIterationItem, itemId, itemQuantity);
		}
	}
	
	private void updateItemsList(ItemDTO currentIterationItem, int itemId, int itemQuantity) {
		for (int i = 0; i < items.size(); i++) {
			currentIterationItem = items.get(i).getItem();
			if (currentIterationItem.getItemId() == itemId) {
				items.set(i, new ItemAndQuantity(currentIterationItem, itemQuantity));
			}
		}
	}
}
