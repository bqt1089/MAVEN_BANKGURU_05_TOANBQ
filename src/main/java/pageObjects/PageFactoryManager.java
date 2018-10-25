package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageFactoryManager {

	public static HomePageObject getHomePageDriver(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static LoginPageObject getLoginPageDriver(WebDriver driver) {
		return new LoginPageObject(driver);
	}

	public static RegisterPageObject getRegisterPageDriver(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

	public static EditCustomerPageObject getEditCustomerPageDriver(WebDriver driver) {
		return new EditCustomerPageObject(driver);
	}

	public static NewCustomerPageObject getNewCustomerPageObjectDriver(WebDriver driver) {
		return new NewCustomerPageObject(driver);
	}

	public static NewAccountPageObject getNewAccountPageObject(WebDriver driver) {
		return new NewAccountPageObject(driver);
	}

}
