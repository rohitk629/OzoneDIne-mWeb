package stepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.MyBagScreen;

public class MyBagSteps {

	MyBagScreen myBag = new MyBagScreen();

	@Then("I tap on My Bag from bottom footer")
	public void i_tap_on_My_Bag_from_bottom_footer() {
		myBag.tapOnMyBagTab();
	}

	@When("I click on the next buttton")
	public void i_click_on_the_next_buttton() {
		myBag.tapOnNextButton();
	}

	@Then("I should be able to navigate to checkout screen")
	public void i_should_be_able_to_navigate_to_checkout_screen() {
		myBag.verifyCheckOutScreen();
	}

	@Then("I verify that added menu is being displayed in the screen")
	public void i_verify_that_added_menu_is_being_displayed_in_the_screen() {
		myBag.verifyAddedMenuIsDisplayed();
	}
	
	@Then("I enter {string} as a name for this order and submit the yes button")
	public void i_enter_as_a_name_for_this_order_and_submit_the_yes_button(String seatName) {
		myBag.enterSeatNameAndSubmit(seatName);
	}


}
