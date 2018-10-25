package com.bankguru.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class User_01_Login_Level_0_StepByStep {
	WebDriver driver;
	private String currentUrl, newEmail, newPass;

	@Test
	public void TC_01_Register() {
		// Get current URL
		currentUrl = driver.getCurrentUrl();

		// click to Here button
		driver.findElement(By.xpath("//a[text()='here']")).click();

		// Send Email to register new account
		WebElement emailRegistTextBox = driver.findElement(By.xpath("//input[@name='emailid']"));
		emailRegistTextBox.sendKeys("toanbui" + randomNumber() + "@gmail.com");

		// Click submit button
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		// Get newmail and password
		newEmail = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText().trim();
		newPass = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText().trim();
		System.out.println(newEmail + " &&&& " + newPass);
	}

	@Test
	public void TC_02_Login() {
		// Get URL homepage
		driver.get(currentUrl);

		// Input Email and Password to Login text box
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(newEmail);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(newPass);

		// Click Login button
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		// Verify Login success
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='menusubnav']")).isDisplayed());

		// Click logout button
		driver.findElement(By.xpath("//a[contains(.,'Log out')]")).click();

	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

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
