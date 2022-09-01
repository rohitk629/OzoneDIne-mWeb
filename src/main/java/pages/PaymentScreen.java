package pages;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.TestBase;
import io.appium.java_client.MobileElement;
import utilities.CommonUtility;
import utilities.WebForm;

public class PaymentScreen extends TestBase {

	TestBase base = new TestBase();

	CommonUtility commonUtility = new CommonUtility();

	WebForm webForm = new WebForm();

	final String cardNumber = "//input[@placeholder='Card number']";

	final String fullName = "//input[@id='tsep-fullName']";

	final String expiryDate = "//input[@placeholder='MM/YY']";

	final String CVV = "//div[contains(text(),'CVV')]/input";

	final String zipCode = "//input[@id='tsep-zip']";

	public String[] fields = { cardNumber, fullName, expiryDate, CVV, zipCode };

	final String receipt = "//button[@type='button'][contains(text(), 'Receipt')]";

	final String confirmButton = "//button[@type='button'][contains(text(),'Confirm')]";

	final String pay_NowButton = "//button[@class='btn btn-primary od-btn-primary w-100']";

	final String pay_At_Restaurant = "//button[@class='selectedCart_lineHeight__1cko_ btn btn-secondary od-btn-secondary w-100']";

	final String payLater = "//button[contains(text(), 'pay later')]";

	final String confirmation_CheckInPlace = "//div[@class='fs-18 fw-600 my-2 text-center' or @class='row m-0 justify-content-center pt-15']";

	final String orderNow_Button = "//button[contains(text(), 'Order Now')]";

	// Coupon Details Element

	final String expandCouponField = "//*[@data-icon='caret-down']";

	final String couponCode_Field = "//div[@class='col-12']//input[@class='form-control']";

	final String applyCoupon_Button = "//button[contains(text(),'Apply Coupon')]";

	// Add Card Elements for USAE-Pay

	final String card_Frame = "//iframe[@id='paymentCardIFrame']";

	final String add_CardNumber = "//input[@id='payjs-cnum']";

	final String add_ExpiryDate = "//input[@id='payjs-exp']";

	final String add_CVV = "//input[@id='payjs-cvv']";

	final String fullName_USAE = "//input[contains(@id,'fullName')]";

	final String add_ZipCode = "//input[@id='zip-code']";

	final String addCardButton = "//button[@type='submit'][contains(text(),'Add Card')]";

	// Add Card Elements for Shift4 Pay

	final String shift4_Frame = "//iframe[@id='i4goTT_fe9934a9599f4f6ea07174422857b41d']";

	final String shift4_Card_Type = "//select[@id='i4go_cardType']";

	final String shift4_CardNumber = "//input[@id='i4go_cardNumber']";

	final String fullName_Shift4Pay = "//input[contains(@id,'fullName')]";

	final String shift4_ExpirationMonth = "//select[@id='i4go_expirationMonth']";

	final String shift4_ExpirationYear = "//select[@id='i4go_expirationYear']";

	final String shift4_CVV = "//input[@id='i4go_cvv2Code']";

	final String shift4_ZipCode = "//input[@id='i4go_postalCode']";

	final String add_shift4PayCard = "//input[@id='i4go_submit']";

	// Tip Details Elements

	final String noTipButton = "//div[contains(text(),'No Tip')]";

	String tipAmount = "//div[contains(@class,'tips')]/div[contains(text(),'temp')]";
	
	final String scrollToSection = "//div[contains(text(), 'temp')]";

	// the error fields.
	final String FORM_ERROR = ".//*[@class='error']";

	final String TOAST_ERROR = "//div[@role='alert']";

//	final String TOAST_ERROR = "//div[@class='notification_notificationBar__3SXBY notification_notificationOpen__OwCns notification_alertDanger__Fa5MG']";

	final String[] ERROR_MESSAGE_FIELDS = { FORM_ERROR, TOAST_ERROR };

	String expectedMessageKeys = "";

	// error message map (Key-Value Pair)
	HashMap<String, String> paymentScreenErrorMessageMap = new HashMap<String, String>();

	public void verifyPaymentScreen() {
		// TODO Auto-generated method stub
		base.waitForVisibility(base.getElement(XPATH, receipt));
		String currentUrl = base.getCurrentURL();
		Assert.assertTrue(currentUrl.toLowerCase().contains("tip"), "Does not navigate to Payment screen");
	}

	// To enter the excel data into Payment screen fields
	public void populatePaymentScreenFields(String[][] formData) {

		// TO get the error/success message key from excel sheet
		expectedMessageKeys = commonUtility.flatten(formData)[commonUtility.flatten(formData).length - 1];
		// To check if the size of formdata and fields are same
		boolean outcome = webForm.checkFormFieldsData(formData, fields);
		Assert.assertTrue(webForm.checkFormFieldsData(formData, fields));

		if (outcome) {
			webForm.enterData(formData, fields);
		}
	}

