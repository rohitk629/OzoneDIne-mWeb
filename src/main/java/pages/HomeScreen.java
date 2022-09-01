package pages;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;

public class HomeScreen extends TestBase {

	TestBase base = new TestBase();

	final String clickToPay = "//div[text()='CLICK TO PAY']";

	final String refillTab = "//div[text()='Refill']";

	final String callServer = "//div[text()='Call Server']";

	final String viewCheck = "//div[text()='View Check']";

	final String payBalance = "//button[contains(text(),'PAY REMAINING BALANCE') or contains(text(),'Full Check')]";

	final String splitEvenly = "//button[contains(text(),'SPlit Evenly')]";

	final String continueButton = "//button[contains(text(),'CONTINUE')]";

	final String splitByMoreButton = "//button//span[contains(text(),'+')]";

	final String splitByLessButton = "//button//span[contains(text(),'-')]";
	
	
	// Tap on 'Click to Pay' button
	public void tapOnClickToPay() {

		if (base.getElement(XPATH, clickToPay) != null) {
			if (base.isDisplayed(clickToPay)) {
				base.tapElementUsingJS(clickToPay);
			} else {
				Assert.assertFalse(base.isDisplayed(clickToPay), "Not able to click " + clickToPay + " to Bag button");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, clickToPay), "Click to Pay button is not available");
		}
	}

	// Tap on 'Full Check' or 'Pay Remaining Balance' or 'Split Evenly' button
	public void tapPayBalance() {

		if (base.getElement(XPATH, payBalance) != null) {
			if (base.isDisplayed(payBalance)) {
				base.tapElementUsingJS(payBalance);
			} else {
				Assert.assertFalse(base.isDisplayed(payBalance), "Not able to click " + payBalance + " to Bag button");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, payBalance), "The button is not available");
		}
	}

	// Tap on 'Continue' button
	public void tapOnContinueButton() {

		if (base.getElement(XPATH, continueButton) != null) {
			if (base.isDisplayed(continueButton)) {
				base.tapElementUsingJS(continueButton);
				base.delay(1500L);
			} else {
				Assert.assertFalse(base.isDisplayed(continueButton),
						"Not able to click " + continueButton + " to Bag button");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, continueButton), "Continue button is not available");
		}
	}

	public void tapSplitEvenlyButton() {
		// TODO Auto-generated method stub

		if (base.getElement(XPATH, splitEvenly) != null) {
			if (base.isDisplayed(splitEvenly)) {
				base.tapElementUsingJS(splitEvenly);
				base.delay(1500L);
			} else {
				Assert.assertFalse(base.isDisplayed(splitEvenly), "Not able to click " + splitEvenly + " to Bag button");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, splitEvenly), "The button is not available");
		}
	}

	public void verifySplitEvenlyScreen() {
		// TODO Auto-generated method stub
		base.waitForVisibility(base.getElement(XPATH, splitByMoreButton));
		String currentUrl = base.getCurrentURL();
		Assert.assertTrue(currentUrl.toLowerCase().contains("split-evenly"), "Does not navigate to Split Even Screen");
	}

	public void splitCheckBy(Integer num) {
		// TODO Auto-generated method stub
		int noOfTimesToClick = num -2;
		System.out.println("No of times to be clicked "+noOfTimesToClick);
		for(int i=0; i<=noOfTimesToClick-1; i++) {
			base.tapElementUsingJS(splitByMoreButton);
			base.delay(1500L);
		}

	}

}
