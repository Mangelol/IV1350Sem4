package se.kth.iv1350.pos.model;
import se.kth.iv1350.pos.util.Amount;

public interface PaidAmountObserver {
	
	/**
	 * This method updates the observer
	 * @param amountPaid The amount paid
	 */
	public void newPayment(Amount amountPaid);

}
