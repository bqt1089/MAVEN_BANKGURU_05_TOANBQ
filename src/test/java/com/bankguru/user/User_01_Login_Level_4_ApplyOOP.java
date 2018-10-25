package com.bankguru.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;

public class User_01_Login_Level_4_ApplyOOP extends AbstractTest {
	WebDriver driver;
	private LoginPageObject loginPageObject;
	private HomePageObject homePageObject;
	private RegisterPageObject registerPageObject;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String urlName) {
		driver = openMultiBrowser(browserName, urlName);

		loginPageObject = PageFactoryManager.getLoginPageDriver(driver);

	}

	@Test
	public void TC_01_Register() {
		// Get current URL
		crUrl = loginPageObject.getLoginPageUrl();

		// click to Here button
		registerPageObject = loginPageObject.clickRegistHereButton();

		// Send Email to register new account
		registerPageObject.inputEmailID();

		// Click submit button
		registerPageObject.clickSubmitButton();

		// Get new mail and password
		userID = registerPageObject.getUserID();
		password = registerPageObject.getPassword();
	}

	@Test
	public void TC_02_Login() {
		// Get URL homepage
		loginPageObject = registerPageObject.openUrl(crUrl);
		// Input Email and Password to Login text box
		loginPageObject.inputUserID(userID);

		// Click Login button
		loginPageObject.inputPassword(password);

		// Verify Login success
		homePageObject = loginPageObject.clickLoginButton();

		// Click logout button
		Assert.assertTrue(homePageObject.verifyMenuBarDisplayed());
		homePageObject.clickLogoutButton();
	}

	@Test
	public void TC_03_OpenMultiPage() {
		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private String crUrl, userID, password;
}
