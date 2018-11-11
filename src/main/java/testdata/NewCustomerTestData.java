package testdata;

import java.util.Random;

public class NewCustomerTestData {
	public static final String customerName = "Toan Bui";
	public static final String dateOfBirth = "10/10/2000";
	public static final String address = "K123 Le Duan";
	public static final String city = "Da Nang";
	public static final String state = "Thanh Khe";
	public static final String pin = "123456";
	public static final String mobileNumber = "0123456789";
	public static final String email = "toanbui" + randomNumber() + "@gmail.com";
	public static final String password = "123456";

	
	
	
	public static String randomNumber() {
		Random num = new Random();
		int n = num.nextInt(99999999) + 1;
		String number = String.valueOf(n);
		return number;
	}
}
