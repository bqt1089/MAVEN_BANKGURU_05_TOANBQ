package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;
	
	public LoginPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	public void clickRegistHereButton() {
		waitForControlVisible(driver, LoginPageUI.HERE_BUTTON);
		clickToElement(driver, LoginPageUI.HERE_BUTTON);
	}

	public void inputUserID(String userName) {
		waitForControlVisible(driver, LoginPageUI.USERID_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.USERID_TEXTBOX, userName);
	}

	public void inputPassword(String password) {
		waitForControlVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public void clickLoginButton() {
		waitForControlVisible(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}
	public String getLoginPageUrl() {
		return driver.getCurrentUrl();
	}
	
}
