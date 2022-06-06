package se.kth.iv1350.pos.view;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class handles a error message
 * @author magnu
 *
 */
public class ErrorMessageHandler {
	
	/**
	 * The method that displays the error message
	 * @param message The specific error message
	 */
	void displayErrorMsg(String message) {
		StringBuilder errorMsgBuilder = new StringBuilder();
		errorMsgBuilder.append(currentLocalTime());
		errorMsgBuilder.append("\nERROR: ");
		errorMsgBuilder.append(message);
		System.out.println(errorMsgBuilder);
	}
	
	private String currentLocalTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		return now.format(formatter);
	}
}
