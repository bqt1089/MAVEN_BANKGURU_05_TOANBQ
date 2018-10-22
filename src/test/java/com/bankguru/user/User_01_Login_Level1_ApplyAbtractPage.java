package com.bankguru.user;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.AbstractPage;

public class User_01_Login_Level1_ApplyAbtractPage {
	WebDriver driver;
	private String currentUrl, newEmail, newPass;
	private AbstractPage abstractPage;

	@Test
	public void TC_01_Register() {
		// Get current URL
		
		currentUrl = abstractPage.getCurrentUrl(driver);

		// click to Here button
		abstractPage.clickToElement(driver, "//a[text()='here']");

		// Send Email to register new account
		abstractPage.sendKeyToElement(driver, "//input[@name='emailid']", "toanbui" + randomNumber() + "@gmail.com");

		// Click submit button
		abstractPage.clickToElement(driver, "//input[@type='submit']");

		// Get newmail and password
		newEmail = abstractPage.getTextElement(driver, "//td[text()='User ID :']/following-sibling::td").trim();
		newPass = abstractPage.getTextElement(driver, "//td[text()='Password :']/following-sibling::td").trim();
		System.out.println(newEmail + " &&&& " + newPass);
	}

	@Test
	public void TC_02_Login() {
		// Get URL homepage
		abstractPage.openUrl(driver, currentUrl);

		// Input Email and Password to Login text box
		abstractPage.sendKeyToElement(driver, "//input[@name='uid']", newEmail);
		abstractPage.sendKeyToElement(driver, "//input[@name='password']", newPass);

		// Click Login button
		abstractPage.clickToElement(driver, "//input[@name='btnLogin']");

		// Verify Login success
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='menusubnav']")).isDisplayed());

		// Click logout button
		abstractPage.clickToElement(driver, "//a[contains(.,'Log out')]");

	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		abstractPage = new AbstractPage();
		
		abstractPage.openUrl(driver, "http://demo.guru99.com/v4/");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomNumber() {
		Random num = new Random();
		int n = num.nextInt(99999999) + 1;
		return n;
	}

}
