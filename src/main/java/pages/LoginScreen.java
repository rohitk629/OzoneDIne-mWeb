package pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;
import utilities.CommonUtility;
import utilities.WebForm;

public class LoginScreen extends TestBase {

	TestBase base = new TestBase();

	CommonUtility commonUtility = new CommonUtility();

	WebForm webForm = new WebForm();

	// Check-in Screen Elements
	final String welcomeMessageCheckInPageElement = "//div[@class='fs-20 fw-700 pb-10']";

	final String cancelCheckIn = "//div[@class='row mx-0 justify-content-center align-items-center']";

	final String checkInPlace_CheckInScreen = "//div[@class='fs-18 fw-600 my-1 text-center']";

	final String signInWarningMessage = "//div[@class='col-10 text-left pb-2 pt-2 fs-16 sensorCheckin_warningMessageColor__1HzBU']";

	final String signInButton_CheckInScreen = "//button[@type='button'][contains(@class, 'btn btn-primary')]";

	// Login Screen Elements
	final String headerTextLoginScreenElement = "//div[@class='text-center fs-20 fw-700 p-10 pb-15']";

	final String checkedInPlace_LoginScreenElement = "//div[@class='fs-14 fw-600 signUp_colorWhite__27Dfm']";

	final String signInWithPassword = "//div[contains(text(), 'Sign in with a password')]";

	final String userName = "//input[@name='username']";

	final String password = "//input[@name='password']";

	final String signInButton_LoginScreen = "//*[@class='btn btn-primary btn-block btn-md'][@type='submit']";

	public String[] fields = { userName, password };


	// the error fields.
	final String FORM_ERROR = ".//*[@class='error']";

	final String[] ERROR_MESSAGE_FIELDS = { FORM_ERROR };

	// Welcome or Success Messages

	final String expectedWelcomeMessage_CheckInPage = "You checked in at:";

	final String signInWarningMessage_CheckInPage = "You'll need to sign in before you can order.";
	
	final String expectedHeaderTextOnLoginScreen = "Sign in with your phone number:";
	

	String checkInPlace = "";

	String expectedMessageKeys = "";

	// error message map (Key-Value Pair)
	HashMap<String, String> loginScreenErrorMessageMap = new HashMap<String, String>();

	// Verify the messages displayed on the Check-in screen
	public void verifyCheckInMessages() {

		if (base.getElement(XPATH, welcomeMessageCheckInPageElement) != null) {
			if (base.getElement(XPATH, signInWarningMessage) != null) {
				String actualSignInMessage = base.gettext(signInWarningMessage);
				Assert.assertEquals(actualSignInMessage, signInWarningMessage_CheckInPage);
			} else {
				Assert.assertFalse(base.isDisplayed(signInWarningMessage),
						"Sign-in alert message is not being displayed on check-in screen");
			}
			String actualcheckInMessage = base.gettext(welcomeMessageCheckInPageElement);
			Assert.assertEquals(actualcheckInMessage, expectedWelcomeMessage_CheckInPage);
		} else {
			Assert.assertNull(base.getElement(XPATH, welcomeMessageCheckInPageElement),
					"Check-in message is not being displayed on Check-In screen");
		}

	}

	// Tap on cancel check-in link
	public void tapCancelCheckInLink() {

		if (base.getElement(XPATH, cancelCheckIn) != null) {
			if (base.isDisplayed(cancelCheckIn)) {
				base.tapElement(cancelCheckIn);
				wait = new WebDriverWait(this.driver, 5);
			} else {
				Assert.assertFalse(base.isDisplayed(cancelCheckIn), "Cancel Check-in link is not clicked");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, cancelCheckIn),
					"Cancel Check-in link is not present on the Check-in screen");
		}
	}

	// Tap on 'Sign In' button
	public void tapSignInButton() {
		// Save the check-in place text shown on check-in screen
		checkInPlace = base.gettext(checkInPlace_CheckInScreen);
		// Store the check-in place
		commonUtility.checkInPlace = base.gettext(checkInPlace_CheckInScreen);
		// Tap on Sign In button
		if (base.getElement(XPATH, signInButton_CheckInScreen) != null) {
			if (base.isDisplayed(signInButton_CheckInScreen)) {
				base.tapElement(signInButton_CheckInScreen);
				wait = new WebDriverWait(this.driver, 5);
			} else {
				Assert.assertFalse(base.isDisplayed(signInButton_CheckInScreen), "SIGN IN button is not clicked");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, signInButton_CheckInScreen),
					"SIGN IN button is not present on the landing screen");
		}
	}

	// Verify that the checked-in place displayed on Login screen is the same to
	// that of check-in screen
	public void verifyCheckedInPlace() {
		String checkedInPlace_LoginScreen = base.gettext(checkedInPlace_LoginScreenElement);
		Assert.assertEquals(checkedInPlace_LoginScreen.trim(), checkInPlace.trim());
	}

	// To enter the excel data into email and password fields
	public void populateLoginScreenFields(String[][] formData) {

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

	// Verify the error or success message once next button is tapped
	public void verifyScreenErrorMessage() {

		loginScreenErrorMessageMap.put("empty_UserName", "Please enter your username.");
		loginScreenErrorMessageMap.put("empty_Password", "Please enter your password.");
		loginScreenErrorMessageMap.put("invalid_Password", "Login failed - please check input and try again");
		loginScreenErrorMessageMap.put("password_length", "password must be at least 8 characters");

		// List of all error message displayed in the screen
		List<String> actualValidationMsg = base.getValidationMessages(ERROR_MESSAGE_FIELDS);

		// List of error-keys of all error messages displayed
		List<String> actualValidationMsgKeys = webForm.getActualErrorMessageKeys(actualValidationMsg,
				loginScreenErrorMessageMap);


		// Comparing expected error message keys from excel to actual error keys of displayed error messages
		webForm.compareMessageKeys(expectedMessageKeys, actualValidationMsgKeys);
	}

	// Verify the user is navigated to login screen by comparing the header text
	public void navigateToSignInScreen() {
		String actualHeaderTextOnLoginScreen = base.gettext(headerTextLoginScreenElement);
		Assert.assertEquals(actualHeaderTextOnLoginScreen.trim(), expectedHeaderTextOnLoginScreen.trim());
	}

	// Tap on the link 'Sign in with password'
	public void tapSignInWithPasswordLink() {

		if (base.getElement(XPATH, signInWithPassword) != null) {
			if (base.isDisplayed(signInWithPassword)) {
				base.tapElement(signInWithPassword);
			} else {
				Assert.assertTrue(base.isDisplayed(signInWithPassword), "Link 'Sign in with password' is not clicked");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, signInWithPassword),
					"Link 'Sign in with password' is not present on the landing screen");
		}

	}


	public void tapSignInWButton_LoginScreen() {


		if (base.getElement(XPATH, signInButton_LoginScreen) != null) {
			if (base.isDisplayed(signInButton_LoginScreen)) {
				base.tapElement(signInButton_LoginScreen);
			} else {
				Assert.assertFalse(base.isDisplayed(signInButton_LoginScreen), "SIGN IN button is not clicked");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, signInButton_LoginScreen),
					"SIGN IN button is not present in the Login screen");
		}
	}

}
