package pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;
import utilities.CommonUtility;
import utilities.WebForm;

public class PickupScreen extends TestBase {

	TestBase base = new TestBase();

	CommonUtility commonUtility = new CommonUtility();

	WebForm webForm = new WebForm();

	// Pickup Screen Elements
	final String name_Field = "//input[@name='guestName']";

	final String deliveryTab = "//div[contains(text(), 'Delivery')]";

	final String pickUpDate_Field = "//input[@name='selectedDate']";
	
	final String deliveryDate_Field = "//input[@name='deliverydDate']";

	final String pickUpTime_Field = "//input[@id='react-select-2-input']";
	
	final String deliveryTime_Field = "//input[@id='react-select-3-input']";
	
	final String address_Field = "//input[@name='address']";
	
	final String city_Field = "//input[@name='city']";
	
	final String state_Field = "//input[@name='state']";
	
	final String zip_Field = "//input[@name='zip']";

	final String nextButton = "//button[@type='submit'][contains(text(), 'Next')]";

	public String[] pickUp_Fields = { name_Field, pickUpDate_Field, pickUpTime_Field };
	
	public String[] delivery_Fields = { name_Field, deliveryDate_Field, deliveryTime_Field, address_Field,  city_Field, state_Field, zip_Field};

	// the error fields.
	final String FORM_ERROR = ".//*[contains(@class, 'error')]";

	final String[] ERROR_MESSAGE_FIELDS = { FORM_ERROR };

	// error message map (Key-Value Pair)
	HashMap<String, String> pickUpScreenErrorMessageMap = new HashMap<String, String>();

	String expectedMessageKeys = "";

	// Verify if the current screen is Pickup screen
	public void verifyPickUpScreen() {
		// TODO Auto-generated method stub
		base.waitForVisibility(base.getElement(XPATH, name_Field));
		String currentUrl = base.getCurrentURL();
		Assert.assertTrue(currentUrl.toLowerCase().contains("pickup-time"), "Does not navigate to Pickup screen");
	}

	// Populate the data in to the pickup screen fields
	public void populatePickUpScreenFields(String guestName, String pickUpDate, String pickUpTime) {
		// TODO Auto-generated method stub
		base.populateFields(name_Field, guestName);
		base.delay(500L);

		if (pickUpDate.equalsIgnoreCase("Today")) {
			base.populateFields(pickUpDate_Field, pickUpDate);
		} else if (pickUpDate.equalsIgnoreCase("Tomorrow")) {
			pickUpDate = getPickUpDate(1);
		}
		base.delay(500L);
		base.populateFields(pickUpDate_Field, pickUpDate);
		base.pressENTER(pickUpDate_Field);

		base.populateFields(pickUpTime_Field, pickUpTime);
		base.pressTAB(pickUpTime_Field);
	}
	
	// Populate the data in to the pickup screen fields
	public void populateDeliveryScreenFields(String guestName, String deliveryDate, String deliveryTime, String address, String city, String state, String zipCode) {
		// TODO Auto-generated method stub
		base.populateFields(name_Field, guestName);
		base.delay(500L);
		if (deliveryDate.equalsIgnoreCase("Today")) {
			deliveryDate = getPickUpDate(0);
		} else if (deliveryDate.equalsIgnoreCase("Tomorrow")) {
			deliveryDate = getPickUpDate(1);
		}
		base.delay(500L);
		base.populateFields(deliveryDate_Field, deliveryDate);
		base.pressENTER(deliveryDate_Field);

		base.populateFields(deliveryTime_Field, deliveryTime);
		base.pressTAB(deliveryTime_Field);
		
		base.populateFields(address_Field, address);
		base.populateFields(city_Field, city);
		base.populateFields(state_Field, state);
		base.populateFields(zip_Field, zipCode);
	}

	public String getPickUpDate(int n) {
		// get a calendar instance, which defaults to "now"
		Calendar calendar = Calendar.getInstance();

		// add days to the date/calendar
		calendar.add(Calendar.DAY_OF_YEAR, n);

		// now get the required date
		Date date = calendar.getTime();

		DateFormat dateFormat = new SimpleDateFormat("MMM dd, YYYY");
		String dateToSelect = dateFormat.format(date);
		System.out.println("Date To Select: " + dateToSelect);
		return dateToSelect;
	}

	// Tap on 'Next' button with all the mandatory fields filled
	public void submitNextButton() {

		if (base.getElement(XPATH, nextButton) != null) {
			if (base.isDisplayed(nextButton)) {
				base.tapElementUsingJS(nextButton);
				wait = new WebDriverWait(this.driver, 5);
			} else {
				Assert.fail(base.isDisplayed(nextButton) + " - Next button is not displayed");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, nextButton), " - Next button is not present on the Pickup screen");
		}
	}

	// Verify the error or success message once next button is tapped
	public void verifyScreenErrorMessage() {

		pickUpScreenErrorMessageMap.put("empty_GuestName", "Name field is required");
		pickUpScreenErrorMessageMap.put("pickUpTime_Req", "Select Time field is required");
		pickUpScreenErrorMessageMap.put("address_Req", "Address field is required");
		pickUpScreenErrorMessageMap.put("city_Req", "City field is required");
		pickUpScreenErrorMessageMap.put("state_Req", "State field is required");
		pickUpScreenErrorMessageMap.put("zip_Req", "Zip field is required");

		// List of all error message displayed in the screen
		List<String> actualValidationMsg = base.getValidationMessages(ERROR_MESSAGE_FIELDS);

		// List of error-keys of all error messages displayed
		List<String> actualValidationMsgKeys = webForm.getActualErrorMessageKeys(actualValidationMsg,
				pickUpScreenErrorMessageMap);

		// Comparing expected error message keys from excel to actual error keys of
		// displayed error messages
		webForm.compareMessageKeys(expectedMessageKeys, actualValidationMsgKeys);
	}

	// Tap on delivery tab
	public void tapDeliveryTab() {
		// TODO Auto-generated method stub
		if (base.getElement(XPATH, deliveryTab) != null) {
			if (base.isDisplayed(deliveryTab)) {
				base.tapElementUsingJS(deliveryTab);
				wait = new WebDriverWait(this.driver, 5);
//				base.allowPermissionPopup();
			} else {
				Assert.fail(base.isDisplayed(deliveryTab) + " - Delivery Tab is not displayed");
			}
		} else {
			Assert.assertNull(base.getElement(XPATH, deliveryTab), " - Delivery tab element is not present on the Pickup screen");
		}
	}

}
