package commons;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class AbstractTest {
	WebDriver driver;
	protected final Log log;

	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	public WebDriver openMultiBrowser(String browser, String url) {
		if (browser.toLowerCase().contains("chrome")) {
			if (checkOs().toLowerCase().contains("mac")) {
				System.setProperty("webdriver.chrome.driver", ".//src/test/resources/chromedriver");
			} else if (checkOs().toLowerCase().contains("windown")) {
				System.setProperty("webdriver.chrome.driver", ".//src/test/resources/chromedriver.exe");
			} else {
				System.out.println("Error OS");
			}

			driver = new ChromeDriver();
			driver.get(url);
		} else if (browser.toLowerCase().contains("firefox")) {
			driver = new FirefoxDriver();
			driver.get(url);
		} else if (browser.toLowerCase().contains("headless")) {
			if (checkOs().toLowerCase().contains("mac")) {
				System.setProperty("webdriver.chrome.driver", ".//src/test/resources/chromedriver");
			} else if (checkOs().toLowerCase().contains("windown")) {
				System.setProperty("webdriver.chrome.driver", ".//src/test/resources/chromedriver.exe");
			} else {
				System.out.println("Error OS");
			}
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1366x768");
			driver = new ChromeDriver(options);
			driver.get(url);
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public String checkOs() {
		String osName = System.getProperty("os.name");
		return osName;
	}

	// Hàm close browser driver
	protected void closeBrowser(WebDriver driver) {
		try {
			// Detect OS (Windows/ Linux/ MAC)
			String osName = System.getProperty("os.name").toLowerCase();
			String cmd = "";
			driver.quit();
			if (driver.toString().toLowerCase().contains("chrome")) {
				// Kill process
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			if (driver.toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill firefoxdriver";
				} else {
					cmd = "taskkill /F /FI \"IMAGENAME eq firefoxdriver*\"";
				}
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			if (driver.toString().toLowerCase().contains("internetexplorer")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean checkPassed(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			if (condition == true)
				log.info("===PASSED===");
			else
				log.info("===FAILED===");

		} catch (Throwable e) {
			pass = false;
			log.info(e);

			// Add status (true/ false) to Report (ReportNG)
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkPassed(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true)
				log.info("===PASSED===");
			else
				log.info("===FAILED===");
			Assert.assertFalse(condition);

		} catch (Throwable e) {
			pass = false;
			log.info(e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}

		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {

			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			pass = false;
			log.info(e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	String pathProject = System.getProperty("user.dir");
}
