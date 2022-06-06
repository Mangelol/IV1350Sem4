package se.kth.iv1350.pos.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * This class is used for writing the log file
 * @author magnu
 *
 */
public class LogHandler {
	private final PrintWriter logFile;
	private static final String LOG_FILE_NAME = "logPOS.txt";
	
	public LogHandler() throws IOException {
		logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
	}
	
	public void logException(Exception exception) {
		StringBuilder logMessageBuilder = new StringBuilder();
		logMessageBuilder.append("Exception thrown: ");
		logMessageBuilder.append(exception.getMessage());
		if (exception.getMessage().contains("database")) {
			logFile.println(currentLocalTime() + logMessageBuilder);
		}
		
		System.out.println(logMessageBuilder);
		exception.printStackTrace(logFile);
	}
	
	private String currentLocalTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		return now.format(formatter) + ", ";
	}
}