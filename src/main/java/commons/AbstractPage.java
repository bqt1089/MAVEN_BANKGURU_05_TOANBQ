package commons;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.EditCustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.PageFactoryManager;
import pageUIs.AbtractPageUI;

public class AbstractPage {

	/* ==== Web Browser==== */

	public void openUrl(WebDriver driver, String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backPreviousPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	/* ===== Web Element ===== */

	public void clickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void clickToElement(WebDriver driver, String locator, String... locatorValues) {
		locator = String.format(locator, (Object[]) locatorValues);
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);
	}

	public void sendKeyToElement(WebDriver driver, String locator, String inputValue, String... locatorValues) {
		locator = String.format(locator, (Object[]) locatorValues);
		WebElement element = driver.findElement(By.xpath(locator));
//		element.clear();
		element.sendKeys(inputValue);
	}

	public void selectItemInDropDown(WebDriver driver, String locator, String item) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		select.deselectByVisibleText(item);
	}

	public void selectCustomDropdownList(WebDriver driver, String locator, String itemsList, String itemvalue) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement element = driver.findElement(By.xpath(locator));
		((JavascriptExecutor) driver).executeScript("arguments.scrollIntoView(true);", element);
		element.click();
		List<WebElement> allItems = driver.findElements(By.xpath(itemsList));
		wait.until(ExpectedConditions.visibilityOfAllElements(allItems));

		for (WebElement items : allItems) {
			System.out.println("List items : " + items.getText());
			if (items.getText().trim().equals(itemvalue)) {
				// Scroll to item
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", items);
				items.click();
				break;
			}
		}
	}

	public String getFirstItemSelected(WebDriver driver, String locator) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		return select.getFirstSelectedOption().getText();
	}

	public String getAttribute(WebDriver driver, String locator, String attributeName) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public String getTextElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}

	public int getSizeElement(WebDriver driver, String locator) {
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		return elements.size();
	}

	public void checkToCheckBox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckBox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlDisplayed(WebDriver driver, String locator, String... locatorValues) {
		locator = String.format(locator, (Object[]) locatorValues);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlSelected(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public boolean isControlEnabled(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}

	public void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void sendKeyToAlert(WebDriver driver, String keytext) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(keytext);
	}

	public String getParentWindow(WebDriver driver) {
		return driver.getWindowHandle();
	}

	public void swithToChildWinDownByIDOnly2Windows(WebDriver driver, String parentWindow) {
		Set<String> allWindowns = driver.getWindowHandles();
		for (String runWindown : allWindowns) {
			if (!runWindown.equals(parentWindow)) {
				driver.switchTo().window(runWindown);
				break;
			}
		}
	}

	public void customSwitchToChildWindownByTitle(WebDriver driver, String title) {
		Set<String> allWindowns = driver.getWindowHandles();
		for (String runWindow : allWindowns) {
			driver.switchTo().window(runWindow);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWithoutParentWindows(WebDriver driver, String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void switchToIframeByWebElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		driver.switchTo().frame(element);
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	public void dragAndDrop(WebDriver driver, String sourceToDrag, String targetToDrop) {
		WebElement sourceButton = driver.findElement(By.xpath(sourceToDrag));
		WebElement targetButton = driver.findElement(By.xpath(targetToDrop));
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceButton, targetButton);
	}

	public void customDragAndDrop(WebDriver driver, String sourceToDrag, String targetToDrop) {
		WebElement sourceButton = driver.findElement(By.xpath(sourceToDrag));
		WebElement targetButton = driver.findElement(By.xpath(targetToDrop));
		Actions action = new Actions(driver);
		action.clickAndHold(sourceButton);
		action.moveToElement(targetButton);
		action.release().perform();
	}

	public void keyControlPressDown(WebDriver driver, String locator, int[] valueToClick) {
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).build().perform();
		for (int i : valueToClick) {
			elements.get(i).click();
		}
		action.keyUp(Keys.CONTROL).build().perform();
	}

	public void uploadFileBySendKey(WebDriver driver, String locator, String fileName) {
		String proDir = System.getProperty("user.dir");
		String filePath = proDir + "\\fileUpload\\" + fileName;
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(filePath);
	}

	public void uploadMultiFileBySendKey(WebDriver driver, String locator, String[] filesUpload) {
		String proDir = System.getProperty("user.dir");
		WebElement element = driver.findElement(By.xpath(locator));
		for (int i = 0; i < filesUpload.length; i++) {
			String filePath = proDir + "\\fileUpload\\" + filesUpload[i];
			element.sendKeys(filePath);
		}
	}

	public void keyPressToElement(WebDriver driver, String locator, Keys keyName) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.sendKeys(element, keyName);
	}

	public Object executeForBrowserElement(WebDriver driver, String javaSript) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public void navigateBrowserByJS(WebDriver driver, String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.location = '" + url + "'");
	}

	public String getDomainByJS(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String domain = (String) js.executeScript("return document.domain");
		return domain;
	}

	public String getUrlPageByJS(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String URL = (String) js.executeScript("return document.URL");
		return URL;
	}

	public String getTitlePageByJS(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String sText = js.executeScript("return document.title;").toString();
		return sText;
	}

	public String getInnerTextByJS(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String sText = js.executeScript("return document.documentElement.innerText;").toString();
		return sText;
	}

	public void refreshBrowserByJS(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
	}

	public void clickElementByJS(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	public void clickElementByJS(WebDriver driver, String locator, String... locatorValues) {
		locator = String.format(locator, (Object[]) locatorValues);
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void highlightElementByJS(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='2px groove green'", element);
	}

	public Object executeForWebElement(WebDriver driver, WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object removeAttributeInDOM(WebDriver driver, WebElement element, String attribute) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object scrollToBottomPage(WebDriver driver) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public void scrollToElementByJS(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/* =====WAIT==== */

	public void waitForControlPresence(WebDriver driver, String byIDLocator) {
		By locator = By.id(byIDLocator);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForControlVisible(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForControlVisible(WebDriver driver, String locator, String... locatorValues) {
		locator = String.format(locator, (Object[])locatorValues);
		WebElement element = driver.findElement(By.xpath(locator));
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForControlClickAble(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForControlNotVisible(WebDriver driver, String byIDLocator) {
		By locator = By.id(byIDLocator);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForAlertPresence(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	/* ===== Open any Page==== */

	public HomePageObject openHomePage(WebDriver driver) {
		waitForControlVisible(driver, AbtractPageUI.DYNAMIC_XPATH_OPENPAGE, "Manager");
		clickToElement(driver, AbtractPageUI.DYNAMIC_XPATH_OPENPAGE, "Manager");
		return PageFactoryManager.getHomePageDriver(driver);
	}

	public NewCustomerPageObject openNewCustomerPage(WebDriver driver) {
		waitForControlVisible(driver, AbtractPageUI.DYNAMIC_XPATH_OPENPAGE, "New Customer");
		clickToElement(driver, AbtractPageUI.DYNAMIC_XPATH_OPENPAGE, "New Customer");
		return PageFactoryManager.getNewCustomerPageObjectDriver(driver);
	}

	public EditCustomerPageObject openEditCustomerPage(WebDriver driver) {
		waitForControlVisible(driver, AbtractPageUI.DYNAMIC_XPATH_OPENPAGE, "Edit Customer");
		clickToElement(driver, AbtractPageUI.DYNAMIC_XPATH_OPENPAGE, "Edit Customer");
		return PageFactoryManager.getEditCustomerPageDriver(driver);
	}

	public NewAccountPageObject openNewAccountPage(WebDriver driver) {
		waitForControlVisible(driver, AbtractPageUI.DYNAMIC_XPATH_OPENPAGE, "New Account");
		clickToElement(driver, AbtractPageUI.DYNAMIC_XPATH_OPENPAGE, "New Account");
		return PageFactoryManager.getNewAccountPageObject(driver);
	}

	public HomePageObject openLogoutPage(WebDriver driver) {
		waitForControlVisible(driver, AbtractPageUI.DYNAMIC_XPATH_OPENPAGE, "Log out");
		clickToElement(driver, AbtractPageUI.DYNAMIC_XPATH_OPENPAGE, "Log out");
		acceptAlert(driver);
		return PageFactoryManager.getHomePageDriver(driver);
	}

	public boolean isControlUndisplayed(WebDriver driver, String locator) {
		Date date = new Date();
		System.out.println("Started time = " + date.toString());
		overrideGlobalTimeout(driver, shorTimeout);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		if (elements.size() == 0) {
			date = new Date();
			System.out.println("End time = " + date.toString());
			overrideGlobalTimeout(driver, timeout);
			return true;
		} else {
			date = new Date();
			System.out.println("End time = " + date.toString());
			overrideGlobalTimeout(driver, timeout);
			return false;
		}
	}

	public boolean isControlUndisplayed(WebDriver driver, String locator, String... value) {
		Date date = new Date();
		System.out.println("Started time = " + date.toString());
		overrideGlobalTimeout(driver, shorTimeout);
		locator = String.format(locator, (Object[]) value);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		if (elements.size() == 0) {
			date = new Date();
			System.out.println("End time = " + date.toString());
			overrideGlobalTimeout(driver, timeout);
			return true;
		} else {
			date = new Date();
			System.out.println("End time = " + date.toString());
			overrideGlobalTimeout(driver, timeout);
			return false;
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public int randomNumber() {
		Random num = new Random();
		int n = num.nextInt(99999999) + 1;
		return n;
	}

	// DYNAMIC PAGE & DYNAMIC XPATH

	public void sendkeyToDynamicTextBox(WebDriver driver, String locatorValue, String inputValue) {
		waitForControlVisible(driver, AbtractPageUI.DYNAMIC_TEXTBOX, locatorValue);
		sendKeyToElement(driver, AbtractPageUI.DYNAMIC_TEXTBOX, inputValue, locatorValue);
	}

	public void sendkeyToDynamicTextArea(WebDriver driver, String locatorValue, String inputValue) {
		waitForControlVisible(driver, AbtractPageUI.DYNAMIC_TEXTAREA, locatorValue);
		sendKeyToElement(driver, AbtractPageUI.DYNAMIC_TEXTAREA, inputValue, locatorValue);
	}

	public void clickAnyDynamicRadioButton(WebDriver driver, String locatorValue) {
		waitForControlVisible(driver, AbtractPageUI.DYNAMIC_RADIO_BUTTON, locatorValue);
		clickToElement(driver, AbtractPageUI.DYNAMIC_RADIO_BUTTON, locatorValue);
	}

	public void clickToDynamicButton(WebDriver driver, String locatorValue) {
		waitForControlVisible(driver, AbtractPageUI.DYNAMIC_TEXTBOX, locatorValue);
		if (driver.toString().toLowerCase().contains("firefox")) {
			clickElementByJS(driver, AbtractPageUI.DYNAMIC_TEXTBOX, locatorValue);
		} else {
		clickToElement(driver, AbtractPageUI.DYNAMIC_TEXTBOX, locatorValue);
		}
	}

	private int timeout = 30;
	private int shorTimeout = 5;

}
