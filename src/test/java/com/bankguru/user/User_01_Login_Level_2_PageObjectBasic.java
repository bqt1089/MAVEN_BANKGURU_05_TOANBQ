package com.bankguru.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractPage;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import pageUIs.LoginPageUI;

public class User_01_Login_Level_2_PageObjectBasic {
	WebDriver driver;
	private AbstractPage abstractPage;
	private LoginPageObject loginPageObject;
	private HomePageObject homePageObject;
	private RegisterPageObject registerPageObject;

	@Test
	public void TC_01_Register() {
		// Get current URL
		crUrl = loginPageObject.getCurrentUrl(driver);
		
		// click to Here button
		loginPageObject.clickRegistHereButton();
		
		// Send Email to register new account
		registerPageObject.inputEmailID();
		
		// Click submit button
		registerPageObject.clickSubmitButton();

		// Get newmail and password
		userID = registerPageObject.getUserID();
		password = registerPageObject.getPassword();
	}

	@Test
	public void TC_02_Login() {
		// Get URL homepage
		abstractPage.openUrl(driver, crUrl);
		
		// Input Email and Password to Login text box
		loginPageObject.inputUserID(userID);
		
		// Click Login button
		loginPageObject.inputPassword(password);
		
		// Verify Login success
		loginPageObject.clickLoginButton();
		
		// Click logout button
		homePageObject.verifyMenuBarDisplayed();
		homePageObject.clickLogoutButton();
	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		abstractPage = new AbstractPage();
		loginPageObject = new LoginPageObject(driver);
		homePageObject = new HomePageObject(driver);
		registerPageObject = new RegisterPageObject(driver);

		abstractPage.openUrl(driver, LoginPageUI.URL);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private String crUrl, userID, password;
}
