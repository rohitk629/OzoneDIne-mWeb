package base;

import static java.lang.Runtime.getRuntime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import cucumber.api.Scenario;

//import org.apache.commons.codec.binary.Base64;
import java.util.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.property.GetProperty;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions.VideoQuality;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

@SuppressWarnings("deprecation")
public class TestBase {

	public static AppiumDriver<MobileElement> driver;

	protected static WebDriverWait wait;

	public static final String XPATH = "xpath";
	public static final String ID = "id";

	public TestBase() {
		this.driver = driver;
	}

	/**
	 * To reset the app after completion of a scenario
	 */
	public void resetApp() {
		try {
			driver.resetApp();
			wait = new WebDriverWait(driver, 30);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To launch the in-built apps
	 * 
	 * @param bundleId
	 */
	public void activateApp(String bundleId) {
		try {
			driver.activateApp(bundleId); // Setting - "com.apple.Preferences"
			wait = new WebDriverWait(driver, 30);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To get the title of the page
	 */
	public String getCurrentTitle() {
		String title = "";
		try {
			title = driver.getTitle();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return title;
	}

	/**
	 * To get the URL of the page
	 */
	public String getCurrentURL() {
		String currentURL = "";
		try {
			currentURL = driver.getCurrentUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentURL;
	}

	/**
	 * Close the app which was provided in the capabilities at session creation
	 */
	public void tearDown() {
		try {
			driver.quit();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Runs the current app as a background app for the number of seconds Number of
	 * seconds to run App in background
	 */
	public void runAppInBackground(Duration seconds) {
		try {
			driver.runAppInBackground(seconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To hide the keyboard in the app
	 **/
	public void hideKeyboard() {
		try {
			driver.hideKeyboard();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
	}

	public void delay(long l) {
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setContext(String value) {
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
			if (contextName.contains(value)) {
				driver.context(contextName);
			}
		}
	}

	public void setBrowserScale(String scaleFactor) {
		((JavascriptExecutor) driver).executeScript("document.body.style.transform='scale(scaleFactor)';");
		wait = new WebDriverWait(driver, 30);

	}

	// To verify if keypad is open
	public boolean isKeyBoardOpen() {
		Keyboard Element = driver.getKeyboard();
		boolean isOpen = false;
		try {
			if (Element != null) {
				isOpen = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isOpen;
	}

	public void executeCommand(String command) throws IOException, InterruptedException {
		Process process = getRuntime().exec(command);
		process.waitFor();
		System.out.println(process.exitValue());
	}

	/**
	 * Long press on a element to view sub-options
	 **/
	public void longPress(LongPressOptions element) {
		@SuppressWarnings("rawtypes")
		TouchAction action = new TouchAction(driver);
		try {
			action.longPress(element).release().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ExpectedCondition<MobileElement> elementToBeChecked(final MobileElement element) {
		return new ExpectedCondition<MobileElement>() {

			public ExpectedCondition<WebElement> visibilityOfElement = ExpectedConditions.visibilityOf(element);

			@Override
			public MobileElement apply(WebDriver driver) {
				MobileElement element = (MobileElement) visibilityOfElement.apply(driver);
				try {
					if (element != null && element.getAttribute("checked").equals("true")) {
						return element;
					} else {
						return null;
					}
				} catch (StaleElementReferenceException e) {
					return null;
				}
			}

			@Override
			public String toString() {
				return "element to be checked : " + element;
			}
		};
	}

	public MobileElement waitForElementToBeClickable(MobileElement mobileElement) {

		return (MobileElement) new WebDriverWait(driver, 10)
				.until(ExpectedConditions.elementToBeClickable(mobileElement));

	}

	public MobileElement waitForElementToBeClickable(String inputElement) {
		MobileElement mobileElement = driver.findElement(By.xpath(inputElement));

		return (MobileElement) new WebDriverWait(driver, 10)
				.until(ExpectedConditions.elementToBeClickable(mobileElement));

	}

	public boolean waitForPageToBeLoaded() {
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("loaded")
				|| ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	}

	public void waitForElementToBeVisible(By by) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForElementToBeVisible(By by, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {

		}
	}

	public MobileElement waitForElementToBeVisible(MobileElement element) {
		return (MobileElement) new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
	}

	public MobileElement waitForElementToBeVisible(String xpath) {
		MobileElement element = getElement(XPATH, xpath);
		return (MobileElement) new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
	}

	public MobileElement waitForVisibility(MobileElement e) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(45))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

		return (MobileElement) wait.until(ExpectedConditions.visibilityOf(e));

	}

	public MobileElement waitForVisibility(By by) {
		MobileElement e = driver.findElement(by);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

		return (MobileElement) wait.until(ExpectedConditions.visibilityOf(e));

	}

	public void click_last(List<MobileElement> element) {
		waitForElementToBeClickable(getLast(element));
		getLast(element).click();
	}

	public MobileElement getLast(List<MobileElement> element) {
		return element.get(element.size() - 1);
	}

	public void waitForTextToBePresentInElement(MobileElement element, String text) {
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	public void waitForElementToBeSelected(MobileElement element) {
		wait.until(ExpectedConditions.elementSelectionStateToBe(element, true));
	}

	public void waitForElementToBeRefreshed(MobileElement element) {
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
	}

	public void waitForInvisibilityOfElementByText(By by, String text) {
		wait.until(ExpectedConditions.invisibilityOfElementWithText(by, text));
	}

	public void waitForElementToBeInVisible(MobileElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitForElementToBeInVisible(MobileElement element, int timeout) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
		webDriverWait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitForElementToBeChecked(MobileElement element) {
		wait.until(elementToBeChecked(element));
	}

	/**
	 * Takes snapshot
	 * 
	 * @param filename(String) filename of the snapshot
	 */
	public void takeScreenShot(String filename) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "/src/test/resources/Screenshots/"
					+ filename + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Get element text
	public String getTexts(MobileElement element) {
		String text = element.getText();
		return text;
	}

	// To verify that the checkBox is selected or not
	public boolean isSelected(String inputElement) {
		MobileElement element = getElement(XPATH, inputElement);
		boolean isSelected = false;
		try {
			if (element != null) {
				isSelected = element.isSelected();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSelected;
	}

	/**
	 * To Get the selected value from the dropdown
	 * 
	 * @param element
	 * @return
	 */
	public String getDropdownValue(String element) {
		String selectedOption = null;
		MobileElement option = driver.findElement(By.xpath(element));
		try {
			if (option != null) {
				selectedOption = new Select(option).getFirstSelectedOption().getText();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectedOption;
	}

	/**
	 * To Delete Cookies before loging into the Application
	 */
	public void deleteCookies() {
		try {
			driver.manage().deleteAllCookies();
		} catch (Exception e) {
			Assert.fail("Not able to delete cookies");
		}
	}

	/**
	 *
	 * @param locator locator of the element(ie.xpath)
	 * @param element element on the page
	 */
	public String gettext(String element) {
		String text = null;
		MobileElement Element = waitForVisibility(getElement(XPATH, element));
		try {
			if (Element != null) {
				text = Element.getText();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}

	/**
	 * To get text based on attribute(EX: id,src, value...) *
	 * 
	 * @param element   locator of the element(ie.xpath)
	 * @param attribute element on the page
	 */
	public String gettext(String element, String attribute) {
		String value = null;
		MobileElement Element = waitForVisibility(getElement(XPATH, element));
		try {
			if (Element != null) {
				value = Element.getAttribute(attribute);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * To get text based on attribute(EX: id,src, value...)
	 * 
	 * @param element   MobileElement of locator
	 * @param attribute element on the page
	 */
	public String gettexts(MobileElement element, String attribute) {
		String value = null;
		try {
			if (element != null) {
				value = element.getAttribute(attribute);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	// To verify element is enabled
	public boolean checkEnabled(String Xpath) {
		MobileElement element = driver.findElement(By.xpath(Xpath));
		boolean isEnabled = false;
		try {
			if (element != null) {
				isEnabled = element.isEnabled();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isEnabled;
	}

	// To verify if element is displayed
	public boolean isDisplayed(String inputLocator) {
		MobileElement element = waitForVisibility(getElement(XPATH, inputLocator));
//		MobileElement element = driver.findElement(By.xpath(Xpath));
		boolean isDisplayed = false;
		try {
			if (element != null) {
				isDisplayed = element.isDisplayed();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDisplayed;
	}

	// To get the text of the lists
	public List<String> getLists(String element) {
		List<String> list = new ArrayList<String>();
		List<MobileElement> lists = driver.findElements(By.xpath(element));
		for (int i = 0; i < lists.size(); i++) {
			String text = lists.get(i).getText();
			list.add(text);
		}
		return list;
	}

	// To get the attribute values
	public List<String> getLists(String element, String value) {
		List<String> list = new ArrayList<String>();
		List<MobileElement> lists = driver.findElements(By.xpath(element));
		for (int i = 0; i < lists.size(); i++) {
			String text = lists.get(i).getAttribute(value);
			list.add(text);
		}
		return list;
	}

	/**
	 * To get all elements from the list
	 * 
	 * @param String xpath
	 */
	public List<MobileElement> getListElements(String element) {

		List<MobileElement> elementList = null;
		try {
			elementList = driver.findElements(By.xpath(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elementList;
	}

	/**
	 * Selects from drop down based on value
	 * 
	 * @param String xpath
	 * @param String value of the text
	 */
	public void selectDropdownValue(String element, String value) {

		boolean flag = false;
		try {
			MobileElement dropDownListBox = getElement(XPATH, element);
			List<MobileElement> lists = dropDownListBox.findElements(By.tagName("option"));
			for (int i = 0; i <= lists.size() - 1; i++) {
				String dropdownValue = lists.get(i).getText().trim();
				if (value.equals(dropdownValue)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				value = "Select";
			}
			if (dropDownListBox != null) {
				Select clickThis = new Select(dropDownListBox);
				clickThis.selectByVisibleText(value);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	/**
	 * To select through index from a dropdown*
	 * 
	 * @param String  xpath
	 * @param Integer index of the element to be selected
	 */
	public void selectOptionFromDropdown(String element, int indexvalue) {
		MobileElement mySelectElm = driver.findElement(By.xpath(element));
		try {
			if (mySelectElm != null) {
				Select mySelect = new Select(mySelectElm);
				mySelect.selectByIndex(indexvalue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To Change the xpath into MobileElement *
	 * 
	 * @param String xpath of the element
	 */
	public MobileElement getElement(String Xpath) {
		MobileElement element = driver.findElement(By.xpath(Xpath));
		return element;

	}

	public MobileElement getElement(String locator, String inputElement) {

		By byElement;
		MobileElement query;
		switch (locator) {
		case "xpath":
			byElement = By.xpath(inputElement);
			break;
		case "id":
			byElement = By.id(inputElement);
			break;
		case "name":
			byElement = By.name(inputElement);
			break;
		case "classname":
			byElement = By.className(inputElement);
			break;
		case "linkname":
			byElement = By.linkText(inputElement);
			break;
		case "paritallinkname":
			byElement = By.partialLinkText(inputElement);
			break;
		case "tagname":
			byElement = By.tagName(inputElement);
			break;
		case "css":
			byElement = By.cssSelector(inputElement);
			break;
		default:
			throw new RuntimeException("Invalid locator");
		}

		try {
			query = waitForVisibility(byElement);
			return query;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return null;
		} catch (ElementNotFoundException e) {
			return query = null;
		}
	}

	/**
	 * To enter the data into a text field*
	 * 
	 * @param element xpath (String) of the element
	 * @param text    String value of data to enter
	 */
	public void populateFields(String element, String text) {
		Object platformName = driver.getCapabilities().getCapability("platformName");
		MobileElement ele = waitForVisibility(getElement(element));

		try {
			if (ele != null) {
//				MobileElement ele = waitForElementToBeClickable(element);
				if (text != null) {
					if (!ele.getText().isEmpty() && ele.getText() != null) {
						ele.clear();
					}
					if (platformName.equals("iOS")) {
						((JavascriptExecutor) driver).executeScript(
								"let input = arguments[0];var setValue = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;setValue.call(input, '"
										+ text
										+ "');var e = new Event('input', { bubbles: true });input.dispatchEvent(e);",
								ele);
					} else if (platformName.equals("Android")) {
						ele.sendKeys(text);
					}
				} else {
					Assert.assertNotNull(ele.getText());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * To tap on the element *
	 * 
	 * @param element xpath (String) of the element
	 */
	public void tapElement(String element) {
		MobileElement ele = waitForVisibility(getElement(element));
		delay(200L);
		try {
			if (ele != null) {
				ele.click();
			}
		} catch (StaleElementReferenceException e) {
			waitForElementToBeClickable(ele).click();
		} catch (ElementClickInterceptedException e) {
			waitForElementToBeClickable(ele).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		delay(200L);

	}

	/**
	 * To tap on the element using JavaScriptExecutor
	 * 
	 * @param element xpath (String) of the element
	 */
	public void tapElementUsingJS(String element) {
		MobileElement elem = waitForVisibility(getElement(XPATH, element));
		MobileElement ele = waitForElementToBeClickable(element);
		delay(200L);
		try {
			if (ele != null) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].click();", ele);
			}
		} catch (StaleElementReferenceException e) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", ele);
		} catch (ElementClickInterceptedException e) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", ele);
		} catch (Exception e) {
			e.printStackTrace();
		}
		delay(200L);
	}

	/**
	 * To tap on the element *
	 * 
	 * @param element xpath (String) of the element
	 */
	public void doubleTapElement(String element) {
		Actions actions = new Actions(driver);
		WebElement elementLocator = getElement(XPATH, element);
		actions.doubleClick(elementLocator).perform();
	}

	public void startRecordingScreen() {
		Object platformName = driver.getCapabilities().getCapability("platformName");

		if (platformName.equals("iOS")) {
			((CanRecordScreen) driver).startRecordingScreen(new IOSStartScreenRecordingOptions()
					.withVideoScale("1280x720").withTimeLimit(Duration.ofSeconds(100)).withVideoType("mpeg4")
					.withVideoQuality(VideoQuality.MEDIUM).enableForcedRestart());

		} else if (platformName.equals("Android")) {
			((CanRecordScreen) driver).startRecordingScreen(new AndroidStartScreenRecordingOptions()
					.withVideoSize("1280x720").withTimeLimit(Duration.ofSeconds(200)));
		}
	}

	// stop video capturing and create *.mp4 file
	public synchronized void stopRecordingScreen(Scenario scenario) throws Exception {

		Object platformName = driver.getCapabilities().getCapability("platformName");
		Object deviceName = ((String) driver.getCapabilities().getCapability("deviceName")).replaceAll(" ", "_");
		delay(3000L);
		String media = ((CanRecordScreen) driver).stopRecordingScreen();

		String dirPath = "videos" + File.separator + platformName + "_" + deviceName + File.separator + getDateTime();

		File videoDir = new File(dirPath);

		synchronized (videoDir) {
			if (!videoDir.exists()) {
				videoDir.mkdirs();
			}
		}

		String[] scenarios = scenario.getId().split("/");
		String scenarioId = scenarios[1].replaceAll("(\\W|^_)*", "");

		byte[] decode = Base64.getDecoder().decode(media);
		FileUtils.writeByteArrayToFile(new File(videoDir + File.separator + scenarioId + ".mp4"), decode);

	}

	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * TO allow the permission pop-up
	 **/
	public boolean allowPermissionPopup() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
		try {
			By allowXpath = By.xpath("//*[@text='Save' or @text='Allow' or @name = 'Allow']");
			MobileElement acceptElement = (MobileElement) webDriverWait
					.until(ExpectedConditions.elementToBeClickable(allowXpath));
			acceptElement.click();
			acceptElement = (MobileElement) webDriverWait.until(ExpectedConditions.elementToBeClickable(allowXpath));
			acceptElement.click();
			return true;
		} catch (TimeoutException e) {
		}
		return false;
	}

	public void scrollToElementUsingJS(String input) {
		MobileElement element = waitForVisibility(getElement(input));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollAndClickElementUsingJS(String input) {
		MobileElement element = waitForVisibility(getElement(input));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void scrollTo(String text) {
		scrollDownTo(text);
	}

	public void scrollDownTo(String text) {
		scrollDownTo(By.xpath("//*[@text=\'" + text + "\' or @name=\'" + text + "\']"));
	}

	public void tapOn(String text) {
		driver.findElement(By.xpath("//*[@text=\'" + text + "\']")).click();
	}

	public void scrollDownToContainsText(String text) {
		scrollDownTo(By.xpath("//*[contains(@text,\'" + text + "\') or contains(@name,\'" + text + "\')]"));
	}

	public void scrollDownTo(String attribute, String text) {

		switch (attribute) {
		case "content-desc":
			scrollDownWithoutFailTo(By.xpath("//*[@content-desc=\'" + text + "\']"));
			break;
		}
	}

	public void scrollDownWithoutFailTo(By byOfElementToBeFound) {
		hideKeyboard();
		int i = 0;
		while (i < 4) {
			if (driver.findElements(byOfElementToBeFound).size() > 0)
				return;

			scrollDown();

			i++;
		}
	}

	public void scrollDownTo(By byOfElementToBeFound) {
		hideKeyboard();
		int i = 0;
		while (i < 12) {
			if (driver.findElements(byOfElementToBeFound).size() > 0)
				return;

			scrollDown();

			i++;
		}
		Assert.fail("Did not find : " + byOfElementToBeFound.toString());
	}

	public void scrollDownTo(MobileElement element) {
		hideKeyboard();
		int i = 0;
		while (i < 12) {
			try {
				if (element.isDisplayed())
					return;
			} catch (Exception e) {

			}

			scrollDown();

			i++;
		}
		Assert.fail("Did not find : " + element.toString());
	}

	// when locating the `element` is optional
	public void scrollDownWithoutFailTo(MobileElement element) {
		hideKeyboard();
		int i = 0;
		while (i < 12) {
			try {
				if (element.isDisplayed())
					return;
			} catch (Exception e) {

			}

			scrollDown();

			i++;
		}
	}

	public void scrollDownTo(List<MobileElement> initSize) {
		hideKeyboard();
		int i = 0;
		int size = initSize.size();
		while (i < 12) {
			if (size < initSize.size()) {
				break;
			}
			scrollDown();
			i++;
		}
	}

	@SuppressWarnings("rawtypes")
	public void scrollDown() {
		int height = driver.manage().window().getSize().getHeight();

		PointOption pointOption = new PointOption();
		pointOption.withCoordinates(5, height * 2 / 3);

		PointOption moveToPointOption = new PointOption();
		moveToPointOption.withCoordinates(5, height / 3);
		WaitOptions waitOptions = new WaitOptions();
		waitOptions.withDuration(Duration.ofMillis(1000));
		new TouchAction(driver).press(pointOption).waitAction(waitOptions).moveTo(moveToPointOption).release()
				.perform();

	}

	public void scrollUp() {

		int height = driver.manage().window().getSize().getHeight();

//        new TouchAction(driver).press(5, height / 3)
//                .waitAction(Duration.ofMillis(1000))
//                .moveTo(5, height * 2 / 3)
//                .release().perform();
	}

	public void swipeLeftToRight() {
		int height = driver.manage().window().getSize().getHeight();
		int width = driver.manage().window().getSize().getWidth();
//        new TouchAction(driver).press(width / 3, height / 2)
//                .waitAction(Duration.ofMillis(1000))
//                .moveTo(width * 2 / 3, height / 2)
//                .release().perform();
	}

	public void swipeRightToLeft() {
		int height = driver.manage().window().getSize().getHeight();
		int width = driver.manage().window().getSize().getWidth();
//        new TouchAction(driver).press(width * 9 / 10, height / 2)
//                .waitAction(Duration.ofMillis(1000))
//                .moveTo(width / 10, height / 2)
//                .release().perform();
	}

	public void scrollUpTo(String text) {
		scrollUpTo(By.xpath("//*[@text=\"" + text + "\"]"));
	}

	public void scrollUpTo(By by) {
		hideKeyboard();
		int i = 0;
		while (i < 5) {
			if (driver.findElements(by).size() > 0)
				return;

			scrollUp();

			i++;
		}
		Assert.fail("Did not find : " + by.toString());
	}

	public void swipeRightToLeftToFindElementAndClick(By byOfElementToSwipeOn, By byOfElementToBeFound) {

		int height = driver.findElement(byOfElementToSwipeOn).getLocation().getY() + 50;
		int width = driver.manage().window().getSize().getWidth();

		System.out.println("Screen width ::" + width);

		int count = 0;
		while (count < 20) {
			if (driver.findElements(byOfElementToBeFound).size() > 0) {
				driver.findElement(byOfElementToBeFound).click();
				return;
			}
//            new TouchAction(driver).press(width * 6 / 7, height)
//                    .waitAction(Duration.ofMillis(1000))
//                    .moveTo(width / 7, height)
//                    .release().perform();
			count++;
		}
		Assert.fail("Could not find element with by - " + byOfElementToBeFound.toString());
	}

	public void swipeRtoLOnElementUsingCount(By by, int count) {

		Point p = driver.findElement(by).getLocation();
		int x_int = ((Integer) p.getX());
		int y_int = ((Integer) p.getY());

		int height = driver.manage().window().getSize().getHeight();
		int width = driver.manage().window().getSize().getWidth();

		boolean flag = true;
		int _count = 0;
		while (flag) {
			try {
				if (_count == count) {
					flag = false;
				} else {
//                    new TouchAction(driver).press(width - x_int, y_int)
//                            .waitAction(Duration.ofMillis(1000))
//                            .moveTo(x_int, y_int)
//                            .release().perform();

					_count++;
					System.out.println("Swipe Count :: " + _count);
				}
			} catch (Exception e) {
//                new TouchAction(driver).press(width - 100, y_int + 100)
//                        .waitAction(Duration.ofMillis(1000))
//                        .moveTo(x_int + 100, y_int + 100)
//                        .release().perform();

				_count++;

			}
		}
	}

	protected void swipeFromTo(MobileElement startElement, MobileElement stopElement) {
//        new TouchAction(driver).press(startElement.getLocation().getX(), startElement.getLocation().getY())
//                .waitAction(Duration.ofMillis(1000))
//                .moveTo(stopElement.getLocation().getX(), stopElement.getLocation().getY())
//                .release().perform();

	}

	/**
	 * Scroll and Click an element
	 * 
	 * @param String xpath
	 */
	public void scrollAndClickMobileElement(String xpathValue) {

		MobileElement mobElement = driver.findElement(MobileBy.xpath(xpathValue));
		try {
			if (mobElement != null) {
				Point point = mobElement.getLocation();
				int startY = point.y;
				int endY = point.y;

				int startX = (int) ((driver.manage().window().getSize().getWidth()) * 0.80);
				driver.manage().window().getSize().getWidth();
				new TouchAction(driver).press(PointOption.point(startX, startY)).waitAction()
						.moveTo(PointOption.point(startX, endY)).release().perform();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void scrollDowntoXPath(String xPath) {
		boolean flag = true;
		int count = 1;
		while (flag) {
			try {
				driver.findElement(By.xpath(xPath));
				flag = false;
				break;
			} catch (Exception NoSuchElementException) {
				count = count + 1;
				Map<String, Object> params = new HashMap<>();
				params.put("start", "40%,90%");
				params.put("end", "40%,20%");
				params.put("duration", "2");
				Object res = driver.executeScript("mobile:touch:swipe", params);
				if (count == 5) {
					break;
				}
			}
		}
	}

	/**
	 * Tap in x and y coordinates
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void TapByCoordinates(int x, int y) {
		TouchAction touchAction = new TouchAction(driver);
		touchAction.tap(PointOption.point(x, y)).perform();
	}

	/**
	 * Swipe screen vertically by anchor start point, end point, and anchor
	 * 
	 * @param driver
	 * @param startPoint in y coordinates
	 * @param endPoint   in y coordinates
	 * @param anchor     in x coordinates
	 */
	public void SwipeVerticalByCoordinates(int startPoint, int endPoint, int anchor) {
		TouchAction touchAction = new TouchAction(driver);
		touchAction.press(PointOption.point(anchor, startPoint))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(anchor, endPoint))
				.release().perform();
	}

	/**
	 * Swipe screen horizontally by anchor start point, end point, and anchor
	 * 
	 * @param driver
	 * @param startPoint in x coordinates
	 * @param endPoint   in y coordinates
	 * @param anchor     in y coordinates
	 */
	public static void SwipeHorizontalByCoordinates(AppiumDriver driver, int startPoint, int endPoint, int anchor) {
		TouchAction touchAction = new TouchAction(driver);
		touchAction.press(PointOption.point(startPoint, anchor))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endPoint, anchor))
				.release().perform();
	}

	/**
	 * Swipe screen horizontally by x and y start point to x and y end point.
	 * 
	 * @param driver
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	public static void SwipeByCoordinates(AppiumDriver driver, int startX, int startY, int endX, int endY) {
		TouchAction touchAction = new TouchAction(driver);
		touchAction.press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
				.moveTo(PointOption.point(endX, endY)).release().perform();
	}

	public String getTagName(String inputElement) {
		String tagname = "";
		MobileElement ele = waitForVisibility(getElement(XPATH, inputElement));
		if (ele != null) {
			tagname = getElement(XPATH, inputElement).getTagName();
			if (tagname.equals("input")) {
				tagname = getElement(XPATH, inputElement).getAttribute("type");
			}
		} else {
			tagname = "NotFound";
		}
		return tagname;
	}

	/**
	 * Collect validation messages from error message fields
	 * 
	 * @param fields      array of error message fields
	 * @param locatorType locator for error message fields
	 */
	public List<String> getValidationMessages(String[] fields) {
		List<String> validationMessages = new ArrayList<String>();
		if (!validationMessages.isEmpty()) {
			validationMessages.clear();
		}
		for (String errMessageFields : fields) {
			if (getListElements(errMessageFields).size() > 0) {
				if (getElement(XPATH, errMessageFields) != null) {
					List<MobileElement> validationMsgs = driver.findElements(By.xpath(errMessageFields));
					for (int i = 0; i <= validationMsgs.size() - 1; i++) {
						if (validationMsgs.get(i).getText() != null && validationMsgs.get(i).getText() != "") {
							validationMessages.add(validationMsgs.get(i).getText());
						}
					}
				}
			}
		}
		return validationMessages;
	}

	public void pressENTER(String inputElement) {
		// TODO Auto-generated method stub
		driver.findElement(By.xpath(inputElement)).sendKeys(Keys.ENTER);
	}

	public void pressTAB(String inputElement) {
		// TODO Auto-generated method stub
		driver.findElement(By.xpath(inputElement)).sendKeys(Keys.TAB);
	}

}