	public void tapPayNowButton() {

		base.waitForElementToBeClickable(pay_NowButton);
		if (base.getElement(XPATH, pay_NowButton) != null) {
			if (base.isDisplayed(pay_NowButton)) {
				base.tapElementUsingJS(pay_NowButton);
				base.delay(1500L);
				if (base.gettext(pay_NowButton).equalsIgnoreCase("Validating")) {
					System.out.println("Text on the button ::: " + base.gettext(pay_NowButton));
					base.tapElement(pay_NowButton);
				}
			} else {
				Assert.assertFalse(base.isDisplayed(pay_NowButton), "Not able to tap on 'Pay Now' button");
			}
		} else {
			Assert.fail("Pay Now button is not displayed" + base.getElement(XPATH, pay_NowButton));
		}
	}

	public void tapPayAtRestaurantButton() {

		if (base.getElement(XPATH, payLater) != null) {
			if (base.isDisplayed(payLater)) {
				base.tapElementUsingJS(payLater);
			} else {
				Assert.assertFalse(base.isDisplayed(payLater), "Not able to tap on 'Pay at the Restaurant' button");
			}
		} else {
			Assert.fail("Pay at the restaurant button is not displayed" + base.getElement(XPATH, payLater));
		}
	}

	public void tapOnOrderNowButton() {

		base.waitForElementToBeClickable(orderNow_Button);
		if (base.getElement(XPATH, orderNow_Button) != null) {
			if (base.isDisplayed(orderNow_Button)) {
				base.tapElementUsingJS(orderNow_Button);
			} else {
				Assert.assertFalse(base.isDisplayed(orderNow_Button), "Not able to tap on 'Order Now' button");
			}
		} else {
			Assert.fail("Pay Now button is not displayed" + base.getElement(XPATH, orderNow_Button));
		}
	}

	public void tapConfirmButton() {

		if (base.getElement(XPATH, confirmButton) != null) {
			if (base.isDisplayed(confirmButton)) {
				base.tapElementUsingJS(confirmButton);
				base.delay(6000L);
			} else {
				Assert.assertFalse(base.isDisplayed(confirmButton), "Not able to tap on 'Confrim' button");
			}
		} else {
			Assert.fail("Confirm button is not displayed" + base.getElement(XPATH, confirmButton));
		}
	}

	public void verifyScreenErrorMessage() {

		paymentScreenErrorMessageMap.put("invalid_CardDetails", "Card information entered is invalid");
		paymentScreenErrorMessageMap.put("invalid_TipCardDetails", "Invalid tip and card details");

		// List of all error message displayed in the screen
		List<String> actualValidationMsg = base.getValidationMessages(ERROR_MESSAGE_FIELDS);

		// List of error-keys of all error messages displayed
		List<String> actualValidationMsgKeys = webForm.getActualErrorMessageKeys(actualValidationMsg,
				paymentScreenErrorMessageMap);

		// Comparing expected error message keys from excel to actual error keys of
		// displayed error messages
		webForm.compareMessageKeys(expectedMessageKeys, actualValidationMsgKeys);
	}

	public void verifyPaymentConfirmationScreen() {
//		base.waitForElementToBeVisible(confirmation_CheckInPlace);
		base.waitForVisibility(base.getElement(XPATH, confirmation_CheckInPlace));
		System.out.println("Displayed OR not :: " + base.isDisplayed(confirmation_CheckInPlace));
		String confirmation_CheckInPlaceText = base.gettext(confirmation_CheckInPlace);
		Assert.assertTrue(confirmation_CheckInPlaceText.trim().equalsIgnoreCase(commonUtility.checkInPlace.trim()),
				"Check-in place at the payment confirmation page is not as same as the check-in place displayed at login screen");

	}
	

	public void scrollTillASection(String scrollToText) {
		// TODO Auto-generated method stub  //div[contains(text(), 'Add a tip')]
		
		String scrollToRequiredSec = scrollToSection.replace("temp", scrollToText);
		System.out.println("Scroll To Element ::: To text :: "+scrollToText);
		if (base.getElement(XPATH, scrollToRequiredSec) != null) {
			base.delay(1000L);
			base.scrollToElementUsingJS(scrollToRequiredSec);
			base.delay(1000L);
		} else {
			Assert.assertNull(base.getElement(XPATH, scrollToRequiredSec), scrollToText + " item is not available");
		}
		
	}

	public void selectTipAmount() {
		if (base.getElement(XPATH, noTipButton) != null) {
			if (base.isDisplayed(noTipButton)) {
				base.tapElementUsingJS(noTipButton);
				base.delay(1000L);
			} else {
				Assert.fail(base.isDisplayed(noTipButton) + " - Tip button is not displayed");
			}
		} else {
			Assert.fail("No Tip button is not displayed" + base.getElement(XPATH, noTipButton));
		}
	}
	
