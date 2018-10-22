package pageObjects;

import java.util.Random;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;

	public RegisterPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	public void inputEmailID() {
		waitForControlVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, "toanbui" + randomNumber() + "@gmail.com");
	}
	
	public void clickSubmitButton() {
		waitForControlVisible(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}
	
	public String getUserID() {
		waitForControlVisible(driver, RegisterPageUI.NEW_USERID_TEXT);
		return getTextElement(driver, RegisterPageUI.NEW_USERID_TEXT);
	}
	public String getPassword() {
		waitForControlVisible(driver, RegisterPageUI.NEW_PASSWORD_TEXT);
		return getTextElement(driver, RegisterPageUI.NEW_PASSWORD_TEXT);
	}
	
	
	
	public int randomNumber() {
		Random num = new Random();
		int n = num.nextInt(99999999) + 1;
		return n;
	}
	
	public void openUrl ( String url) {
		openUrl(driver, url);
	}
}
