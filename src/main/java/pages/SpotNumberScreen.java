package pages;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

import base.TestBase;
import utilities.CommonUtility;
import utilities.WebForm;

public class SpotNumberScreen extends TestBase {

	TestBase base = new TestBase();

	CommonUtility commonUtility = new CommonUtility();

	WebForm webForm = new WebForm();

	// Spot No Screen Elements

	final String spotNumber = "//input[@name='spotNumber']";

	final String submitNextButton = "//button[@type='submit']";

	final String spotNoScreenWelcomeTextElement = "//label[@class='fs-20 fw-600 mb-2']";

	final String checkedInPlace_SpotNoScreen = "//div[@class='fs-20 fw-600 mb-1 text-center']";
	
	final String selectedTab = "//div[@class='text-primary od-text-primary tab-icon']";
	
	final String checkedInPlaceElement = "//div[@class=\"fs-20 fw-600 mb-1 text-center\"]";

	final String expectedSpotNumberMessage = "What's your spot number?";
	
	final String expected_SelectedTab = "Order";

	public String[] fields = { spotNumber };

	String expectedMessageKeys = "";

	// the error fields.
	final String FORM_ERROR = ".//*[@class='error']";

	final String[] ERROR_MESSAGE_FIELDS = { FORM_ERROR };

	// error message map (Key-Value Pair)
	HashMap<String, String> spotNoScreenErrorMessageMap = new HashMap<String, String>();

	// Verify the message displayed on Spot Number screen
	public void verifySpotNumberScreen() {

		if (base.getElement(XPATH, spotNoScreenWelcomeTextElement) != null) {
			String actualSignInMessage = base.gettext(spotNoScreenWelcomeTextElement);
			Assert.assertEquals(actualSignInMessage.trim(), expectedSpotNumberMessage.trim());
		} else {
			Assert.assertFalse(base.isDisplayed(spotNoScreenWelcomeTextElement),
					"Sign-in alert message is not being displayed on check-in screen");
		}
	}

	public void populateSpotNoScreenFields(String[][] formData) {
		
		// TO get the error/success message key from excel sheet
		expectedMessageKeys = commonUtility.flatten(formData)[commonUtility.flatten(formData).length - 1];
		
		// Store the check-in place
		commonUtility.checkInPlace = base.gettext(checkedInPlaceElement);
		// To check if the size of formdata and fields are same
		boolean outcome = webForm.checkFormFieldsData(formData, fields);
		Assert.assertTrue(webForm.checkFormFieldsData(formData, fields));
		// To enter the data
		if (outcome) {
			webForm.enterData(formData, fields);
		}
	}

	// Verify if 'Order' bottom tab is selected
	public void verifySelectedOrderTab() {
//		base.delay(3000L);
		String actual_SelectedTab = base.gettext(selectedTab);
		Assert.assertEquals(actual_SelectedTab.trim(), expected_SelectedTab.trim());
	}

	// Tap on Next Arrow button
	public void tapNextArrowButton() {

		if (base.getElement(XPATH, submitNextButton) != null) {
			if (base.isDisplayed(submitNextButton)) {
				base.tapElementUsingJS(submitNextButton);
				base.delay(1000L);
			} else {
				Assert.assertTrue(base.isDisplayed(submitNextButton), "Next arrow button is not clicked");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, submitNextButton),
					"Next Arrow button is not present on the Spot Number screen");
		}

	}

	// Verify the error messages displayed on the screen
	public void verifyScreenErrorMessages() {

		spotNoScreenErrorMessageMap.put("empty_SpotNumber", "Please enter your spot number.");
		spotNoScreenErrorMessageMap.put("invalid_SpotNumber", "Please enter a valid spot number.");

		// List of all error message displayed in the screen
		List<String> actualValidationMsg = base.getValidationMessages(ERROR_MESSAGE_FIELDS);

		// List of error-keys of all error messages displayed
		List<String> actualValidationMsgKeys = webForm.getActualErrorMessageKeys(actualValidationMsg,
				spotNoScreenErrorMessageMap);

		// Comparing expected error message keys from excel to actual error keys of
		// displayed error messages
		webForm.compareMessageKeys(expectedMessageKeys, actualValidationMsgKeys);
	}

}
