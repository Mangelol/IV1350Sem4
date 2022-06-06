package se.kth.iv1350.pos.model;
import se.kth.iv1350.pos.integration.Printer;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.SystemUpdater;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import se.kth.iv1350.pos.util.Amount;

/**
 * Contains all the info about the current sale
 * @author magnu
 *
 */
public class Sale {
	private final Printer printer = new Printer();
	private final SystemUpdater systemUpdater = new SystemUpdater();
	private final Date saleTime;
	private Receipt infoForReceipt;
	private StringBuilder receipt;
	private PriceInfo priceInfo;
	private TotalDTO total;
	private Amount itemVatPrice;
	private Amount runningPrice = new Amount(0);
	private Amount totalVatPriceForEntireSale = new Amount(0);
	private Amount totAmountPaid;
	private List<ItemAndQuantity> registeredItemsList = new ArrayList<>();
	private List<PaidAmountObserver> paidAmountObservers = new ArrayList<>();
	
	/**
	 * This creates a new instance of Sale and sets the date and time for the start of the sale
	 */
	public Sale() {
		saleTime = new Date();
	}
	
	/**
	 * This method is used for every registered item
	 * @param currentRegisteredItem This represent the current registered item
	 * @param quantity This represents the amount of items that the customer wants to purchase
	 * @return An object with item description, running price and price of registered item is returned
	 */
	public TotalDTO currentRegisteredItemInfo(ItemDTO currentRegisteredItem, int quantity) {
		Amount itemPrice = currentRegisteredItem.getPrice();
		String itemDesc = currentRegisteredItem.getItemDesc();
		runningPrice = updateRunningPrice(currentRegisteredItem, quantity);
		registeredItemsList = addItemToRegisteredItemList(currentRegisteredItem, quantity);
		
		TotalDTO total = new TotalDTO(itemDesc, runningPrice, itemPrice);
		
		return total;
	}
	
	private Amount updateRunningPrice(ItemDTO currentRegisteredItem, int quantity) {
		Amount itemPrice = currentRegisteredItem.getPrice();
		Amount itemVatRate = currentRegisteredItem.getVAT();
		Amount itemPriceIncludingVat = calculateVatPrice(itemPrice, itemVatRate);
		
		// adds the price of the item * quantity of the item to running total
		for (int i = 0; i < quantity; i++) {
			runningPrice = runningPrice.add(itemPriceIncludingVat);
			totalVatPriceForEntireSale = totalVatPriceForEntireSale.add(itemVatRate);
		}
		return runningPrice;
	}
	
	private Amount calculateVatPrice(Amount itemPrice, Amount itemVatRate) {
		itemVatPrice = itemVatRate.multiply(itemPrice);
		Amount itemPriceIncludingVat = itemVatPrice.add(itemPrice);
		
		return itemPriceIncludingVat;
	}
	
	//very long method. Is explained in report
	private List<ItemAndQuantity> addItemToRegisteredItemList(ItemDTO item, int quantity) {
		ItemAndQuantity currentIterationItem;
		boolean itemAlreadyRegistered = false;
		int itemId = item.getItemId();
		int updatedQuantity;
		
		if (!registeredItemsList.isEmpty()) {
			for (int i = 0; i < registeredItemsList.size(); i++) {
				currentIterationItem = registeredItemsList.get(i);
				
				if (currentIterationItem.getItemId() == itemId) {
					itemAlreadyRegistered = true;
					updatedQuantity = increaseQuantity(currentIterationItem.getQuantity(), quantity);
					registeredItemsList.set(i,  new ItemAndQuantity(item, updatedQuantity));
				}
			}
			
			if (!itemAlreadyRegistered) {
				addItem(item, quantity);
			}
		}
		
		if (registeredItemsList.isEmpty()) {
			addItem(item, quantity);
		}
		
		return registeredItemsList;
	}
	
	private int increaseQuantity(int currentQuantity, int addedQuantity) {
		return currentQuantity + addedQuantity;
	}
	
	private void addItem(ItemDTO item, int quantity) {
		registeredItemsList.add(new ItemAndQuantity(item, quantity));
	}
	
	/**
	 * Gets running total
	 * @return Returns the running total for the entire sale
	 */
	public Amount getRunningTotal() {
		return runningPrice;
	}
	
	/**
	 * Contains all data about one sale for the receipt and sends the receipt to the printer
	 * @param totalCost The total price of the sale
	 * @param amountPaid The amount paid by customer
	 * @param change The amount paid - the total price
	 */
	public void printReceipt(Amount totalCost, Amount amountPaid, Amount change) {
		priceInfo = new PriceInfo(totalCost, totalVatPriceForEntireSale, amountPaid, change);
		infoForReceipt = new Receipt(priceInfo, saleTime, registeredItemsList);
		receipt = infoForReceipt.stringBuilderForReceipt();
		notifyObservers();
		printer.printReceipt(receipt);
	}
	
	/**
	 * The external systems is updated with new information
	 */
	public void updateExternalSystems() {
		SaleDTO saleInfo = new SaleDTO(registeredItemsList, priceInfo);
		systemUpdater.updateSystems(saleInfo);
	}
	
	public void addObservers(List<PaidAmountObserver> observer) {
		this.paidAmountObservers.addAll(observer);
	}
	
	public void notifyObservers() {
		for(PaidAmountObserver observer : this.paidAmountObservers) {
			observer.newPayment(getRunningTotal());
		}
	}
}
