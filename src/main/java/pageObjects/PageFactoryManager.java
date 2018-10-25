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

}
