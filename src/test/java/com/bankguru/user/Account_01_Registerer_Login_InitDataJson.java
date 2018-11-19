package com.bankguru.user;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import commons.AbstractTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;
import pageUIs.NewCustomerUI;
import testdata.GetCustomerData;
import testdata.NewCustomerTestData;

public class Account_01_Registerer_Login_InitDataJson extends AbstractTest {
	WebDriver driver;
	private LoginPageObject loginPageObject;
	private HomePageObject homePageObject;
	private RegisterPageObject registerPageObject;
	private NewCustomerPageObject newCustomerPageObject;
	private GetCustomerData customerData;
	
	@Parameters({ "browser", "url" , "newcustomer"})
	@BeforeClass
	public void beforeClass(String browserName, String urlName, String filename) throws JsonParseException, JsonMappingException, IOException {
		driver = openMultiBrowser(browserName, urlName);
		
		customerData = GetCustomerData.get(filename);
		loginPageObject = PageFactoryManager.getLoginPageDriver(driver);

	}

	@Test
	public void TC_01_Register() {
		log.info("Register - Step 01  : Open URL");
		crUrl = loginPageObject.getLoginPageUrl();

		log.info("Register - Step 02 : Click Regist Here button");
		registerPageObject = loginPageObject.clickRegistHereButton();

		log.info("Register - Step 03 : Input random Email");
		registerPageObject.inputEmailID();

		log.info("Register - Step 04 : Click Submit button");
		registerPageObject.clickSubmitButton();

		log.info("Register - Step 05 : Get User ID and Password");
		userID = registerPageObject.getUserID();
		password = registerPageObject.getPassword();
	}

	@Test
	public void TC_02_Login() {
		log.info("Login - Step 01 : Open  Login Page by open Url");
		loginPageObject = registerPageObject.openUrl(crUrl);

		log.info("Login - Step 02 : Input User ID");
		loginPageObject.inputUserID(userID);

		log.info("Login - Step 03 : Input Password");
		loginPageObject.inputPassword(password);

		log.info("Login - Step 04 : Click Login button");
		homePageObject = loginPageObject.clickLoginButton();

		log.info("Login - Step 05 : Verify Login success");
		homePageObject.verifyUserDisplay(userID);
	}

	@Test
	public void TC_03_CreateNewCustomer() {
		log.info("Create NewCustomer - Step 01 : Open Customer Page");
		newCustomerPageObject = homePageObject.openNewCustomerPage(driver);

		log.info("Create NewCustomer - Step 02 : Input All information");
		newCustomerPageObject.sendkeyToDynamicTextBox(driver, NewCustomerUI.CUSTOMER_NAME_FIELD, customerData.getCustomerName());
		newCustomerPageObject.sendkeyToDynamicTextArea(driver, NewCustomerUI.ADDRESS_TEXTBOX, customerData.getAddress());
		newCustomerPageObject.sendkeyToDynamicTextBox(driver, NewCustomerUI.CITY_FIELD, customerData.getCCCity());
		newCustomerPageObject.sendkeyToDynamicTextBox(driver, NewCustomerUI.STATE_FIELD, customerData.getCCState());
		newCustomerPageObject.sendkeyToDynamicTextBox(driver, NewCustomerUI.PIN_FIELD, customerData.getNumbPin());
		newCustomerPageObject.sendkeyToDynamicTextBox(driver, NewCustomerUI.MOBILE_FIELD, customerData.getNumbMobile());
		
		System.out.println("Customer Name : " + customerData.getCustomerName());
		System.out.println("Address : " + customerData.getAddress());
		System.out.println("City : " + customerData.getCCCity());
		System.out.println("State : " + customerData.getCCState());
		System.out.println("Pin : " + customerData.getNumbPin());
		System.out.println("Mobile : " + customerData.getNumbMobile());
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
		closeBrowser(driver);
	}

	private String crUrl, userID, password;
}
