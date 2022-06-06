package se.kth.iv1350.pos.view;
import se.kth.iv1350.pos.util.Amount;
import se.kth.iv1350.pos.model.PaidAmountObserver;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TotalRevenueFileOutput implements PaidAmountObserver {
	private Amount totAmountPaid;
//	private Amount startAmount;
	private PrintWriter revenueLogger;
	//private static final String LOG_FILE_NAME = "revenueLog.txt";
	
	public TotalRevenueFileOutput(Amount startAmount) {
		//this.totAmountPaid = startAmount;
		this.totAmountPaid = startAmount;
		
		try {
			revenueLogger = new PrintWriter(new FileWriter("revenueLog.txt"), true);
			//totAmountPaid = totAmountPaid.add(amountPaid);
			//revenueLogger.println(currentLocalTime() + "\nThe total amount paid since start of program: " + totAmountPaid);
		} catch (IOException exception) {
			System.out.println("Failed to log total revenue due to an error");
			exception.printStackTrace();
		}
	}

	@Override
	public void newPayment(Amount amountPaid) {
		totAmountPaid = totAmountPaid.add(amountPaid);
		revenueLogger.println(currentLocalTime() + "\nThe total amount paid since start of program: " + totAmountPaid);
	}
	
	private String currentLocalTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		return now.format(formatter) + ", ";
	}

}