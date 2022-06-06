package se.kth.iv1350.pos.model;
import se.kth.iv1350.pos.util.Amount;

/**
 * Represents a payment for a sale
 * @author magnu
 *
 */
public class CashPayment {
	Amount amountPaid;
	Amount totalCost;
	Amount change;
	
	/**
	 * Creates a new instance of a payment
	 * @param totalCost The total cost for the sale
	 */
	public CashPayment(Amount totalCost) {
		this.totalCost = totalCost;
	}
	
	public void setAmountPaid(Amount amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	public Amount getAmountPaid() {
		return amountPaid;
	}
	
	public Amount getTotalCost() {
		return totalCost;
	}
	
	public Amount getCalculatedChange() {
		change = amountPaid.subtract(totalCost);
		return change;
	}
}
