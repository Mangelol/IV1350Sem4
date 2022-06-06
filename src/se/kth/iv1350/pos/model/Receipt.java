package se.kth.iv1350.pos.model;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import se.kth.iv1350.pos.util.Address;

/**
 * Info for a receipt
 * @author magnu
 *
 */
public class Receipt {
	private final PriceInfo priceInfo;
	private final Date saleTime;
	private final Address address = new Address();
	private final List<ItemAndQuantity> boughtItems;
	
	StringBuilder string = new StringBuilder();
	
	/**
	 * Creates a new instance. A new receipt can't be created until sale has ended
	 * @param priceInfo This includes the total price and total VAT for the entire sale
	 * @param saleTime This represents the time and date of a sale
	 * @param boughtItems This represents a list of all the bought items and the quantity for every item
	 */
	public Receipt(PriceInfo priceInfo, Date saleTime, List<ItemAndQuantity> boughtItems) {
		this.priceInfo = priceInfo;
		this.saleTime = saleTime;
		this.boughtItems = boughtItems;
	}
	
	/**
	 * Creates a formatted string with all the info for a receipt that is to be printed
	 * @return
	 */
	public StringBuilder stringBuilderForReceipt() {
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
		string.append("RECEIPT\n");
		string.append(address.getStoreName()).append("\n");
		string.append(df.format(saleTime)).append("\n\n");
		appendBoughtItems();
		appendPrices();
		string.append(getStoreAddress());
		
		return string;
	}
	
	private void appendPrices() {
		string.append("\nTotal price (excl. VAT): ");
		string.append(priceInfo.getTotalPrice().subtract(priceInfo.getTotalVatPrice())).append("\n");
		string.append("Total VAT: ");
		string.append(priceInfo.getTotalVatPrice()).append("\n");
		string.append("\nTotal price (incl. VAT): ");
		string.append(priceInfo.getTotalPrice()).append("\n");
		string.append("\nAmount paid: ");
		string.append(priceInfo.getAmountPaid());
		string.append("\nChange: ");
		string.append(priceInfo.getChange()).append("\n");
	}
	
	private String getStoreAddress() {
		String storeAddress = "\n" + address.getStreet() + "\n" + address.getCity() + "\n" + address.getZipCode() + "\n" + address.getCountry();
		
		return storeAddress;
	}
	
	private void appendBoughtItems() {
		for (int i = 0; i < boughtItems.size(); i++) {
			string.append(boughtItems.get(i).getItemName()).append(" ");
			string.append(boughtItems.get(i).getQuantity()).append(" x ");
			string.append(boughtItems.get(i).getPrice()).append("\n");
		}
	}
}
