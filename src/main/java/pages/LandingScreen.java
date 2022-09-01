package pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;
import utilities.CommonUtility;
import utilities.WebForm;

public class LandingScreen extends TestBase {

	TestBase base = new TestBase();

	CommonUtility commonUtility = new CommonUtility();

	WebForm webForm = new WebForm();

	// Landing Screen Elements
	final String welcomeMessageElement = "//div[@class='fs-20 fw-700 pb-10']";

	final String scanQRCode = "//img[@alt='QR Code']";

	final String spotCode = "//input[@name='spotCode']";

	final String submitNextButton = "//button[@type='submit']";

	final String signInButton = "//button[@class='btn btn-primary w-100']";

	public String[] fields = { spotCode };

	// the error fields.
	final String FORM_ERROR = ".//*[@class='error']";

	final String[] ERROR_MESSAGE_FIELDS = { FORM_ERROR };

	// Welcome or Success Messages
	final String expectedWelcomeMessage = "Order & pay from your phone!";
//	final String expectedWelcomeMessage = "Order & Pay from Your Phone!";   //Change in Text

	String expectedMessageKeys = "";

	// error message map (Key-Value Pair)
	HashMap<String, String> landingScreenErrorMessageMap = new HashMap<String, String>();

	// Verify the welcome message when the app is launched on mobile browser
	public void verifyWelcomeMessage() {

		if (base.getElement(XPATH, welcomeMessageElement) != null) {
			if (base.isDisplayed(welcomeMessageElement)) {
				String actualWelcomeMessage = base.gettext(welcomeMessageElement);
				Assert.assertEquals(actualWelcomeMessage, expectedWelcomeMessage);
			} else {
				Assert.assertFalse(base.isDisplayed(welcomeMessageElement), "Welcome Message is not being displayed");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, welcomeMessageElement),
					"Welcome Message Element is not displayed");
		}
	}

	// Verify if QR Scanner is displayed
	public void verifyQRScannerDisplayed() {

		if (base.getElement(XPATH, scanQRCode) != null) {
			if (base.isDisplayed(scanQRCode)) {
				Assert.assertTrue(base.isDisplayed(scanQRCode), "QR Scan Code button is displayed");
			} else {
				Assert.fail("QR Scan Code is not displayed");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, scanQRCode),
					"QR Scan Code button is not present on the landing screen");
		}
	}

	// To enter the excel data into email and password fields
	public void populateLandingScreenFields(String[][] formData) {

		// TO get the error/success message key from excel sheet
		expectedMessageKeys = commonUtility.flatten(formData)[commonUtility.flatten(formData).length - 1];
		// To check if the size of formdata and fields are same
		boolean outcome = webForm.checkFormFieldsData(formData, fields);
		Assert.assertTrue(webForm.checkFormFieldsData(formData, fields));
		// To enter the data
		if (outcome) {
			webForm.enterData(formData, fields);
		}

	}

	// Verify if 'Sign In' button is displayed
	public void isSignInButtonDisplayed() {

		if (base.getElement(XPATH, signInButton) != null) {
			if (base.isDisplayed(signInButton)) {
				Assert.assertTrue(base.isDisplayed(signInButton), "SIGN IN button is displayed");
			} else {
				Assert.fail(base.isDisplayed(signInButton)+" - SIGN IN button is not clicked");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, signInButton),
					"SIGN IN button is not present on the landing screen");
		}
	}

	// Tap on Next arrow mark button after entering the data
	public void tapNextButton() {

		if (base.getElement(XPATH, submitNextButton) != null) {
			if (base.isDisplayed(submitNextButton)) {
				base.tapElementUsingJS(submitNextButton);
				wait = new WebDriverWait(driver, 5);
			} else {
				Assert.fail(base.isDisplayed(submitNextButton)+" - Next (Arrow Mark) button is not clicked");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, submitNextButton),
					"Next (Arrow Mark) button is not present on the landing screen");
		}
	}

	// Verify the error or success message once next button is tapped
	public void verifyScreenErrorMessage() {

		landingScreenErrorMessageMap.put("empty_Code", "Spot Code is Required");
		landingScreenErrorMessageMap.put("minDigit_Error", "Spot code should be 7-digit");

		// List of all error message displayed in the screen
		List<String> actualValidationMsg = base.getValidationMessages(ERROR_MESSAGE_FIELDS);

		// List of error-keys of all error messages displayed
		List<String> actualValidationMsgKeys = webForm.getActualErrorMessageKeys(actualValidationMsg,
				landingScreenErrorMessageMap);

		// Comparing expected error message keys from excel to actual error keys of
		// displayed error messages
		webForm.compareMessageKeys(expectedMessageKeys, actualValidationMsgKeys);
	}

	// Verify the screen is Landing Page Screen
	public void verifyLandingScreen() {

		verifyWelcomeMessage();
		verifyQRScannerDisplayed();
	}

	public void enter7digitCode(String spot_Code) {
		// TODO Auto-generated method stub
		String tagName = base.getTagName(spotCode);
		webForm.inputData(spotCode, tagName, spot_Code);	
	}

}
