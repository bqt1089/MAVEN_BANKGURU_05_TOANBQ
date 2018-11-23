package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class NewCustomerPageObject extends AbstractPage {
	WebDriver driver;

	public NewCustomerPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

}
