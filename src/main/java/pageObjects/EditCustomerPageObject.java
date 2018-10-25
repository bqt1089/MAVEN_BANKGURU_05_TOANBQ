package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class EditCustomerPageObject extends AbstractPage {
	WebDriver driver;
	
	public EditCustomerPageObject  (WebDriver mappingDriver) {
		driver = mappingDriver;
	}
	

}
