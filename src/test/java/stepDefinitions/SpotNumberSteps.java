package stepDefinitions;

import java.io.IOException;

import cucumber.api.java.en.Then;
import pages.SpotNumberScreen;
import utilities.StaticDataProvider;

public class SpotNumberSteps {

	SpotNumberScreen spotNoScreen = new SpotNumberScreen();

	@Then("I naviagte to spot number screen and verify the message displayed on the screen")
	public void i_naviagte_to_spot_number_screen_and_verify_the_message_displayed_on_the_screen() {
		spotNoScreen.verifySpotNumberScreen();
	}
	
	@Then("I navigate to spot number screen and verify the message displayed on the screen")
	public void i_navigate_to_spot_number_screen_and_verify_the_message_displayed_on_the_screen() {
		spotNoScreen.verifySpotNumberScreen();
	}

	@Then("I enter the Spot Number from the excel sheet {string} in the Spot Number field")
	public void i_enter_the_Spot_Number_from_the_excel_sheet_in_the_Spot_Number_field(String sheetName)
			throws IOException {
		String[][] testData = StaticDataProvider.getExcelData(sheetName);
		spotNoScreen.populateSpotNoScreenFields(testData);
	}

	@Then("I tap on next arrow button and submit the spot number")
	public void i_tap_on_next_arrow_button_and_submit_the_spot_number() {
		spotNoScreen.tapNextArrowButton();
	}

	@Then("it navigates to the Order screen and I verify if the Order tab is selected in the bottom footer")
	public void it_navigates_to_the_Order_screen_and_I_verify_if_the_Order_tab_is_selected_in_the_bottom_footer() {
		spotNoScreen.verifySelectedOrderTab();
	}

	@Then("I verify the error message displayed")
	public void i_verify_the_error_message_displayed() {
		spotNoScreen.verifyScreenErrorMessages();
	}

}
