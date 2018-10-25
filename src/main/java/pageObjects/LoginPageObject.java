package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;
	
	public LoginPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	public RegisterPageObject clickRegistHereButton() {
		waitForControlVisible(driver, LoginPageUI.HERE_BUTTON);
		clickToElement(driver, LoginPageUI.HERE_BUTTON);
		return PageFactoryManager.getRegisterPageDriver(driver);
	}

	public void inputUserID(String userName) {
		waitForControlVisible(driver, LoginPageUI.USERID_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.USERID_TEXTBOX, userName);
	}

	public void inputPassword(String password) {
		waitForControlVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public HomePageObject clickLoginButton() {
		waitForControlVisible(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageFactoryManager.getHomePageDriver(driver);
	}
	public String getLoginPageUrl() {
		return driver.getCurrentUrl();
	}
	
}
