package se.kth.iv1350.pos.model;
import se.kth.iv1350.pos.util.Amount;

/**
 * Represents all the info about different prices that are relevant for the receipt
 * @author magnu
 *
 */
public class PriceInfo {
	private final Amount totalPrice;
	private final Amount totalVatPrice;
	private final Amount amountPaid;
	private final Amount change;
	
	/**
	 * This method creates a new instance and is used to improve encapsulation, coupling and cohesion
	 * @param totalPrice The total cost for a sale
	 * @param totalVatPrice The total VAT price for a sale
	 * @param amountPaid The amount the customer paid
	 * @param change The amount paid minus the total price
	 */
	public PriceInfo(Amount totalPrice, Amount totalVatPrice, Amount amountPaid, Amount change) {
		this.totalPrice = totalPrice;
		this.totalVatPrice = totalVatPrice;
		this.amountPaid = amountPaid;
		this.change = change;
	}
	
	public Amount getTotalPrice() {
		return totalPrice;
	}
	
	public Amount getTotalVatPrice() {
		return totalVatPrice;
	}
	
	public Amount getAmountPaid() {
		return amountPaid;
	}
	
	public Amount getChange() {
		return change;
	}
}
