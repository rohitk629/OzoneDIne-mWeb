package stepDefinitions;

import cucumber.api.java.en.Then;
import pages.OrderDetailsScreen;

public class OrderDetailsSteps {

	OrderDetailsScreen orderDetails = new OrderDetailsScreen();

	@Then("I should be able to navigate to {string} screen")
	public void i_should_be_able_to_navigate_to_screen(String input) {
		orderDetails.verifyActiveScreen(input);
	}

	@Then("I verify that selected category is {string}")
	public void i_verify_that_selected_category_is(String input) {
		orderDetails.verifySelectedCategory(input);
	}

	@Then("I scroll down the page till {string} menu and select the menu")
	public void i_scroll_down_the_page_till_menu_and_select_the_menu(String input) {
		orderDetails.scrollDownToMenuItem(input);
	}

	@Then("I tap on order button with {string} item to add it to the menu")
	public void i_tap_on_order_button_with_item_to_add_it_to_the_menu(String input) {
		orderDetails.tapOrderMenuButton(input);
	}

	@Then("I should be able to navigate to menu details screen")
	public void i_should_be_able_to_navigate_to_menu_details_screen() {
		orderDetails.verifyMenuDetailsScreen();
	}

	@Then("I tap on Add to Bag button")
	public void i_tap_on_Add_to_Bag_button() {
		orderDetails.tapAddToBagButton();
	}
	

	@Then("I select {string} , {string} and {string} as the required modifiers")
	public void i_select_and_as_the_required_modifiers(String modifier1, String modifier2, String modifier3) {
		 orderDetails.selectRequiredModifiers(modifier1, modifier2, modifier3);
	}
	
	@Then("I tap on {string} tab and verify the page header title")
	public void i_tap_on_tab_and_verify_the_page_header_title(String selectMenuTab) {
		orderDetails.selectRequiredMenu(selectMenuTab);
	}
	
	@Then("I tap on {string} from bottom footer")
	public void i_tap_on_from_bottom_footer(String tabToClick) {
		orderDetails.tapOnRequiredTab(tabToClick);
	}
	
}
