package se.kth.iv1350.pos.view;
import se.kth.iv1350.pos.model.PaidAmountObserver;
import se.kth.iv1350.pos.util.Amount;

/**
 * This class represents the revenue, the total amount paid for all purchases
 * since the program started
 * @author magnu
 *
 */
public class TotalRevenueView implements PaidAmountObserver {
		private Amount totAmountPaid;
		
		/**
		 * This method creates a new instance that should be called when the program starts
		 * @param startAmount The Amount paid is set to 0 when the instance is created
		 */
		public TotalRevenueView(Amount startAmount) {
			this.totAmountPaid = startAmount;
		}
		
		@Override
		public void newPayment(Amount amountPaid) {
			totAmountPaid = totAmountPaid.add(amountPaid);
			System.out.println("\nThe total amount paid since start of program: " + totAmountPaid);
		}
}
