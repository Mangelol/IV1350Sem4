package se.kth.iv1350.pos.integration;
import se.kth.iv1350.pos.model.SaleDTO;

/**
 * Updates the external systems and to keep low coupling
 * @author magnu
 *
 */
public class SystemUpdater {
	Accounting acc = new Accounting();
	Inventory inv = new Inventory();
	
	public SystemUpdater() {
	}
	
	/**
	 * This method updates the external systems
	 * @param saleInfo Contains information about the bought items in a sale and the prices
	 */
	public void updateSystems(SaleDTO saleInfo) {
		acc.updateAccounting(saleInfo);
		inv.updateInventory(saleInfo);
	}
}
