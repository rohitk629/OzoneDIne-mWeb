package stepDefinitions;

import java.io.IOException;

import cucumber.api.java.en.Then;
import pages.MyBagScreen;
import pages.PaymentScreen;
import utilities.StaticDataProvider;

public class PaymentSteps {

	PaymentScreen payment = new PaymentScreen();
	MyBagScreen myBag = new MyBagScreen();

	@Then("I tap on Pay Now button")
	public void i_tap_on_Pay_Now_button() {
		myBag.tapOnPayNowButton();
	}

	@Then("I should be able to navigate to Payment screen of the application")
	public void i_should_be_able_to_navigate_to_Payment_screen_of_the_application() {
		payment.verifyPaymentScreen();
	}

	@Then("I enter the valid card details into the fields from the excel sheet name {string}")
	public void i_enter_the_valid_card_details_into_the_fields_from_the_excel_sheet_name(String sheetName)
			throws IOException {
		String[][] testData = StaticDataProvider.getExcelData(sheetName);
		payment.populatePaymentScreenFields(testData);
	}

	@Then("I select No Tip as a tip amount")
	public void i_select_No_Tip_as_a_tip_amount() {
		payment.selectTipAmount();
	}

	@Then("I scroll down the page till {string} section")
	public void i_scroll_down_the_page_till_section(String scrollToText) {
		payment.scrollTillASection(scrollToText);
	}

	@Then("I select {string} as a tip amount")
	public void i_select_as_a_tip_amount(String tipAmount) {
		payment.selectTip_Amount(tipAmount);
	}

	@Then("I tap on Pay Now button available on payment screen")
	public void i_tap_on_Pay_Now_button_available_on_payment_screen() {
		payment.tapPayNowButton();
	}

	@Then("I tap on Confirm button to proceed the payment")
	public void i_tap_on_Confirm_button_to_proceed_the_payment() {
		payment.tapConfirmButton();
	}

	@Then("I tap on Pay at the Restaurant button available on the Payment screen")
	public void i_tap_on_Pay_at_the_Restaurant_button_available_on_the_Payment_screen() {
		payment.tapPayAtRestaurantButton();
	}

	@Then("I tap on Order Now button")
	public void i_tap_on_Order_Now_button() {
		payment.tapOnOrderNowButton();
	}

	@Then("I am on payment confirmation screen")
	public void i_am_on_payment_confirmation_screen() {
		payment.verifyPaymentConfirmationScreen();
	}

	@Then("I enter the invalid card details into the fields from the excel sheet name {string}")
	public void i_enter_the_invalid_card_details_into_the_fields_from_the_excel_sheet_name(String sheetName)
			throws IOException {
		String[][] testData = StaticDataProvider.getExcelData(sheetName);
		payment.populatePaymentScreenFields(testData);
	}

	@Then("error message\\(s) are displayed on the payment screen")
	public void error_message_s_are_displayed_on_the_payment_screen() {
		// Write code here that turns the phrase above into concrete actions
		payment.verifyScreenErrorMessage();
	}

	@Then("I enter the AVE Coupon number as {string}")
	public void i_enter_the_AVE_Coupon_number_as(String couponCode) {
		// Write code here that turns the phrase above into concrete actions
		payment.enterAVECouponCode(couponCode);
	}

	@Then("I tap on apply coupon button")
	public void i_tap_on_apply_coupon_button() {
		// Write code here that turns the phrase above into concrete actions
		payment.tapOnApplyCoupon();
	}

	@Then("I add the valid card details with {string} , {string} , {string} , {string} and {string}")
	public void i_add_the_valid_card_details_with_and(String cardNumber, String expiryDate, String CVV,
			String full_Name, String zipCode) {
		// Write code here that turns the phrase above into concrete actions
		payment.addUSAECardDetails(cardNumber, expiryDate, CVV, full_Name, zipCode);
	}

	@Then("I tap on Add Card button")
	public void i_tap_on_Add_Card_button() {
		// Write code here that turns the phrase above into concrete actions
		payment.tapOnAddCardButton();
	}

	@Then("I tap on Add Card button for Shift Pay")
	public void i_tap_on_Add_Card_button_for_Shift_Pay() {
		// Write code here that turns the phrase above into concrete actions
		payment.tapOnShift4AddCardButton();
	}

	@Then("I select the card as {string} and enter all other mandatory fields {string} , {string} , {string} , {string} and {string}")
	public void i_select_the_card_as_and_enter_all_other_mandatory_fields_and(String cardType, String cardNumber,
			String expirationMonth, String expirationYear, String CVV, String zipCode) {
		// Write code here that turns the phrase above into concrete actions
		payment.addShift4CardDetails(cardType, cardNumber, expirationMonth, expirationYear, CVV, zipCode);
		;
	}

}