	public void selectTip_Amount(String tip_Amount) {
		// TODO Auto-generated method stub
		if(tip_Amount.contains("%")) {
			tipAmount = tipAmount.replace("temp", tip_Amount.replaceAll("[^a-zA-Z0-9]", " ").trim());
		} else {
			tipAmount = tipAmount.replace("temp", tip_Amount.trim());
		}
		
		if (base.getElement(XPATH, tipAmount) != null) {
			if (base.isDisplayed(tipAmount)) {
				base.tapElementUsingJS(tipAmount);
				base.delay(1000L);
			} else {
				Assert.fail(base.isDisplayed(tipAmount) + " - Tip button is not displayed");
			}
		} else {
			Assert.fail("No Tip button is not displayed" + base.getElement(XPATH, tipAmount));
		}
		
	}

	public void enterAVECouponCode(String couponCode) {
		// TODO Auto-generated method stub
		if (base.getElement(XPATH, expandCouponField) != null) {
			base.tapElement(expandCouponField);
			if (base.isDisplayed(couponCode_Field)) {
				base.populateFields(couponCode_Field, couponCode);
			} else {
				Assert.fail(base.isDisplayed(couponCode_Field) + " - Coupon Code field is not displayed");
			}

		} else {
			Assert.fail("No Coupon details are displayed" + base.getElement(XPATH, expandCouponField));
		}
	}

	public void tapOnApplyCoupon() {
		// TODO Auto-generated method stub

		if (base.getElement(XPATH, applyCoupon_Button) != null) {
			if (base.isDisplayed(applyCoupon_Button)) {
				base.tapElementUsingJS(applyCoupon_Button);
			} else {
				Assert.assertFalse(base.isDisplayed(applyCoupon_Button), "Not able to tap on 'Apply Coupon' button");
			}
		} else {
			Assert.fail("Apply COupon button is not displayed" + base.getElement(XPATH, applyCoupon_Button));
		}
	}

	public void addUSAECardDetails(String cardNumberToEnter, String expiryDateToEnter, String CVVToEnter,
			String full_Name, String zipCodeToEnter) {
		// TODO Auto-generated method stub
		base.delay(6000L);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("paymentCardIFrame")));
		} catch (NoSuchElementException | TimeoutException e) {
			throw new NoSuchFrameException("No frame found with id/name: ");
		}catch (InvalidArgumentException e) {
			  throw new NoSuchFrameException("No frame found with id/name: " , e) ;
		 }
		
		base.populateFields(add_CardNumber, cardNumberToEnter);
		base.populateFields(add_ExpiryDate, expiryDateToEnter);
		base.populateFields(add_CVV, CVVToEnter);
		driver.switchTo().defaultContent();
		base.populateFields(fullName_USAE, full_Name);
		base.populateFields(add_ZipCode, zipCodeToEnter);
	}

	public void tapOnAddCardButton() {
		// TODO Auto-generated method stub
		System.out.println("Inside USAE Add Button");
		if (base.getElement(XPATH, addCardButton) != null) {
			if (base.isDisplayed(addCardButton)) {
				base.delay(4000L);
				base.tapElementUsingJS(addCardButton);
				base.delay(4000L);
			} else {
				Assert.assertFalse(base.isDisplayed(addCardButton), "Not able to tap on 'Add Card' button");
			}
		} else {
			Assert.fail("Apply Card button is not displayed" + base.getElement(XPATH, addCardButton));
		}

	}

	public void addShift4CardDetails(String cardType, String card_Number, String expirationMonth, String expirationYear,
			String CVV_Number, String zip_Code) {
		// TODO Auto-generated method stub
		base.delay(5000L);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(base.getElement(XPATH, "//iframe")));
		System.out.println("Switched to iFrame");

		base.selectDropdownValue(shift4_Card_Type, cardType);
		base.populateFields(shift4_CardNumber, card_Number);
		base.selectDropdownValue(shift4_ExpirationMonth, expirationMonth);
		base.selectDropdownValue(shift4_ExpirationYear, expirationYear);
		base.populateFields(shift4_CVV, CVV_Number);
		base.populateFields(shift4_ZipCode, zip_Code);
	}

	public void tapOnShift4AddCardButton() {
		// TODO Auto-generated method stub

		if (base.getElement(XPATH, add_shift4PayCard) != null) {
			if (base.isDisplayed(add_shift4PayCard)) {
				base.tapElementUsingJS(add_shift4PayCard);
				base.delay(1500L);
				driver.switchTo().defaultContent();
//				base.allowPermissionPopup();
			} else {
				Assert.assertFalse(base.isDisplayed(add_shift4PayCard), "Not able to tap on 'Add Card' button");
			}
		} else {
			Assert.fail("Add Card button is not displayed" + base.getElement(XPATH, add_shift4PayCard));
		}
	}




}
