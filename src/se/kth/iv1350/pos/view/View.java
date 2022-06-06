package se.kth.iv1350.pos.view;
import se.kth.iv1350.pos.util.Amount;
import se.kth.iv1350.pos.util.LogHandler;
import se.kth.iv1350.pos.model.TotalDTO;
import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.integration.ItemIdNotFoundException;
import se.kth.iv1350.pos.integration.InventoryException;
import se.kth.iv1350.pos.model.PaidAmountObserver;
import java.util.Scanner;
import java.io.IOException;
import java.sql.SQLException;
import java.io.PrintWriter;

/**
 * This is a replacement for the real view. It has hardcoded calls to all system operations
 * in controller
 * @author magnu
 *
 */
public class View {
	private final Controller contrl;
	private final Scanner in = new Scanner(System.in);
	private final LogHandler logger;
	//public final TotalRevenueFileOutput revenueLogger;
	//private PrintWriter revenueLogger;
	private final ErrorMessageHandler errorMessageHandler = new ErrorMessageHandler();
	
	/**
	 * This method creates a new instance. It uses specified controller for all calls to other layers
	 * @param contrl This controller is used for all calls to the other layers
	 * @throws java.io.IOException
	 */
	public View (Controller contrl) throws IOException {
		this.contrl = contrl;
		this.logger = new LogHandler();
		contrl.addPaidAmountObserver(new TotalRevenueView(new Amount(0)));
		contrl.addPaidAmountObserver(new TotalRevenueFileOutput(new Amount(0)));
	}
	
	/**
	 * Calls all the system operations in the controller to perform a fake sale
	 */
	public void runFakeExecution() throws ItemIdNotFoundException, InventoryException, SQLException {
		//PaidAmountObserver observer = new TotalRevenueView(new Amount(0));
		//PaidAmountObserver observer2 = new TotalRevenueFileOutput(new Amount(0));
		try {
			Amount price;
			Amount runningTotal;
			TotalDTO total;
			String itemDesc;
		
			contrl.startSale();
			System.out.println("New sale has been started");
			System.out.println();
			
			try {
				total = contrl.registerItem(2, 2);
				price = total.getPrice();
				runningTotal = total.getRunningTotal();
				itemDesc = total.getItemDesc();
				System.out.println("Item: " + itemDesc + "\nPrice: " + price + "\nRunning Total: " + runningTotal);
				System.out.println();
			} catch(InventoryException except) {
				exceptionHandle("Failed to register item", except);
			}
			
			try {
				total = contrl.registerItem(9, 2);
				itemDesc = total.getItemDesc();
				price = total.getPrice();
				runningTotal = total.getRunningTotal();
				System.out.println("Item: " + itemDesc + "\nPrice: " + price + "\nRunning total: " + runningTotal);
				System.out.println();
			} catch(InventoryException | ItemIdNotFoundException except) {
				exceptionHandle("Failed to find existing item ID in inventory", except);
			}
			
		
			Amount totalPrice = contrl.endSale();
			System.out.println("Total price: " + totalPrice);
			System.out.println();
		
			System.out.println("Enter the amount paid: ");
			double amountPaidInput = in.nextDouble();
			Amount amountPaid = new Amount(amountPaidInput);
			System.out.println();
		
			Amount change = contrl.pay(amountPaid);
			System.out.println("Change: " + change);
			System.out.println();
		
			contrl.printReceipt(); //Prints the receipt
		
			} catch(SQLException | ItemIdNotFoundException except) {
				exceptionHandle("Item registration failed, please try again.", except);
			}
		}
		
		private void exceptionHandle(String uiMessage, Exception except) {
			errorMessageHandler.displayErrorMsg(uiMessage);
			logger.logException(except);
		}
}