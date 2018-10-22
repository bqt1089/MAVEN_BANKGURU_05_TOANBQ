package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AbstractTest {
	WebDriver driver;

	public WebDriver openMultiBrowser(String browser, String url) {
		if (browser.toLowerCase().contains("chrome")) {
			if (checkOs().toLowerCase().contains("mac")) {
				System.setProperty("webdriver.chrome.driver",  ".//src/test/resources/chromedriver");
			} else if (checkOs().toLowerCase().contains("windown")) {
				System.setProperty("webdriver.chrome.driver",  ".//src/test/resources/chromedriver.exe");
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
				System.setProperty("webdriver.chrome.driver",  ".//src/test/resources/chromedriver");
			} else if (checkOs().toLowerCase().contains("windown")) {
				System.setProperty("webdriver.chrome.driver",   ".//src/test/resources/chromedriver.exe");
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

	String pathProject = System.getProperty("user.dir");
}
