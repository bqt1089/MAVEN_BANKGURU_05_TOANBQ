package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import commons.AbstractPage;
import pageUIs.HomePageUI;

public class HomePageObject extends AbstractPage {
	WebDriver driver;

	public HomePageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
	
	public boolean verifyMenuBarDisplayed() {
		waitForControlVisible(driver, HomePageUI.MENU_BAR);
		WebElement element = driver.findElement(By.xpath(HomePageUI.MENU_BAR));
		return element.isDisplayed();
	}
	
	public void clickLogoutButton() {
		waitForControlVisible(driver, HomePageUI.LOGOUT_BUTTON);
		clickToElement(driver, HomePageUI.LOGOUT_BUTTON);
	}
	
	public void verifyUserIDUndisplay(String value) {
		System.out.println(isControlUndisplayed(driver, HomePageUI.MANAGER_ID_TEXT, value));
		Assert.assertTrue(isControlUndisplayed(driver, HomePageUI.MANAGER_ID_TEXT, value));
	}
}
