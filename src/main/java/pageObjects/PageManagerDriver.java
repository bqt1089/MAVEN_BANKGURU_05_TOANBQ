package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageManagerDriver {
	private static HomePageObject homePageObject;
	private static LoginPageObject loginPageObject;
	private static RegisterPageObject registerPageObject;

	public static HomePageObject getHomePageDriver(WebDriver driver) {
		if (homePageObject == null) {
			new HomePageObject(driver);
		}
		return homePageObject;
	}

	public static LoginPageObject getLoginPageDriver(WebDriver driver) {
		if (loginPageObject == null) {
			new LoginPageObject(driver);
		}
		return loginPageObject;
	}

	public static RegisterPageObject getRegisterPageDriver(WebDriver driver) {
		if (registerPageObject == null) {
			new RegisterPageObject(driver);
		}
		return registerPageObject;
	}

}
