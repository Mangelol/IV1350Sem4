package se.kth.iv1350.pos.model;
import java.util.List;

/**
 * This class contains info about a sale and is used to update the inventory and accounting system
 * @author magnu
 *
 */
public class SaleDTO {
	private PriceInfo priceInfo;
	private List<ItemAndQuantity> registeredItems;
	
	/**
	 * A new instance is created and contains all information needed to update the inventory and accounting system
	 * @param registeredItems This contains all information about every item bought in one sale
	 * @param priceInfo This contains all information about every price in a sale that is necessary for accounting
	 */
	public SaleDTO(List<ItemAndQuantity> registeredItems, PriceInfo priceInfo) {
		this.priceInfo = priceInfo;
		this.registeredItems = registeredItems;
	}
	
	public List<ItemAndQuantity> getRegisteredItems() {
		return registeredItems;
	}
	
	public PriceInfo getPriceInfo() {
		return priceInfo;
	}
}
