package com.bankguru.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;
import pageUIs.AbtractPageUI;
import pageUIs.NewCustomerUI;
import testdata.NewCustomerTestData;

public class Account_01_Registerer_DynamicPageObject extends AbstractTest {
	WebDriver driver;
	private LoginPageObject loginPageObject;
	private HomePageObject homePageObject;
	private RegisterPageObject registerPageObject;
	private NewCustomerPageObject newCustomerPageObject;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String urlName) {
		driver = openMultiBrowser(browserName, urlName);

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
	public void TC_03_OpenMultiPage() {
		log.info("Create NewCustomer - Step 01 : Open Customer Page");
		newCustomerPageObject = homePageObject.openNewCustomerPage(driver);

		log.info("Create NewCustomer - Step 02 : Input All information");
		newCustomerPageObject.sendkeyToDynamicTextBox(driver, NewCustomerUI.CUSTOMER_NAME_FIELD, NewCustomerTestData.customerName);
		newCustomerPageObject.clickAnyDynamicRadioButton(driver, NewCustomerUI.CUSTOMER_RADIO_MALE);
		newCustomerPageObject.sendkeyToDynamicTextBox(driver, NewCustomerUI.DATE_OF_BIRTH_FIELD, NewCustomerTestData.dateOfBirth);
		newCustomerPageObject.sendkeyToDynamicTextArea(driver, NewCustomerUI.ADDRESS_TEXTBOX, NewCustomerTestData.address);
		newCustomerPageObject.sendkeyToDynamicTextBox(driver, NewCustomerUI.CITY_FIELD, NewCustomerTestData.city);
		newCustomerPageObject.sendkeyToDynamicTextBox(driver, NewCustomerUI.STATE_FIELD, NewCustomerTestData.state);
		newCustomerPageObject.sendkeyToDynamicTextBox(driver, NewCustomerUI.PIN_FIELD, NewCustomerTestData.pin);
		newCustomerPageObject.sendkeyToDynamicTextBox(driver, NewCustomerUI.MOBILE_FIELD, NewCustomerTestData.mobileNumber);
		newCustomerPageObject.sendkeyToDynamicTextBox(driver, NewCustomerUI.EMAIL_FIELD, NewCustomerTestData.email);
		newCustomerPageObject.sendkeyToDynamicTextBox(driver, NewCustomerUI.PASSWORD_FIELD, NewCustomerTestData.password);

		log.info("Create NewCustomer - Step 03 : Click Submit button");
		newCustomerPageObject.clickToDynamicButton(driver, NewCustomerUI.SUBMIT_BUTTON);

		log.info("TC_03 Create NewCustomer - Step 04 : Verify register success is displayed");
		verifyTrue(newCustomerPageObject.isControlDisplayed(driver,NewCustomerUI.REGISTER_SUCCESS_LOCATOR));

	}

	@Test
	public void TC_04__GetInforAfterRegistNewCustomer() {

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
		closeBrowser(driver);
	}

	private String crUrl, userID, password;
}
