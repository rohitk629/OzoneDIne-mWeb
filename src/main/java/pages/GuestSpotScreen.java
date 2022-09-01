package pages;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

import base.TestBase;
import utilities.CommonUtility;
import utilities.WebForm;

public class GuestSpotScreen extends TestBase {

	TestBase base = new TestBase();

	CommonUtility commonUtility = new CommonUtility();

	WebForm webForm = new WebForm();

	// Pickup Screen Elements
	final String name_Field = "//input[@name='guestName']";

	final String nextButton = "//button[@type='submit'][contains(text(), 'Next')]";

	public String[] pickUp_Fields = { name_Field };

	// the error fields.
	final String FORM_ERROR = ".//*[contains(@class, 'error')]";

	final String[] ERROR_MESSAGE_FIELDS = { FORM_ERROR };

	// error message map (Key-Value Pair)
	HashMap<String, String> guestSpotScreenErrorMessageMap = new HashMap<String, String>();

	String expectedMessageKeys = "";

	// Verify if the current screen is Pickup screen
	public void verifyGuestSpotScreen() {
		// TODO Auto-generated method stub
		base.waitForVisibility(base.getElement(XPATH, name_Field));
		String currentUrl = base.getCurrentURL();
		Assert.assertTrue(currentUrl.toLowerCase().contains("guest-spot"), "Does not navigate to Guest Spot screen");
	}

	// Populate the data in to the pickup screen fields
	public void populateGuestSpotScreenFields(String guestName) {
		// TODO Auto-generated method stub
		base.populateFields(name_Field, guestName);
	}

	// Tap on 'Next' button with all the mandatory fields filled
	public void submitNextButton() {

		if (base.getElement(XPATH, nextButton) != null) {
			if (base.isDisplayed(nextButton)) {
				base.tapElementUsingJS(nextButton);
			} else {
				Assert.fail(base.isDisplayed(nextButton) + " - Next button is not displayed");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, nextButton), " - Next button is not present on the Pickup screen");
		}
	}

	// Verify the error or success message once next button is tapped
	public void verifyScreenErrorMessage() {

		guestSpotScreenErrorMessageMap.put("empty_GuestName", "Name field is required");
		guestSpotScreenErrorMessageMap.put("pickUpTime_Req", "Select Time field is required");

		// List of all error message displayed in the screen
		List<String> actualValidationMsg = base.getValidationMessages(ERROR_MESSAGE_FIELDS);

		// List of error-keys of all error messages displayed
		List<String> actualValidationMsgKeys = webForm.getActualErrorMessageKeys(actualValidationMsg,
				guestSpotScreenErrorMessageMap);

		// Comparing expected error message keys from excel to actual error keys of
		// displayed error messages
		webForm.compareMessageKeys(expectedMessageKeys, actualValidationMsgKeys);
	}

}
