package se.kth.iv1350.pos.controller;
import se.kth.iv1350.pos.integration.Inventory;
import se.kth.iv1350.pos.integration.ItemDTO;
import se.kth.iv1350.pos.integration.Accounting;
import se.kth.iv1350.pos.integration.ExternalSystemCreator;
import se.kth.iv1350.pos.integration.ItemIdNotFoundException;
import se.kth.iv1350.pos.model.CashPayment;
import se.kth.iv1350.pos.model.Sale;
import se.kth.iv1350.pos.model.TotalDTO;
import se.kth.iv1350.pos.model.PaidAmountObserver;
import se.kth.iv1350.pos.view.TotalRevenueView;
import se.kth.iv1350.pos.util.Amount;
import se.kth.iv1350.pos.view.TotalRevenueFileOutput;
import se.kth.iv1350.pos.view.View;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 * This is the only controller for the application. All calls to the model pass through here
 * @author magnu
 *
 */

public class Controller {
	private Amount amountPaid;
	private Amount change;
	private Amount totalCost;
	private Sale sale;
	private final Accounting acc;
	private final Inventory inv;
	private ItemDTO registeredItem;
	//private TotalDTO total;
	private CashPayment payment;
	//private PaidAmountObserver observer;
	private List<PaidAmountObserver> paidAmountObservers = new ArrayList<>();
	//private PaidAmountObserver observer = new TotalRevenueView(new Amount(0));
	//private PaidAmountObserver observer2 = new TotalRevenueFileOutput(new Amount(0));
	
	/**
	 * Creates a new instance
	 * @param systemCreator Creates a new instance of external systems
	 */
	public Controller(ExternalSystemCreator systemCreator) {
		acc = systemCreator.getAccounting();
		inv = systemCreator.getInventory();
	}
	
	/**
	 * This method starts a new sale. It must be called first in sale.
	 */
	public void startSale() {
		sale = new Sale();
		sale.addObservers(paidAmountObservers);
	}
	
	/**
	 * This method registers a new item. It gets called for every registration of every item.
	 * @param itemId Is used to identify the specific item
	 * @param quantity Is the amount of the specific item
	 * @return Returns item price, description and running price
	 * @throws se.kth.iv1350.pos.integration.ItemIdNotFoundException
	 * @throws se.kth.iv1350.pos.integration.InventoryException
	 * @throws java.sql.SQLException
	 */
	public TotalDTO registerItem(int itemId, int quantity) throws ItemIdNotFoundException, SQLException {
		ItemDTO registeredItem;
		
		if (quantity == 0) {
			quantity = 1;
		}
		
		if (!inv.findItem(itemId)) {
			throw new ItemIdNotFoundException(itemId);
		}
		
		registeredItem = inv.fetchItem(itemId);
		TotalDTO total = sale.currentRegisteredItemInfo(registeredItem, quantity);
		
		return total;
	}
	
	/**
	 * When every item is registered the sale ends. The running total will be the same as total price
	 * for the entire sale.
	 * @return Total prie for the entire sale is returned
	 */
	public Amount endSale() {
		totalCost = sale.getRunningTotal();
		payment = new CashPayment(totalCost);
		
		return totalCost;
	}
	

	/**
	 * This method pays the paid amount to payment. Sets the value for paid amount, then the change
	 * can be calculated based on that.
	 * @param amountPaid The amount paid by the customer
	 * @return Returns the change
	 */
	public Amount pay(Amount amountPaid) {
		this.amountPaid = amountPaid;
		payment.setAmountPaid(amountPaid);
		//observer.newPayment(amountPaid);
	//	observer.newPayment(amountPaid);
		change = payment.getCalculatedChange();
		
		return change;
	}
	
	/**
	 * This method prints the receipt and updates the external systems
	 */
	public void printReceipt() {
		sale.updateExternalSystems();
		sale.printReceipt(totalCost, amountPaid, change);
	}
	
	public void addPaidAmountObserver(PaidAmountObserver observer) {
		this.paidAmountObservers.add(observer);
	} 
}